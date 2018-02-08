/**
 * 
 */
package com.nisum.shop.repository;

import com.nisum.shop.model.OrderTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



/**
 * @author omkhan
 *
 */
public interface TransactionRepository extends JpaRepository<OrderTransaction, Long> {

	@Query("SELECT distinct pp FROM OrderTransaction pp JOIN pp.user u WHERE u.id = ?1 ")
	public OrderTransaction findTransactionByUserId(Long userId);

	
}
