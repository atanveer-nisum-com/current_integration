package com.nisum.promotion.test.controller;

import com.nisum.common.constant.AppConstant;
import com.nisum.common.constant.CommonEndPointConstant;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@RunWith(SpringRunner.class)
@EnableSpringDataWebSupport
@ActiveProfiles("test")
public abstract  class BaseControllerTest {

	/**  Main entry point for server-side. */
	@Autowired 
	protected MockMvc mvc;


	@Configuration
	static class ContextConfiguration {

		// this bean will be injected into the OrderServiceTest class
		@Bean
		public ExecutorService executorService() {
			ExecutorService executorService = Executors.newFixedThreadPool(AppConstant.THREADS_COUNT);
			return executorService;
		}

		@Bean
		public CommonEndPointConstant endPointConstant() {
			return new CommonEndPointConstant();
		}
	}

	@Autowired
	ExecutorService executorService;
	@Autowired
	public CommonEndPointConstant endPointConstant;

}
