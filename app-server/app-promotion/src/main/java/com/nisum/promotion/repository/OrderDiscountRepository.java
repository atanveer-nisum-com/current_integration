/**
 * 
 */
package com.nisum.promotion.repository;

import com.nisum.promotion.model.OrderAmountDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Nisum Pakistan
 * 
 * The interface OrderDiscountRepository
 */

public interface OrderDiscountRepository extends JpaRepository<OrderAmountDiscount, Long>{
	
	/**
	 * Get All active order discounts.
	 * @return List<OrderAmountDiscount>
	 */
	
	@Query("SELECT od FROM OrderAmountDiscount od where od.active=1")
	public List<OrderAmountDiscount> getAllActiveDiscounts();
	
	
}
