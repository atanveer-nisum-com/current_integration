/**
 * 
 */
package com.nisum.shop.service;

import com.nisum.shop.model.OrderTransaction;

import java.util.List;

/**
 * @author omkhan
 *
 */
public interface OrderTransactionService {

	public OrderTransaction findOne(Long transactionId);
	public OrderTransaction findTransactionByUserId(Long userId);
	public OrderTransaction saveTransaction(OrderTransaction transaction);
	public List<OrderTransaction> findAllTransactions();
	public Boolean deleteTransaction(OrderTransaction transaction);
	public Boolean deleteTransaction(Long transactionId);
}
