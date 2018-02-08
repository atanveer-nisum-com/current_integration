package com.nisum.shop.service;

import com.nisum.common.service.BaseServiceLocator;
import com.nisum.common.shop.dto.TransactionDTO;
import com.nisum.shop.model.Transaction;

/**
 * The class {@code CheckoutService} includes methods 
 * for checkout and place order functionality with inventory management and checking 
 * operations. 
 *  
 * @author Owais Majid
 *
 */

public interface CheckoutService extends BaseServiceLocator{
	
	
	public Transaction placeOrder(TransactionDTO transactionDTO);
	

}
