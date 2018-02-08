package com.nisum.event.producer;

import org.springframework.beans.factory.annotation.Autowired;

import com.nisum.event.producer.factory.EventProducerFactory;

/**
 * 
 * @author brazzaq
 */
public abstract class EventProducer{
	
	@Autowired
	EventProducerFactory  eventProducerFactory;
	
	public void produceEvent(Object payload){
		this.eventProducerFactory.produceEvent(this,payload);
	}
}
