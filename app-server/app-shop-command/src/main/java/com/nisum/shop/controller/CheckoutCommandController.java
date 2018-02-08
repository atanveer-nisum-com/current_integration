package com.nisum.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.common.shop.dto.RegistrationDTO;
import com.nisum.common.shop.dto.TransactionDTO;
import com.nisum.shop.service.CheckoutCommandService;
import com.nisum.shop.service.UserCommandService;

@RestController	
@RequestMapping("shop")
public class CheckoutCommandController extends BaseController{
	
	@Autowired
	private CheckoutCommandService checkoutServiceC;
	
	@Autowired
	private UserCommandService userServiceC;
	
	@PostMapping("/placeorder")
	public String placeOrder(@RequestBody TransactionDTO transactionDTO) {
		return checkoutServiceC.placeOrder(transactionDTO);		 
	}
	
	@PostMapping("/saveuser")
	public String saveUser(@RequestBody RegistrationDTO registrationDTO) {
		 return userServiceC.update(registrationDTO);		
	}

}

