package com.nisum.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.nisum.common.shop.controller.dto.CartLineItem;
import com.nisum.shop.kafka.producer.ShopEventProducer;
import com.nisum.shop.service.CartCommandService;


@Service
public class CartCommandServiceImpl implements CartCommandService {

	@Autowired
	ShopEventProducer eventProducer1;
	
	@Override
	public void addToCart(String itemName) {
		eventProducer1.produceEvent(itemName +" From Producer 1");
	}

	@Override
	public void addToCart(Long userId, String itemId) {
		CartLineItem lineItem = new CartLineItem();
		lineItem.setItemId(itemId);
		lineItem.setUserId(userId);
		Gson gson = new Gson();
		gson.toJson(lineItem);
		eventProducer1.produceEvent(gson.toJson(lineItem));
	}

}
