package com.nisum.event.listener.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.nisum.event.listener.EventListener;

@Component
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class EventConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventConsumer.class);
	
	@Autowired
	private EventListener eventListener;
	
	
	  @KafkaListener(topics = "${EVENT.LISTENER.TOPIC}")
	  public void onMessage(Object payload) {
		LOGGER.info("Event Recieved where payload='{}'", payload);
		if(this.eventListener !=null)
			this.eventListener.acceptEvent(payload);  
	  }
}

