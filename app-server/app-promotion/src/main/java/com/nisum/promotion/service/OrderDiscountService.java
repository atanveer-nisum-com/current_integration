/**
 * 
 */
package com.nisum.promotion.service;

import com.nisum.promotion.model.OrderAmountDiscount;

import java.util.List;
/**
 * @author Nisum Pakistan
 *
 *The Interface OrderDiscountService
 */
public interface OrderDiscountService {

	/**
	 * Get all active order discounts
	 * @return List<OrderAmountDiscount>
	 */
	public List<OrderAmountDiscount>getAllActiveDiscounts();
	
	/**
	 * Get order discount 
	 * @param orderAmount
	 * @return Double # OrderDiscount
	 */
	public Double getOrderDiscount(Double orderAmount);
	
	
}
