package com.nisum.shop;

import com.nisum.event.producer.factory.EventProducerFactory;
import com.nisum.shop.kafka.producer.ShopEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The Class AppConfig.
 */
@Configuration
@ComponentScan(basePackages="com.nisum")
public class AppConfig {
	
	@Autowired
	EventProducerFactory eventProducerFactory;
	
	@Autowired
	ShopEventProducer eventProducer1;
	
	@PostConstruct
	private void init(){
		this.eventProducerFactory.registerProducer(eventProducer1, "TOPIC.1");
	}
	
    /**
     * Executor service.
     *
     * @return the executor service
     */
    @Bean
    public ExecutorService executorService(){
    	ExecutorService executorService = Executors.newFixedThreadPool(100);
    	return executorService;		
    }

    @Bean
    public RestTemplate restClient() {
        return new RestTemplate();
    }
    
    
}
