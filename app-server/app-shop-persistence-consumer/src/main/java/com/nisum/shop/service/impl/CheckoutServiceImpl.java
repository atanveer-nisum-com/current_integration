package com.nisum.shop.service.impl;


import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.common.constant.OrderStatus;
import com.nisum.common.shop.dto.TransactionDTO;
import com.nisum.common.util.BaseDTO;
import com.nisum.common.util.DateUtils;
import com.nisum.shop.model.Order;
import com.nisum.shop.model.Transaction;
import com.nisum.shop.service.CheckoutService;
import com.nisum.shop.service.EmailService;
import com.nisum.shop.service.OrderService;
import com.nisum.shop.service.TransactionService;
import com.nisum.shop.util.TransactionConverter;

/**
 * The class {@code CheckoutService} includes methods 
 * for checkout and place order functionality with inventory management and checking 
 * operations. 
 *  
 * @author Owais Majid
 *
 */

@Service
public class CheckoutServiceImpl implements CheckoutService {

	/** The order service. */
	@Autowired 
	private OrderService orderService;

	@Autowired
	private TransactionConverter transactionConverter;
	
	/** The transaction service. */
	@Autowired 
	private TransactionService transactionService;
	
	@Autowired
	private EmailService emailService;

	
	public Transaction placeOrder(TransactionDTO transactionDTO) {
		
		Set<Order> orderSet = new HashSet<Order>(); 
		Order activeOrder = orderService.getActiveOrderByUserId(transactionDTO.getUserId());
		orderSet.add(activeOrder);
		Transaction Transaction = transactionConverter.convertToEntity(transactionDTO);
		//Transaction.setUserId(transactionDTO.getUserId());
		Transaction.setOrders(orderSet);
		Order order = Transaction.getOrders().iterator().next();
		Transaction TransactionCreated = transactionService.saveTransaction(Transaction);	
		order.setOrderStatus(OrderStatus.AWAITING_SHIPMENT.getValue());
		order.setUpdatedAt(DateUtils.getCurrentTime());
		order.setTransaction(TransactionCreated);
		orderService.update(order);
		emailService.sendOrderConfirmationEmail(order);
		return TransactionCreated;
	}


	@Override
	public void save(BaseDTO baseDto) {
		
		if (baseDto instanceof TransactionDTO) {
			placeOrder((TransactionDTO) baseDto);
			
		}
		
	}


	@Override
	public void update(BaseDTO baseDTO) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(BaseDTO baseDTO) {
		// TODO Auto-generated method stub
		
	}
	

	
}
