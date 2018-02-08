package com.nisum.shop.service;

import com.nisum.common.shop.dto.TransactionDTO;


public interface CheckoutCommandService {

	public String placeOrder(TransactionDTO transactionDTO);
}
