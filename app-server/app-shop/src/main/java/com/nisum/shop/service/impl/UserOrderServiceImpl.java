package com.nisum.shop.service.impl;

import com.nisum.common.constant.CommonEndPointConstant;
import com.nisum.common.constant.ErrorLevel;
import com.nisum.common.constant.OrderStatus;
import com.nisum.common.exception.rest.RestException;
import com.nisum.shop.api.dto.ProductDTO;
import com.nisum.shop.dto.OrderDTO;
import com.nisum.shop.dto.OrderItemDTO;
import com.nisum.shop.dto.UserOrderDTO;
import com.nisum.shop.model.Order;
import com.nisum.shop.model.OrderItem;
import com.nisum.shop.model.User;
import com.nisum.shop.repository.OrderRepository;
import com.nisum.shop.service.EmailService;
import com.nisum.shop.service.OrderService;
import com.nisum.shop.service.UserOrderService;
import com.nisum.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The Class UserOrderServiceImpl.
 */
@Service
public class UserOrderServiceImpl implements UserOrderService{

	/** The order repository. */
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	RestTemplate restClient;
	
	/** The order service. */
	@Autowired
	OrderService orderService;
	
	@Autowired
	EmailService emailService;

	@Autowired
	UserService userService;

	
	/* (non-Javadoc)
	 * @see com.nisum.service.UserOrderService#getOrderHistoryByUserId(com.nisum.dto.UserOrderDTO)
	 */
	@Override
	@Transactional(readOnly=true)
	public Page<OrderDTO> getOrderHistoryByUserId(UserOrderDTO userOrderDTO, Pageable page){
		Page<Order> orders = orderRepository.getOrderHistoryByUserId(userOrderDTO.getUserId(), userOrderDTO.getStartDateInLong(), userOrderDTO.getEndDateInLong(), page);

		if(orders!=null && orders.hasContent()){
			return new PageImpl<>((orders.getContent().parallelStream().map(o ->
			new OrderDTO(o.getId(),o.getCreatedAt(),o.getOrderStatus(),o.getTotalItem(),o.getOrderTotalPrice())).collect(Collectors.toList())));
			
		}else{
			throw new RestException(HttpStatus.NOT_FOUND, "Orders not found", ErrorLevel.ERROR);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.nisum.service.UserOrderService#getOrderByUserIdAndOrderId(com.nisum.dto.UserOrderDTO)
	 */
	@Override
	public OrderDTO getOrderByUserIdAndOrderId(UserOrderDTO userOrderDTO){
		Order order = orderRepository.getOrderByUserIdAndOrderId(userOrderDTO.getUserId(), userOrderDTO.getOrderId());
		OrderDTO orderDto = null;
		if(order==null) {
			throw new RestException(HttpStatus.NOT_FOUND, "Order not found", ErrorLevel.ERROR);
		}else {
			orderDto = new OrderDTO();
			Set<OrderItemDTO> orderItemList = new HashSet<OrderItemDTO>();
			
			orderDto.setOrderId(order.getId());
			orderDto.setOrderStatus(order.getOrderStatus());
			orderDto.setCreatedAt(order.getCreatedAt());
			orderDto.setOrderTotalPrice(order.getOrderTotalPrice());
			orderDto.setTotalItem(order.getTotalItem());
			orderDto.setPaymentType(orderDto.getPaymentType());
			
			for(OrderItem orderItem : order.getOrderItems()) {
				OrderItemDTO orderItemDTO;
				
					ProductDTO productDto = restClient.getForObject(CommonEndPointConstant.CART_PRODUCT_FIND_ONE, ProductDTO.class,orderItem.getItemUUID());
					
					if(productDto != null && productDto.getItem() != null) {
						
					orderItemDTO = new OrderItemDTO(orderItem.getId(), orderItem.getPrice(), orderItem.getQuantity(), 
							productDto.getSku(), productDto.getItem().getName(),productDto.getItem().getDefaultImage());
					
					orderItemList.add(orderItemDTO);
					
					}
					
					orderDto.setOrderItems(orderItemList);
			}
		}
		
		return orderDto; 
	}

	/* (non-Javadoc)
	 * @see com.nisum.service.UserOrderService#cancelUnshippedOrder(com.nisum.dto.UserOrderDTO)
	 */
	@Override
	@Transactional
	public OrderDTO cancelUnshippedOrder(UserOrderDTO userOrderDTO){
		Order order = orderRepository.getOrderByUserIdAndOrderId(userOrderDTO.getUserId(), userOrderDTO.getOrderId());
		OrderDTO orderDto = null;
		
		User user = restClient.getForObject(CommonEndPointConstant.USER_FIND_ONE+userOrderDTO.getUserId(), User.class);
		
		if(order != null){
			order.setOrderStatus(OrderStatus.CANCELLED.getValue());
			
			order = orderService.update(order);
			
			orderDto = new OrderDTO(order.getId(),order.getCreatedAt(),order.getOrderStatus(),order.getTotalItem(),order.getOrderTotalPrice());
			
			
			emailService.sendCancelOrderEmail(order, user);
		}else{
			throw new RestException(HttpStatus.NOT_FOUND, "Order does not exist. Could not cancel order", ErrorLevel.ERROR);
		}
		return orderDto;
	}
}
