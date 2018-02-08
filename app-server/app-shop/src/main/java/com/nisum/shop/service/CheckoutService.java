package com.nisum.shop.service;

import com.nisum.shop.dto.CheckoutDTO;
import com.nisum.shop.model.OrderTransaction;

/**
 * The class {@code CheckoutService} includes methods 
 * for checkout and place order functionality with inventory management and checking 
 * operations. 
 *  
 * @author Owais Majid
 *
 */

public interface CheckoutService {
	
	public CheckoutDTO checkOut(Long userId);
	public OrderTransaction placeOrder(OrderTransaction orderTransaction);
	

}
