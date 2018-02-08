package com.nisum.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.common.constant.EventTypeEnum;
import com.nisum.common.event.message.dto.EtaEvent;
import com.nisum.common.shop.dto.RegistrationDTO;
import com.nisum.common.util.Utils;
import com.nisum.shop.kafka.producer.ShopEventProducer;
import com.nisum.shop.service.UserCommandService;

@Service
public class UserCommandServiceImpl implements UserCommandService {

	@Autowired
	ShopEventProducer shopEventProducer;
	
	@Override
	public String update(RegistrationDTO registrationDTO) {
		EtaEvent checkOutEvent = new EtaEvent(RegistrationDTO.class,EventTypeEnum.CHECKOUT_EVENT,Utils.convertObjectToJson(registrationDTO),EventTypeEnum.UPDATE_USER);
		shopEventProducer.produceEvent(Utils.convertObjectToJson(checkOutEvent));		
		return "Information Processed Successfully";
	}

}
