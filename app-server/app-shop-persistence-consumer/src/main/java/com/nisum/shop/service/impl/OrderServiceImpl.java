package com.nisum.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.nisum.common.constant.EventTypeEnum;
import com.nisum.common.service.CacheService;
import com.nisum.shop.model.Order;
import com.nisum.shop.repository.OrderRepository;
import com.nisum.shop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	CacheService cacheService;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	Gson gson;
	
	@Override
	public Order getActiveOrderByUserId(Long userId) {
		String key = userId.toString() +EventTypeEnum.CART_EVENT.getKey();
		String orderString = cacheService.getFromCache(key);
		Order order = null; 
		order = gson.fromJson(orderString, Order.class);
		
		if(order == null ){
			order = new Order();
		}
		return order;
	}
	@Override
	public Order update(Order order) {
		return orderRepository.save(order);
	}
}
