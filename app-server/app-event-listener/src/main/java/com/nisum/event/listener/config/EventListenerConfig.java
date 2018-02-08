package com.nisum.event.listener.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

/**
 * 
 * @author brazzaq
 *
 */
@Configuration
@PropertySources({ @PropertySource("classpath:/event-listener-server.properties"),
		@PropertySource("classpath:/application.properties") })
public class EventListenerConfig {

	@Autowired
	private Environment environment;

	private Map<String, Object> propertyMap = new ConcurrentHashMap<String, Object>(0);

	@PostConstruct
	private void loadPropertyMap() {
		this.loadConsumerSettings();
	}

	private void loadConsumerSettings(){
		this.propertyMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.getEventServerAddress());
		this.propertyMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class/*this.getProperty("key.deserializer.class.config")*/);
        this.propertyMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class/*this.getProperty("value.deserializer.class.config")*/);
        this.propertyMap.put(ConsumerConfig.GROUP_ID_CONFIG, "GROUP1");
	}

	public String getProperty(String key) {
		return environment.getProperty(key);
	}

	public String getEventServerAddress() {
		return this.getProperty("EVENT.SERVER.ADDRESS");
	}

	public Environment getEnvironmentSettings() {
		return this.environment;
	}

	public Map<String, Object> getConfigurationProperties() {
		return this.propertyMap;
	}

	public String getTopicName() {
		return this.getProperty("EVENT.LISTENER.TOPIC");
	}
}
