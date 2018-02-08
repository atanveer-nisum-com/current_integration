package com.nisum.shop.service.impl;

import com.nisum.common.constant.ErrorLevel;
import com.nisum.common.constant.OrderStatus;
import com.nisum.common.exception.rest.RestException;
import com.nisum.common.util.DateUtils;
import com.nisum.shop.api.dto.ProductDTO;
import com.nisum.shop.dto.CartResponseDTO;
import com.nisum.shop.dto.OrderItemDTO;
import com.nisum.shop.dto.TokenDTO;
import com.nisum.shop.model.Order;
import com.nisum.shop.model.OrderItem;
import com.nisum.shop.model.Tax;
import com.nisum.shop.model.User;
import com.nisum.shop.repository.OrderRepository;
import com.nisum.shop.service.*;
import com.nisum.shop.util.OrderItemConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;


/**
 * The class {@code OrderServiceImpl} implements OrderService Interface
 * 
 * @author mabdullah
 */

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	OrderItemService orderItemService;
	@Autowired
	UserService userService;

	@Autowired
	TaxService taxService;
	@Autowired
	OrderService orderService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	OrderItemConverter converter;
	
	/** 
	 * 
	 * This method will take orderId as argument and return the unique Order with that ID 
	 */
	@Transactional(readOnly=true)
	public Order findOrderById(Long id) {
		return orderRepository.findOrderById(id);
	}
	
	/**
	 *  
	 * 		This method will take UserID (optional) and itemId as parameter If userId is
	 *      null it will generate guest user and create an order against the guest
	 *      user with orderItems. If userId is not null it will check for active
	 *      orders against user if present it will update the order else it will
	 *      generate the order against the user
	 *      
	 *      @param	userId	Id of user which is creating the order can be null for first time guest user
	 *      @param	itemId	Id of item being added to cart
	 *      @return	Order	Object of Order that is created and stored in database
	 */
	public CartResponseDTO save(Long userId, String itemId) {
		Order order = null;
		TokenDTO tokenDTO = null;
		OrderItem orderItem = null;
		User user = null;
		
		ProductDTO product = productService.findAvailable(itemId);

		if (product != null) {
			if (userId == null) {
				user = userService.saveGuest();
				tokenDTO = this.setUserToken(user.getAuthToken());
				order = this.saveOrder(user, product);
				orderItem = orderItemService.saveOrderItem(order, product);
				order.addOrderItem(orderItem);
			} else {
				user = userService.findOne(userId);
				if (user == null) {
					throw new RestException(HttpStatus.NO_CONTENT, "User not found in Database", ErrorLevel.ERROR);
				}
				order = this.getActiveOrderByUserId(userId);  //order checks
				if (order == null) {
					order = this.saveOrder(user, product);
					orderItem = orderItemService.saveOrderItem(order, product);
					order.addOrderItem(orderItem);
				} else {
					orderItem = orderItemService.getOrderItemByOrderIdAndItemUUId(order.getId(), itemId);
					if (orderItem == null) {
						orderItemService.saveOrderItem(order, product);
					}else {
						orderItemService.updateOrderItem(orderItem.getId(), orderItem.getQuantity()+1);
					}
					order = this.update(order, product);
				}
			}
			this.updateSubTotalPrice(order);
		} else {
			throw new RestException(HttpStatus.NOT_ACCEPTABLE, "Item not buyable or not in inventory", ErrorLevel.ERROR);
		}
		Set<OrderItem> orderItemSet = order.getOrderItems();

		List<ProductDTO> productDTOList = createProductDTOList(orderItemSet);
		
		CartResponseDTO cartDto = this.createCartDTO(order, orderItemSet, productDTOList, tokenDTO);
		return cartDto;
	}
	
	/**
	 *      This method will set the guest user token in cartDTO
	 * 
	 *      @param	cartDTO		Object of cartDto whose token has to be set
	 *      @param	authToken	user token
	 */
	private TokenDTO setUserToken(String authToken) {
		TokenDTO token = new TokenDTO();
		token.setToken(authToken);		
		return token;
	}

	/**
	 *      This method will take Order as argument and update the Order Sub Total
	 *      Price(i.e. with out tax), add up the individual taxes of items and and
	 *      calculate the Order Total Price (i.e. tax inclusive)
	 * 
	 *      @param	Order	Object of order whose pricing and taxation are to be calculated 
	 *      @return	Order	Object of Order that is stored in database
	 *      TODO this method needs to be optimized later so not to iterate complete
	 *      list of order items
	 */
	@Transactional
	public Order updateSubTotalPrice(Order order) {
		Set<OrderItem> orderItemSet = order.getOrderItems();
		OrderItem orderItem = null;
		Iterator<OrderItem> orderItemIterator = orderItemSet.iterator();
		order.setSubTotalPrice(new BigDecimal(0));
		while (orderItemIterator.hasNext()) {
			orderItem= orderItemIterator.next();
			order.setSubTotalPrice(order.getSubTotalPrice().add(orderItem.getSubTotalPrice()));
		}
		return orderRepository.save(order);
	}
	
	/**
	 * Calculates the percentage and value of taxation
	 * 
	 * @param 	order
	 * @param 	tax
	 * @return	BigDecimal value of taxation
	 */
	private BigDecimal calculateTaxation(Order order, Tax tax) {
		BigDecimal percentValue = new BigDecimal(0);
		if (tax != null) {
			BigDecimal percent = tax.getPercentage().divide(new BigDecimal(100));
			percentValue = order.getSubTotalPrice().multiply(percent);
		}
		return percentValue;
	}
	
	/**
	 * Calculates the Order Total Price by adding taxation to sub total price
	 * 
	 * @param 	order
	 * @return	BigDecimal value of order total price
	 */
	private BigDecimal calculateOrderTotalPrice(Order order) {
		BigDecimal orderTotalPrice = order.getSubTotalPrice().add(order.getTaxation() == null ? new BigDecimal("0") : order.getTaxation());
		return orderTotalPrice;
	}
	
	/**
	 *      This method will take orderId and taxAbbrv as arguments 
	 *      and update the Order taxation and Order Total Price in the order
	 * 
	 *      @param	orderId		Object of order whose pricing and taxation are to be calculated 
	 *      @return	taxAbbvr	Object of Order that is stored in database
	 */
	@Transactional
	public Order updateTaxationPrice(Long orderId, String taxAbbrv ) {
		Tax tax = taxService.findByAbbrv(taxAbbrv);
		Order order = orderService.findOrderById(orderId);
		order.setTaxation(this.calculateTaxation(order, tax));
		order.setOrderTotalPrice(this.calculateOrderTotalPrice(order));
		return orderRepository.save(order);
	}
	
	/**
	 *      This method will fill the Order Object with
	 *      required details provided as parameter and save it in the database
	 *      
	 *      @param	user	Object of user 
	 *      @param	product	Object of product being added to cart
	 *      @return	Order	Object of Order that is stored in database
	 */
	
	@Transactional
	public Order saveOrder(User user,ProductDTO item) {
		Order order = new Order();
		order.setUserId(user.getId());
		order.setCreatedAt(DateUtils.getCurrentTime());
		order.setUpdatedAt(DateUtils.getCurrentTime());
		order.setOrderStatus(OrderStatus.AWAITING_PAYMENT.getValue());
		order.setTotalItem(1);//initially adding item to cart from screen will add only one item
		
		return orderRepository.save(order);
	}
	
	/**
	 *      This method will update the Order and increase the
	 *      quantity by 1
	 * 
	 *      @param	order	Object of order that is to be updated 
	 *      @param	item	Object of productDTO being added to cart
	 *      @return	Order	Object of Order that is updated and stored in database
	 */
	
	@Transactional
	public Order update(Order order, ProductDTO item) {
		order.setTotalItem(order.getTotalItem()+1);
		order.setUpdatedAt(DateUtils.getCurrentTime());
		
		return orderRepository.save(order);
	}

	/**
	 * 		This method will call OrderItemService to remove the OrderItem and also
	 *      decrease the quantity count in Order table
	 *      
	 *      @param	orderItemId	Id of OrderItem to be removed from cart
	 *      @return	Order		Object of Order that is updated and stored in database
	 */
	@Transactional
	public Order removeOrderItem(Long orderItemId) {		
		OrderItem orderItem = orderItemService.findOne(orderItemId);
		Order order = null;
		if (orderItem == null) {
			throw new RestException(HttpStatus.NO_CONTENT, "Item not found in Cart", ErrorLevel.ERROR);
		}else {
			order = orderItem.getOrder();
			if (order != null) {
				Integer quantity = orderItem.getQuantity();
				orderItemService.removeOrderItem(orderItemId);
				order.setTotalItem(order.getTotalItem() - quantity);
				order = orderRepository.save(order);
			}else {
				throw new RestException(HttpStatus.NO_CONTENT, "Order not found against the Order Item", ErrorLevel.ERROR);
			}
		}
		order = this.getActiveOrderByUserId(orderItem.getOrder().getUserId());
		this.updateSubTotalPrice(order);
		
		return order;
	}
	
	/**
	 *      This method will call OrderItemService to change the
	 *      quantity of OrderItem and also change the quantity count in Order table
	 *      
	 *      @param	orderItemId	Id of orderItem to update the orderItem with updated quantity
	 *      @param	quantity	quantity of items being added to cart
	 *      @return	Order		Object of Order that is updated and stored in database
	 */
	@Transactional
	public CartResponseDTO updateOrderItem(Long orderItemId, Integer quantity) {		
		OrderItem orderItem = orderItemService.findOne(orderItemId);
		Order order = null;
		if (orderItem == null) {
			throw new RestException(HttpStatus.NO_CONTENT, "Item not found in Cart", ErrorLevel.ERROR);
		}else {
			order = orderItem.getOrder();
			if (orderItem.getQuantity().equals(quantity)) {
				return getCartByUserIdWithResponseDTO(order.getUserId());
			}

			Integer oldQuantity = orderItem.getQuantity();
			orderItemService.updateOrderItem(orderItemId,quantity);
			if (oldQuantity.compareTo(quantity) > 0) { //oldQuantity > quantity
				order.setTotalItem(order.getTotalItem() - (oldQuantity - quantity));
			}else {
				order.setTotalItem(order.getTotalItem() + (quantity - oldQuantity));
			}
			order = orderRepository.save(order);
		}
		this.updateSubTotalPrice(order);
		
		return getCartByUserIdWithResponseDTO(order.getUserId());
	}
	
	/**
	 *      This method will get the order by userId from database which is in
	 *      AWAITING_PAYMENT state
	 * 
	 * 	  	@param	userId		User Id of active user
	 *      @return	Order		Object of Order that is stored in database
	 */
	@Transactional(readOnly=true)
	public Order getActiveOrderByUserId(Long userId) {
		return orderRepository.getActiveOrderByUserId(userId);
	}
	
	
	
	@Transactional(readOnly=true)
	public CartResponseDTO getCartByUserIdWithResponseDTO(Long userId) {
		if(userId == null){
			throw new RestException(HttpStatus.NO_CONTENT, "user id is not found", ErrorLevel.ERROR);
		}
		Order order = orderRepository.getActiveOrderByUserId(userId);
		if(order == null)
			throw new RestException(HttpStatus.NO_CONTENT, "No order is placed againt user", ErrorLevel.ERROR);

		Set<OrderItem> orderItemSet = order.getOrderItems();

		List<ProductDTO> productDTOList = createProductDTOList(orderItemSet);
		
		CartResponseDTO cartDto = createCartDTO(order, orderItemSet, productDTOList, null);
		
		return cartDto;
	}

	/**
	 * @param orderItemSet
	 * @return
	 */
	private List<ProductDTO> createProductDTOList(Set<OrderItem> orderItemSet) {
		List<ProductDTO> productDTOList  = new ArrayList<>();
		Iterator<OrderItem> orderItemSetIterator = orderItemSet.iterator();
		while (orderItemSetIterator.hasNext()) {
			productDTOList.add(productService.findOne(orderItemSetIterator.next().getItemUUID()));
		}
		return productDTOList;
	}

	private CartResponseDTO createCartDTO(Order order, Set<OrderItem> orderItemSet, List<ProductDTO> productDTOList,TokenDTO token) {
		CartResponseDTO cartDto = new CartResponseDTO();
		if(token!=null)
			cartDto.setUserToken(token);
		cartDto.setId(order.getId());
		cartDto.setOrderTotalPrice(order.getOrderTotalPrice());
		cartDto.setSubTotalPrice(order.getSubTotalPrice());
		cartDto.setTaxation(order.getTaxation());
		cartDto.setTotalItem(order.getTotalItem());
//		cartDto.setOrderItems(ETDUtils.toOrderItemDTO(orderItemSet,productDTOList));
		Set<OrderItemDTO> orderItemDTOSet = new HashSet<>();
		int i =0;
		for(OrderItem eachOrder:orderItemSet) {
			OrderItemDTO orderItemDto = converter.convertToDto(eachOrder);
			orderItemDto.setProduct(productDTOList.get(i++));
			orderItemDTOSet.add(orderItemDto);
		}
		cartDto.setOrderItems(orderItemDTOSet);
			
		return cartDto;
	}
	


	/**
	 * 		This method return List of all the orders that are in AWAITING_PAYMENT state
	 * 
	 * 		@return	List<Order>		List of all the orders in AWAITING_PAYMENT state
	 */
	public List<Order> getAllActiveOrder() {
		List<Order> orders = orderRepository.getAllActiveOrder();

		if (orders == null || orders.size() == 0) {
			throw new RestException(HttpStatus.NO_CONTENT, "OrderList is empty", ErrorLevel.INFO);
		}
		return orders;
	}


	@Override
	public Order update(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public Order mergeCartFromGuestUser(Long guestUserId, Long returningUser) {
		
		Order guestOrder = getActiveOrderByUserId(guestUserId);
		
		Order existingOrder = getActiveOrderByUserId(returningUser);
		
		if ( guestOrder == null) return null;
		
		if (existingOrder == null) {
			existingOrder = guestOrder;
			existingOrder.setUserId(null);
			return existingOrder;
		}
		
		for ( OrderItem orderItem : guestOrder.getOrderItems()) {
			existingOrder.addOrderItem(orderItem);
		}
		existingOrder.setTotalItem(existingOrder.getTotalItem() + guestOrder.getTotalItem() );
		this.updateSubTotalPrice(existingOrder);
		this.orderRepository.delete(guestOrder);
		return existingOrder;
	
	}
	

	/**
	 * 		This method will call OrderItemService to remove the OrderItem and also
	 *      decrease the quantity count in Order table
	 *      
	 *      @param	orderItemId	Id of OrderItem to be removed from cart
	 *      @return	Order		Object of Order that is updated and stored in database
	 */
	@Transactional
	public CartResponseDTO removeOrderItemReturnDTO(Long orderItemId) {		
		OrderItem orderItem = orderItemService.findOne(orderItemId);
		Order order = null;
		if (orderItem == null) {
			throw new RestException(HttpStatus.NO_CONTENT, "Item not found in Cart", ErrorLevel.ERROR);
		}else {
			order = orderItem.getOrder();
			if (order != null) {
				Integer quantity = orderItem.getQuantity();
				orderItemService.removeOrderItem(orderItemId);
				order.setTotalItem(order.getTotalItem() - quantity);
				order = orderRepository.save(order);
			}else {
				throw new RestException(HttpStatus.NO_CONTENT, "Order not found against the Order Item", ErrorLevel.ERROR);
			}
		}
		order = this.getActiveOrderByUserId(orderItem.getOrder().getUserId());
		this.updateSubTotalPrice(order);

		return getCartByUserIdWithResponseDTO(orderItem.getOrder().getUserId());
	}

	
}
