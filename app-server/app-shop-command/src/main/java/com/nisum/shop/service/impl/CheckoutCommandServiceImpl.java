package com.nisum.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.common.constant.EventTypeEnum;
import com.nisum.common.event.message.dto.EtaEvent;
import com.nisum.common.shop.dto.TransactionDTO;
import com.nisum.common.util.Utils;
import com.nisum.shop.kafka.producer.ShopEventProducer;
import com.nisum.shop.service.CheckoutCommandService;

@Service
public class CheckoutCommandServiceImpl implements CheckoutCommandService {

	@Autowired
	ShopEventProducer ShopEventProducer;

	@Override
	public String placeOrder(TransactionDTO transactionDTO) {
		EtaEvent checkOutEvent = new EtaEvent(TransactionDTO.class,EventTypeEnum.CHECKOUT_EVENT,Utils.convertObjectToJson(transactionDTO),EventTypeEnum.PLACE_ORDER);
		ShopEventProducer.produceEvent(Utils.convertObjectToJson(checkOutEvent));
		return "Information Processed Successfully";
	}

}
