package com.nisum.shop.api.controller;

import com.nisum.shop.model.Order;
import com.nisum.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("apiOrderController")
@RequestMapping("api/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@GetMapping("/merge/guest/{guestId}/user/{userId}")
	public Order mergeCartFromGuestUser(@PathVariable("guestId") Long guestId, @PathVariable("userId") Long userId) {
		return orderService.mergeCartFromGuestUser(guestId, userId);
	}

}
