package com.nisum.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nisum.common.constant.EventTypeEnum;
import com.nisum.common.event.message.dto.EtaEvent;
import com.nisum.common.shop.controller.dto.CartLineItem;
import com.nisum.common.shop.dto.ItemAddToCartDTO;
import com.nisum.common.util.Utils;
import com.nisum.shop.kafka.producer.ShopEventProducer;
import com.nisum.shop.service.OrderCommandService;

/**
 * 
 * @author omussawir
 *
 */
@Component
public class OrderCommandServiceImpl implements OrderCommandService {
	
	@Autowired
	ShopEventProducer eventProducer1;  
	
	@Override
	public String save(Long userId, String itemId){
		CartLineItem lineItem = new CartLineItem();
		lineItem.setItemId(itemId);
		lineItem.setUserId(userId);
		EtaEvent event = new EtaEvent(CartLineItem.class, EventTypeEnum.CART_EVENT, Utils.convertObjectToJson(lineItem),EventTypeEnum.ADD_TO_CART);
		eventProducer1.produceEvent(Utils.convertObjectToJson(event));
		return "Information Processed Successfully";
	}

	@Override
	public String updateOrderItem(ItemAddToCartDTO item) {
		EtaEvent event = new EtaEvent(ItemAddToCartDTO.class ,EventTypeEnum.CART_EVENT,Utils.convertObjectToJson(item),EventTypeEnum.ADD_TO_CART);
		eventProducer1.produceEvent(Utils.convertObjectToJson(event));
		return "Information Processed Successfully";
	}

	@Override
	public String removeOrderItemReturnDTO(Long orderitemid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save(String userId, String itemId) {
		// TODO Auto-generated method stub
		return null;
	}
}
