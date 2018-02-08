package com.nisum.fcc.test.controller;

import com.nisum.common.constant.AppConstant;
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

/**
 * The Class BaseControllerTest.
 */
@RunWith(SpringRunner.class)
@EnableSpringDataWebSupport
@ActiveProfiles("test")
public abstract class BaseControllerTest {

	/**  Main entry point for server-side. */
	@Autowired
	MockMvc mvc;
	
	/** The Constant PAGE_SIZE_12. */
	final static protected int PAGE_SIZE_12 = 12;

	@Configuration
	static class ContextConfiguration {

		@Bean
		ExecutorService executorService() {
			return Executors.newFixedThreadPool(AppConstant.THREADS_COUNT);
		}
	}
}
