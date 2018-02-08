package com.nisum.shop.service;

import com.nisum.shop.model.Order;

public interface OrderService {
	public Order getActiveOrderByUserId(Long userId);
	public Order update(Order order);
}
