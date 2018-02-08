package com.nisum.user.test.controller;

import com.nisum.common.constant.AppConstant;
import com.nisum.common.constant.CommonEndPointConstant;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The Class BaseControllerTest.
 */
@RunWith(SpringRunner.class)
@EnableSpringDataWebSupport
@ActiveProfiles("test")
public abstract class BaseControllerTest {
	
	/**  Main entry point for server-side. */
	@Autowired protected MockMvc mvc;
	
	@Configuration
    static class ContextConfiguration {

        // this bean will be injected into the OrderServiceTest class
        @Bean
        public ExecutorService executorService() {
        	ExecutorService executorService = Executors.newFixedThreadPool(AppConstant.THREADS_COUNT);
    		return executorService;
        }
        @Bean
        public RestTemplate restTemplate() {
    		return new RestTemplate();
    	}
        @Bean
    	public ModelMapper modelMapper() {
    	    return new ModelMapper();
    	}
        
        @Bean
    	public CommonEndPointConstant endPointConstant() {
    	    return new CommonEndPointConstant();
    	}
    }
	@Autowired
	 public ExecutorService executorService;
	@Autowired
	public RestTemplate restTemplate;
	@Autowired 
	public ModelMapper modelMapper;
	
	@Autowired
	public CommonEndPointConstant endPointConstant;
	
	
	/** The Constant PAGE_SIZE_12. */
	final static protected int PAGE_SIZE_12 = 12;

	protected static final String BASE_URL_APP_USER = "/user";
	protected static final String BASE_URL_APP_USERS = "/users";
}
