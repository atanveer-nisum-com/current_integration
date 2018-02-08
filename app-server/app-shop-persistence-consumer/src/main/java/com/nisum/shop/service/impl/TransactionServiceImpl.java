package com.nisum.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.nisum.common.util.DateUtils;
import com.nisum.shop.model.Transaction;
import com.nisum.shop.repository.TransactionRepository;
import com.nisum.shop.service.TransactionService;

public class TransactionServiceImpl implements TransactionService{

	@Autowired
	TransactionRepository transactionRepository;
	
	@Override
	public Transaction saveTransaction(Transaction transaction) {
		
		if(transaction.getId()==null)//i.e. a new transaction
			transaction.setTransactionTime(DateUtils.getCurrentTime());
		else //i.e. this is an existing order so update the time
			transaction.setUpdatedAt(DateUtils.getCurrentTime());
		return transactionRepository.save(transaction);
	}

}
