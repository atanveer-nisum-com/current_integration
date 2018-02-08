/**
 *
 */
package com.nisum.shop.repository;

import com.nisum.shop.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 * @author Nisum Pakistan
 *
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	@Query("SELECT distinct pp FROM OrderTransaction pp JOIN pp.user u WHERE u.id = ?1 ")
	public Transaction findTransactionByUserId(Long userId);


}
