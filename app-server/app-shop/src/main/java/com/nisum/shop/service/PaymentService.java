package com.nisum.shop.service;

import com.nisum.shop.dto.AddressDTO;
import com.nisum.shop.model.Order;
import com.paypal.base.rest.PayPalRESTException;

public interface PaymentService {

	public String payWithPaypal(String paymentId, String payerId);
	public String createPaypalPayment(Order order, AddressDTO shippingAddress) throws PayPalRESTException ;
}
