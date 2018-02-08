package com.nisum.shop;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * The Class AppConfig.
 */
@Configuration
@ComponentScan(basePackages="com.nisum")
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
    
    @Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
    
}
