package com.nisum.event.producer.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Component;

import com.nisum.event.producer.EventProducer;
import com.nisum.event.producer.config.EventProducerConfig;

/**
 * 
 * @author brazzaq
 *
 */
@Component

public class EventProducerFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventProducerFactory.class);

	@Autowired
	private EventProducerConfig eventConfig;

	private Map<Object, String> eventProducerContext = new ConcurrentHashMap<Object, String>(
			0);

	@Autowired
	private KafkaTemplate<Object, Object> kafkaTemplate;
	
	
	@Bean
	public ProducerFactory<Object, Object> producerFactory() {
		return new DefaultKafkaProducerFactory<>(this.eventConfig.getConfigurationProperties());
	}
	@Bean
	public KafkaTemplate<Object, Object> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}
	

	public void registerProducer(EventProducer eventProducerClass, String topic) {
		if (eventProducerClass != null && topic != null && !topic.trim().equals("")) {
			
			
		    
			if (this.eventProducerContext.get(eventProducerClass) != null) {
				LOGGER.error("Already Registered Event Producer='{}' to topic='{}'", eventProducerClass, topic,
						new Exception("EventProducer " + eventProducerClass + " already regisered."));
				return;
			}
			try {
				this.eventProducerContext.put((EventProducer)((Advised)eventProducerClass).getTargetSource().getTarget(), topic);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			LOGGER.info("EventProducer Registered='{}' to topic='{}'", eventProducerClass, topic);
		} else {
			LOGGER.error("Error while Registering Event Producer='{}' to topic='{}'", eventProducerClass, topic,
					new Exception("Invalid EventProducer Registration Parameters"));
			return;
		}

	}

	public String getTopicName(EventProducer eventProducerClass) {
		return this.eventProducerContext.get(eventProducerClass);
	}

	public void produceEvent(EventProducer eventProducerClass,Object payload) {
		this.kafkaTemplate.send(this.getTopicName(eventProducerClass), payload);
		LOGGER.info("Event Produced by='{}' to topic='{}'", eventProducerClass, this.getTopicName(eventProducerClass));
		
	}
}
