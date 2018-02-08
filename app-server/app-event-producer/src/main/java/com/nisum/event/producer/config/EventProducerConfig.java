package com.nisum.event.producer.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
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
@PropertySources({
        @PropertySource("classpath:/event-producer-server.properties"),
        @PropertySource("classpath:/application.properties")
})
public class EventProducerConfig {
	
	@Autowired
	private Environment environment;
	
	private Map<String, Object> propertyMap=new ConcurrentHashMap<String, Object>(0);
	
	@PostConstruct
	private void loadPropertyMap(){
        this.loadProducerSettings();
	}
	private void loadProducerSettings(){
		this.propertyMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.getEventServerAddress());
        this.propertyMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class/*this.getProperty("key.serializer.class.config")*/);
        this.propertyMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class/*this.getProperty("value.serializer.class.config")*/);
	}
	public String getProperty(String key){
	    return environment.getProperty(key);
	}
	
	public String getEventServerAddress(){
		return this.getProperty("EVENT.SERVER.ADDRESS");
	}
	
	public Environment getEnvironmentSettings(){
		return this.environment;
	}
	
	public Map<String, Object> getConfigurationProperties(){
		return this.propertyMap;
	}
}
