package com.nisum.shop.service.impl;

import com.nisum.common.constant.AppConstant;
import com.nisum.shop.dto.AddressDTO;
import com.nisum.shop.model.Order;
import com.nisum.shop.service.PaymentService;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.util.ArrayList;

@Service
public class PaymentServiceImpl implements PaymentService {

	public String payWithPaypal(String paymentId, String payerId) {
		String responseFromPaypal = null;

		APIContext context = new APIContext(AppConstant.PAYPAL_KEY, AppConstant.PAYPAL_SECRET, AppConstant.PAYPAL_ENV);

		try {

			Payment payment = Payment.get(context, paymentId);
			PaymentExecution paymentExecution = new PaymentExecution();
			paymentExecution.setPayerId(payerId);

			responseFromPaypal = payment.execute(context, paymentExecution).toJSON();

		} catch (PayPalRESTException e) {
			e.printStackTrace();
		}

		return responseFromPaypal;
	}

	@Override
	public String createPaypalPayment(Order order, AddressDTO shippingAddressDTO) throws PayPalRESTException {

		String responseFromPaypal = null;

		APIContext context = new APIContext(AppConstant.PAYPAL_KEY, AppConstant.PAYPAL_SECRET, AppConstant.PAYPAL_ENV);

		Payment payment = new Payment();

		payment.setIntent("sale");

		RedirectUrls redirect = new RedirectUrls();
		redirect.setCancelUrl("https://www.sandbox.paypal.com/webscr?cmd=_express-checkout&useraction=cancel&token=");
		redirect.setReturnUrl("https://www.sandbox.paypal.com/webscr?cmd=_express-checkout&useraction=commit&token=");

		payment.setRedirectUrls(redirect);

		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");

		payment.setPayer(payer);

		Transaction transaction = new Transaction();

		Amount amount = new Amount("USD", order.getOrderTotalPrice().setScale(2, RoundingMode.CEILING).toString());
		Details details = new Details();
		details.setSubtotal(order.getSubTotalPrice().toString());
		details.setShipping("1.00");
		details.setTax(order.getTaxation().setScale(2, RoundingMode.CEILING).toString());
		details.setShippingDiscount("-1.00");
		amount.setDetails(details);

		transaction.setAmount(amount);

		ArrayList<Item> items = new ArrayList<>();

		ItemList itemList = new ItemList();
		if (shippingAddressDTO != null && shippingAddressDTO.getCountryBean().getId() == 230) {
			ShippingAddress shippingAddress = new ShippingAddress();

			String addressLine1 = null;
			String addressLine2 = null;
			String city = null;
			String country = null;
			String state = null;
			String phoneNumber = null;
			String postalCode = null;

			addressLine1 = shippingAddressDTO.getAddressLine1();
			addressLine2 = shippingAddressDTO.getAddressLine2();
			city = shippingAddressDTO.getCity();
			country = shippingAddressDTO.getCountryBean().getName();
			phoneNumber = shippingAddressDTO.getPhoneNumber();
			state = shippingAddressDTO.getStateBean().getName();
			postalCode = shippingAddressDTO.getZipcode();

			shippingAddress.setCity(city);
			shippingAddress.setCountryCode(country);
			shippingAddress.setLine1(addressLine1);
			shippingAddress.setLine2(addressLine2);
			shippingAddress.setState(state);
			shippingAddress.setPhone(phoneNumber);
			shippingAddress.setPostalCode(postalCode);

			itemList.setShippingAddress(shippingAddress);
		}
		itemList.setItems(items);

		transaction.setItemList(itemList);
		transaction.setCustom("this is custom");
		transaction.setDescription("This is Description");

		ArrayList<Transaction> transactions = new ArrayList<>();
		transactions.add(transaction);

		payment.setTransactions(transactions);

		responseFromPaypal = payment.create(context).toJSON();
		return responseFromPaypal;
	}
}
