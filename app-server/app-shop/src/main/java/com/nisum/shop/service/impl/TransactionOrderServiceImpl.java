/**
 * 
 */
package com.nisum.shop.service.impl;

import com.nisum.common.util.DateUtils;
import com.nisum.shop.model.OrderTransaction;
import com.nisum.shop.repository.TransactionRepository;
import com.nisum.shop.service.OrderTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author omkhan
 *
 */
@Service
public class TransactionOrderServiceImpl implements OrderTransactionService{
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public OrderTransaction findOne(Long transactionId) {
		return transactionRepository.findOne(transactionId);
	}

	@Override
	public OrderTransaction findTransactionByUserId(Long userId) {

		return transactionRepository.findTransactionByUserId(userId);
	}

	/* (non-Javadoc)
	 * @see com.nisum.service.OrderTransactionService#saveTransaction(com.nisum.model.OrderTransaction)
	 */
	@Override
	public OrderTransaction saveTransaction(OrderTransaction transaction) {
		
		if(transaction.getId()==null)//i.e. a new transaction
			transaction.setTransactionTime(DateUtils.getCurrentTime());
		else //i.e. this is an existing order so update the time
			transaction.setUpdatedAt(DateUtils.getCurrentTime());
		return transactionRepository.save(transaction);
	}

	/* (non-Javadoc)
	 * @see com.nisum.service.OrderTransactionService#findAllTransactions()
	 */
	@Override
	public List<OrderTransaction> findAllTransactions() {
		return transactionRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.nisum.service.OrderTransactionService#deleteTransaction(com.nisum.model.OrderTransaction)
	 */
	@Override
	public Boolean deleteTransaction(OrderTransaction transaction) {
		transactionRepository.delete(transaction);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.nisum.service.OrderTransactionService#deleteTransaction(java.lang.Long)
	 */
	@Override
	public Boolean deleteTransaction(Long transactionId) {
		transactionRepository.delete(transactionId);
		return true;
	}

}
