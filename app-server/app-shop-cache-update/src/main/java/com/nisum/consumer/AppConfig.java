package com.nisum.consumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.client.RestTemplate;

/**
 * The Class AppConfig.   endpoints.properties
 */
@Configuration
@ComponentScan(basePackages="com.nisum")
@PropertySources({ @PropertySource("classpath:application.properties")})
public class AppConfig {
	
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
