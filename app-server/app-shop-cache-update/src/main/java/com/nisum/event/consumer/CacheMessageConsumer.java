package com.nisum.event.consumer;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.nisum.common.constant.EventTypeEnum;
import com.nisum.common.event.message.dto.EtaEvent;
import com.nisum.common.message.consumer.BaseMessageConsumer;
import com.nisum.common.service.BaseServiceLocator;
import com.nisum.common.shop.controller.dto.CartLineItem;
import com.nisum.common.util.BaseDTO;
import com.nisum.event.listener.EventListener;
import com.nisum.shop.service.OrderServiceCache;
import com.nisum.shop.service.impl.OrderServiceCacheImpl;

@Component
public class CacheMessageConsumer implements BaseMessageConsumer, EventListener {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CacheMessageConsumer.class);
	@Autowired
	OrderServiceCache orderServiceCache;
	@Override
	public void processEvent(EtaEvent event){
		Gson gson = new Gson();
		BaseServiceLocator baseServiceLocator = null;
		BaseDTO dto  = null;
		if(event.getEventType().toUpperCase().equals(EventTypeEnum.CART_EVENT.getKey())){
		
			switch(event.getActionType().toUpperCase()){
			
			case "ADD_TO_CART":
			{
			dto =  gson.fromJson(event.getPayload(), CartLineItem.class);		
			//baseServiceLocator = new OrderServiceCacheImpl();
			
			//baseServiceLocator.save(dto);
			orderServiceCache.save(dto);
			break;
			}
			
			case "UPDATE_CART":
			{
				dto =  gson.fromJson(event.getPayload(), CartLineItem.class);		
				baseServiceLocator = new OrderServiceCacheImpl();
				baseServiceLocator.save(dto);	
			break;
			}
			//to-do more events need to be added here !!
			}
		//	baseServiceLocator.save(dto);
		}else{
			
		}
	
	}

	@Override
	public void acceptEvent(Object eventPayload) {
		LOGGER.info("received by cache updater consumer where payload='{}'", eventPayload);
		
		Gson gson = new Gson();
		
		System.out.println("payload:  "+eventPayload);
		//
		@SuppressWarnings("unchecked")
		ConsumerRecord<Long,String> record = (ConsumerRecord<Long,String>)eventPayload;
		EtaEvent etaEvent =  gson.fromJson(record.value(), EtaEvent.class);
		processEvent(etaEvent);
		
	}
}
