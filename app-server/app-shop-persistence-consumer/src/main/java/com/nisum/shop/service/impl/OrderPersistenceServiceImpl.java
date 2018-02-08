package com.nisum.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.nisum.common.constant.EventTypeEnum;
import com.nisum.common.event.message.dto.EtaEvent;
import com.nisum.common.service.SaveCacheService;
import com.nisum.common.util.BaseDTO;
import com.nisum.shop.model.Order;
import com.nisum.shop.repository.OrderRepository;
import com.nisum.shop.service.OrderPersistenceService;

public class OrderPersistenceServiceImpl implements OrderPersistenceService {

	@Autowired
	SaveCacheService cacheRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	Gson gson;

	@Override
	public Order getActiveOrderByUserId(Long userId) {
		String key = userId.toString() + EventTypeEnum.CART_EVENT.getKey();
		String orderString = cacheRepository.getFromCache(key);
		Order order = null;
		EtaEvent eventDto = gson.fromJson(orderString, EtaEvent.class);

		if (eventDto == null) {
			order = new Order();
		} else {
			order = gson.fromJson(eventDto.getPayload(), Order.class);
		}

		return order;
	}

	@Override
	public Order updateRepository(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public Order getFromCache(String cacheOrderKey) {
		//TODO: to be implemented
		return null;
	}


	@Override
	public void save(BaseDTO baseDTO) {
		//TODO: to be implemented
	}

	@Override
	public void update(BaseDTO baseDTO) {
		//TODO: to be implemented
	}

	@Override
	public void delete(BaseDTO baseDTO) {
		//TODO: to be implemented
	}
}
