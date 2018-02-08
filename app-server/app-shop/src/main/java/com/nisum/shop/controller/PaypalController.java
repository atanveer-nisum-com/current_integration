package com.nisum.shop.controller;

import com.nisum.shop.dto.AddressDTO;
import com.nisum.shop.dto.RegistrationDTO;
import com.nisum.shop.model.Order;
import com.nisum.shop.model.State;
import com.nisum.shop.service.CountryService;
import com.nisum.shop.service.OrderService;
import com.nisum.shop.service.PaymentService;
import com.nisum.shop.service.StateService;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The Class PaypalController.
 */
@RestController
@RequestMapping("paypal")
public class PaypalController extends BaseController {

	/** The payment service. */
	@Autowired
	private PaymentService paymentService;

	/** The order service. */
	@Autowired
	private OrderService orderService;

	/** The state service. */
	@Autowired
	private StateService stateService;

	/** The country service. */
	@Autowired
	private CountryService countryService;

	/**
	 * Execute payment.
	 *
	 * @param paymentId
	 *            the payment id
	 * @param payerId
	 *            the payer id
	 * @return the string
	 */
	@PostMapping("/execute")
	public String executePayment(String paymentId, String payerId) {
		return paymentService.payWithPaypal(paymentId, payerId);
	}

	/**
	 * Creates the payment.
	 *
	 * @param registrationDTO
	 *            the registration DTO
	 * @return the string
	 */
	@PostMapping("/create")
	public String createPayment(@RequestBody RegistrationDTO registrationDTO) {
		String responseFromPaypal = null;

		List<AddressDTO> tmpAddresses = registrationDTO.getAddresses();

		for (AddressDTO address : tmpAddresses) {
			if (address.getStateBean() != null && address.getStateBean().getId() != null) {
				State state = stateService.findStateById((long) address.getStateBean().getId());
				address.getStateBean().setName(state.getAbbreviation());
			}
		}

		Order order = orderService.getActiveOrderByUserId(registrationDTO.getUserId());

		List<AddressDTO> addresses = registrationDTO.getAddresses();
		String countryState = null;
		AddressDTO shippingAddress = null;

		if (addresses != null) {

			for (AddressDTO address : addresses) {
				byte addressType = address.getAddressType();
				shippingAddress = address;

				// If Address Type is of Shipping Type
				if (addressType == 1) {
					countryState = address.getStateBean().getName();
					long countryId = address.getCountryBean().getId().longValue();
					String countryAbbrevation = countryService.findById(countryId).getAbbreviation();
					shippingAddress.getCountryBean().setName(countryAbbrevation);
				}
			}
				order = orderService.updateTaxationPrice(order.getId(), countryState);
		}

		try {

			responseFromPaypal = paymentService.createPaypalPayment(order, shippingAddress);

		} catch (PayPalRESTException e) {
			System.err.println(e.getDetails());
		}

		return responseFromPaypal;
	}

}
