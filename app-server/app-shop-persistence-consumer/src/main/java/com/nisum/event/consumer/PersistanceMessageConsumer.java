package com.nisum.event.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.nisum.common.constant.EventTypeEnum;
import com.nisum.common.event.message.dto.EtaEvent;
import com.nisum.common.message.consumer.BaseMessageConsumer;
import com.nisum.common.service.BaseServiceLocator;
import com.nisum.common.shop.controller.dto.CartLineItem;
import com.nisum.common.shop.dto.TransactionDTO;
import com.nisum.common.util.BaseDTO;
import com.nisum.event.listener.EventListener;
import com.nisum.shop.service.CheckoutService;
import com.nisum.shop.service.impl.OrderPersistenceServiceImpl;

public class PersistanceMessageConsumer implements BaseMessageConsumer, EventListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersistanceMessageConsumer.class);
	@Autowired
	CheckoutService checoutService;

	@Override
	public void processEvent(EtaEvent event) {

		Gson gson = new Gson();
		BaseServiceLocator baseServiceLocator = null;
		BaseDTO dto = null;
		if (event.getEventType().equals(EventTypeEnum.CART_EVENT.getValue())) {

			switch (event.getObjectType().toUpperCase()) {

			case "ADDTOCART":

				dto = gson.fromJson(event.getPayload(), CartLineItem.class);
				baseServiceLocator = new OrderPersistenceServiceImpl();

				break;

			case "UPDATECART":

				break;

			// to-do more events need to be added here !!
			}
			baseServiceLocator.save(dto);
		} else if (event.getEventType().equals(EventTypeEnum.CHECKOUT_EVENT.getValue())) {
			switch (event.getActionType().toUpperCase()) {

			case "PLACE_ORDER":

				dto = gson.fromJson(event.getPayload(), TransactionDTO.class);
				checoutService.save(dto);
				break;
			}
		}
	}

	@Override
	public void acceptEvent(Object eventPayload) {
		LOGGER.info("received by cache updater consumer where payload='{}'", eventPayload);

		Gson gson = new Gson();

		EtaEvent etaEvent = gson.fromJson(eventPayload.toString(), EtaEvent.class);

		processEvent(etaEvent);
	}

}
