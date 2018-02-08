package com.nisum.shop.service.impl;


import com.nisum.common.constant.ErrorLevel;
import com.nisum.common.constant.OrderStatus;
import com.nisum.common.exception.rest.RestException;
import com.nisum.common.util.DateUtils;
import com.nisum.shop.api.dto.ProductDTO;
import com.nisum.shop.constant.OrderItemInventoryStatus;
import com.nisum.shop.dto.CheckoutDTO;
import com.nisum.shop.model.Order;
import com.nisum.shop.model.OrderItem;
import com.nisum.shop.model.OrderTransaction;
import com.nisum.shop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * The class {@code CheckoutService} includes methods 
 * for checkout and place order functionality with inventory management and checking 
 * operations. 
 *  
 * @author Owais Majid
 *
 */

@Service
public class CheckoutServiceImpl implements CheckoutService {

	
	
	/** The order service. */
	@Autowired 
	private OrderService orderService;

	
	/** The email service. */
	@Autowired 
	private EmailService emailService;
	
	/** The transaction service. */
	@Autowired 
	private OrderTransactionService transactionService;
	
	@Autowired
	private ProductService productService;

	
	/* (non-Javadoc)
	 * @see com.nisum.service.CheckoutService#checkOut(long)
	 */
	@Override
	public CheckoutDTO checkOut(Long userId) {
		
		Order order = orderService.getActiveOrderByUserId(userId);
		
		if(order == null) 
			throw new RestException(HttpStatus.NOT_ACCEPTABLE, "Order does not exists.", ErrorLevel.ERROR);
		
		if(order.getOrderItems() == null || order.getOrderItems().size() <= 0)
			throw new RestException(HttpStatus.NOT_ACCEPTABLE, "Order does not have any line items.", ErrorLevel.ERROR);

		Map<String,ProductDTO> mapOfUnavailableItems = checkQuantity(order);
		
		CheckoutDTO checkoutDTO = new CheckoutDTO();
		checkoutDTO.setOrder(order);
		checkoutDTO.setMapOfUnavailableItems(mapOfUnavailableItems);
				
		return checkoutDTO;
	}
	
	/**
	 * Check quantity.
	 *
	 * @param order the order
	 * @return the map
	 */
	private Map<String,ProductDTO> checkQuantity(Order order){
		Map<String,ProductDTO> mapOfUnavailableItems = new HashMap<String,ProductDTO>();
		
		for(OrderItem orderItem : order.getOrderItems()) {
			
			ProductDTO product =productService.findAvailable(orderItem.getItemUUID());
			if(orderItem.getQuantity()>product.getItem().getQuantity()) {
				orderItem.setInventoryStatus(OrderItemInventoryStatus.OUT_OF_STOCK.getValue());
				
				mapOfUnavailableItems.put(product.getItem().getId(),product);
			}
		}
		
		//Update OrderItem table for inventory status
		orderService.update(order);
		
		return mapOfUnavailableItems;
	}

	/* (non-Javadoc)
	 * @see com.nisum.service.CheckoutService#placeOrder(com.nisum.model.OrderTransaction)
	 */
	@Override
	public OrderTransaction placeOrder(OrderTransaction orderTransaction) {
		Order order = orderTransaction.getOrders().iterator().next();
		OrderTransaction orderTransactionCreated = transactionService.saveTransaction(orderTransaction);
		
		order.setOrderStatus(OrderStatus.AWAITING_SHIPMENT.getValue());
		order.setUpdatedAt(DateUtils.getCurrentTime());
		order.setTransaction(orderTransactionCreated);
		
		//adjust inventory
		adjustInventory(order);
		
		orderService.update(order);
		emailService.sendOrderConfirmationEmail(order);
		 
		return orderTransactionCreated;
	}
	
	/**
	 * Adjust inventory.
	 *
	 * @param order the order
	 */
	private void adjustInventory(Order order) {

		//Adjust inventory accordingly
		//TODO SHOP: Adjust inverntory left
//		for(OrderItem orderItem : order.getOrderItems()) {
//			orderItem.getItem().setQuantity(orderItem.getItem().getQuantity()-orderItem.getQuantity());
//			if (orderItem.getItem().getQuantity().equals(0)) {
//				orderItem.getItem().setIsBuyable((byte) 0);
//			}
//		}
	}
	
}
