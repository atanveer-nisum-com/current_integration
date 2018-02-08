package com.nisum.shop.query.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.nisum.cache.manager.EtaCacheManager;
import com.nisum.common.constant.ErrorLevel;
import com.nisum.common.constant.EventTypeEnum;
import com.nisum.common.event.message.dto.EtaEvent;
import com.nisum.common.exception.rest.RestException;
import com.nisum.shop.api.dto.ProductDTO;
import com.nisum.shop.dto.CartResponseDTO;
import com.nisum.shop.dto.TokenDTO;
import com.nisum.shop.model.Order;
import com.nisum.shop.query.service.CartQueryService;


@Service
public class CartQueryServiceImpl implements CartQueryService {

	@Autowired
	EtaCacheManager cacheManager;
	
	@Autowired
	Gson gson;
	
	@Override
	public CartResponseDTO getCartResponse(Long userId, String orderId) {
		if(userId == null){
			throw new RestException(HttpStatus.NO_CONTENT, "user id is not found", ErrorLevel.ERROR);
		}
		
		String key = userId.toString() +"-"+ orderId+"-" +EventTypeEnum.CART_EVENT.getKey();
		EtaEvent eventDto = gson.fromJson(cacheManager.readFromCache(key), EtaEvent.class);
		Order order = (Order) gson.fromJson(eventDto.getPayload(), Order.class);
		if(order == null)
			throw new RestException(HttpStatus.NO_CONTENT, "No order is placed againt user", ErrorLevel.ERROR);

		List<String> orderItemSet = order.getItems();
		CartResponseDTO cartDto = createCartDTO(order, orderItemSet, null, null);
		
		return cartDto;
	}
	
	private CartResponseDTO createCartDTO(Order order, List<String> orderItemSet, List<ProductDTO> productDTOList,TokenDTO token) {
		CartResponseDTO cartDto = new CartResponseDTO();
		if(token!=null)
			cartDto.setUserToken(token);
		cartDto.setId(order.getId());
		cartDto.setItems(orderItemSet);
		return cartDto;
	}

}
