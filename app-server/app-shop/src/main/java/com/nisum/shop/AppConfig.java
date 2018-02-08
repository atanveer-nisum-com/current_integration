package com.nisum.shop;

import com.nisum.common.constant.AppConstant;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    	ExecutorService executorService = Executors.newFixedThreadPool(AppConstant.THREADS_COUNT);
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
