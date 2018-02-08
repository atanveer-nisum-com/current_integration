package com.nisum.shop.service.impl;

import com.nisum.common.constant.AppConstant;
import com.nisum.common.constant.CommonEndPointConstant;
import com.nisum.common.constant.ErrorLevel;
import com.nisum.common.exception.rest.RestException;
import com.nisum.common.util.DateUtils;
import com.nisum.shop.api.dto.ProductDTO;
import com.nisum.shop.model.Order;
import com.nisum.shop.model.OrderItem;
import com.nisum.shop.repository.OrderItemRepository;
import com.nisum.shop.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

/**
 * The class {@code OrderItemServiceImpl} implements OrderItemService Interface
 * 
 * @author mabdullah
 */
@Service
public class OrderItemServiceImpl implements OrderItemService{

	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private RestTemplate restClient;
	
	/**
	 * 		This method will find a unique orderItem with the given Id
	 * 
	 *      @param	orderItemId	Id of item being added to cart
	 *      @return	OrderItem	Object of Order that is created and stored in database
	 */
	@Transactional(readOnly=true)
	public OrderItem findOne(Long id) {
		return orderItemRepository.findOrderItemById(id);
	}
	
	/**
	 * 		This method will return the orderItem with respect to the orderId and ItemId
	 * 
	 * 		@param	orderId
	 * 		@param	itemId
	 * 		@return	OrderItem
	 */
//	@Transactional(readOnly=true)
//	public OrderItem getOrderItemByOrderIdAndItemId(Long orderId, Long itemId) {
//		return orderItemRepository.getOrderItemByOrderIdAndItemId(orderId, itemId);
//	}
	
	@Transactional(readOnly=true)
	public OrderItem getOrderItemByOrderIdAndItemUUId(Long orderId, String itemId) {
		return orderItemRepository.getOrderItemByOrderIdAndItemUUId(orderId, itemId);
	}
	
	
	/**
	 * 		This method will return the count of unique items in cart 
	 * 		(this method is not being used)
	 * 
	 * 		@param	orderId
	 * 		@return	Integer
	 */
	@Transactional(readOnly=true)
	public Integer getOrderItemsCount(Long orderId) {
		return orderItemRepository.getOrderItemsCount(orderId);
	}
	
	/**
	 * 		This method will return the List of OrderItems in the Order
	 * 
	 * 		@param	orderId
	 * 		@return	List<OrderItem>
	 */
	@Transactional(readOnly=true)
	public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
		return orderItemRepository.getOrderItemsByOrderId(orderId);
	}
	
	/**
	 * 		This method will return the appropriate price in BigDecimal of orderItem with
	 * 		respect to the quantity
	 * 
	 * 		@param	item
	 * 		@param	orderItem
	 * 		@return	BigDecimal	
	 */
//	@Transactional(readOnly=true)
//	public OrderItem setPricesAndTaxation(Item item, OrderItem orderItem) {
//		if (orderItem.getQuantity() == 0) {
//			return orderItem;
//		}
//		Set<Price> itemPriceSet = item.getItemPrice();
//		Price price = null;
//		boolean priceFound = false;
//		Iterator<Price> itemPriceIterator = itemPriceSet.iterator();
//		while (itemPriceIterator.hasNext() && !priceFound) {
//			price= itemPriceIterator.next();
//			if ((price.getMinQuantity() <= orderItem.getQuantity()) && ((orderItem.getQuantity() <= price.getMaxQuantity()) || price.getMaxQuantity() == -1)) {
//				priceFound = true;
//				break;
//			}
//		}
//		if (!priceFound) {
//			throw new RestException(HttpStatus.NO_CONTENT, "Price for the item not found in Database", ErrorLevel.ERROR);
//		}
//		orderItem.setPrice(new BigDecimal(10));//price.getPrice()
//		orderItem.setSubTotalPrice(orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity())));
//		orderItem.setUpdatedAt(DateUtils.getCurrentTime());
//		return orderItem;
//	}
	
	/**
	 * 		This method will return orderItem with
	 * 		respect to the quantity
	 * 
	 * 		@param	item
	 * 		@param	orderItem
	 * 		@return	OrderItem	
	 */
	@Transactional(readOnly=true)
	public OrderItem setPricesAndTaxation(ProductDTO item, OrderItem orderItem) {
		if (orderItem.getQuantity() == 0) {
			return orderItem;
		}
		orderItem.setPrice(item.getItem().getPrice());
		orderItem.setSubTotalPrice(orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity())));
		orderItem.setUpdatedAt(DateUtils.getCurrentTime());
		return orderItem;
	}
	

	/**
	 *      This method will set the required fields in
	 *      orderItem object and save the orderItems in database
	 * 
	 * 		@param	order
	 * 		@param	item
	 * 		@return	OrderItem
	 */
//	@Transactional
//	public OrderItem saveOrderItem(Order order, Item item) {
//		OrderItem orderItem = new OrderItem();
//		orderItem.setQuantity(1);
//		orderItem.setOrder(order);
////		orderItem.setItem(item);
//		orderItem = this.setPricesAndTaxation(item, orderItem);
//		orderItem.setCreatedAt(DateUtils.getCurrentTime());
//		orderItem.setUpdatedAt(DateUtils.getCurrentTime());
//		orderItem.setIsDeleted(AppConstant.NOT_DELETED);
//		return orderItemRepository.save(orderItem);
//	}
	
	/**
	 *      This method will set the required fields in
	 *      orderItem object and save the orderItems in database
	 * 
	 * 		@param	order
	 * 		@param	item
	 * 		@return	OrderItem
	 */
	@Transactional
	public OrderItem saveOrderItem(Order order, ProductDTO item) {
		OrderItem orderItem = new OrderItem();
		orderItem.setQuantity(1);
		orderItem.setOrder(order);
		orderItem.setItemUUID(item.getItem().getId());
		orderItem = this.setPricesAndTaxation(item, orderItem);
		orderItem.setCreatedAt(DateUtils.getCurrentTime());
		orderItem.setUpdatedAt(DateUtils.getCurrentTime());
		orderItem.setIsDeleted(AppConstant.NOT_DELETED);
		return orderItemRepository.save(orderItem);
	}
	
	/**
	 * 		This method will update the orderItem quantity and respective prices and taxation
	 * 
	 * 		@param	orderItemId
	 * 		@param	quantity
	 * 		@return	OrderItem
	 */
	@Transactional
	public OrderItem updateOrderItem(Long orderItemId, Integer quantity) {
		OrderItem orderItem = this.findOne(orderItemId);
//		Item item = null;
		if (orderItem != null) {
			if (quantity != null) {
				ProductDTO product = restClient.getForObject(CommonEndPointConstant.CART_PRODUCT_FIND_ONE, ProductDTO.class,orderItem.getItemUUID());

//				item = orderItem.getItem();
//				if (item.getQuantity().compareTo(quantity) < 0) {
//					throw new RestException(HttpStatus.NOT_FOUND, "Quantity exceeds the inventory", ErrorLevel.ERROR);
//				}else {
					orderItem.setQuantity(quantity);
//				}
				orderItem = this.setPricesAndTaxation(product, orderItem);
				return orderItemRepository.save(orderItem);
			}else {
				throw new RestException(HttpStatus.NOT_FOUND, "Quantity is NULL", ErrorLevel.ERROR);
			}
		}else {
			throw new RestException(HttpStatus.NOT_ACCEPTABLE, "Item not found in Cart", ErrorLevel.ERROR);
		}
	}

	/**
	 * 		This method will remove the orderItem with the given orderItemId 
	 * 		from orderItem table in database table
	 * 
	 * 		@param 	orderItemId
	 * 		@return	void
	 */
	@Transactional
	public void removeOrderItem(Long orderItemId) {
		OrderItem orderItem = this.findOne(orderItemId);
		if (orderItem != null) {
			orderItemRepository.delete(orderItem);
		}else {
			throw new RestException(HttpStatus.NO_CONTENT, "Item not found in Cart", ErrorLevel.ERROR);
		}
	}
	
	/**
	 * 		This method will delete all the orderItems for the specific Order with given orderId
	 * 
	 * 		@param orderId
	 * 		@return	void
	 */
	@Transactional
	public void deleteAllOrderItems(Long orderId) {
		if (!this.getOrderItemsByOrderId(orderId).isEmpty()) {
			orderItemRepository.deleteAllOrderItems(orderId);
		}
	}
	
}
