package com.nisum.shop;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

/**
 * The Class AppConfig.
 */
@Configuration
@ComponentScan(basePackages="com.nisum")
@EnableAutoConfiguration
@EnableJpaRepositories("com.nisum.common.shop")
@EntityScan("com.nisum.common.shop")
public class ApplicationConfig {
	
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
