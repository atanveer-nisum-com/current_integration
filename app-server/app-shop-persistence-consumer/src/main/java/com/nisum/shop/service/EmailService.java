package com.nisum.shop.service;

import com.nisum.shop.model.Order;

public interface EmailService {

	/**
	 * Send order confirmation email.
	 *
	 * @param order the order
	 */
	public void sendOrderConfirmationEmail(Order order);
	
}
