package com.nisum.shop.query.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.shop.dto.CartResponseDTO;
import com.nisum.shop.query.service.CartQueryService;

/**
 * The Class CartCommandController.
 */
@RestController
@RequestMapping("api/v2/cart")
public class CartQueryController extends BaseController{

	@Autowired
	private CartQueryService cartQueryService;
	
	
	@GetMapping("/getCart/{userId}/{orderId}")
	public CartResponseDTO getCartResponse(@PathVariable("userId") Long userId, @PathVariable("orderId") String orderId) {
		return this.cartQueryService.getCartResponse(userId, orderId);
	}
}
