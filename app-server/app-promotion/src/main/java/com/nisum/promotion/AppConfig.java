/**
 * 
 */
package com.nisum.promotion;

import com.nisum.common.constant.AppConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Nisum Pakistan
 *
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
	public ExecutorService executorService() {
		ExecutorService executorService = Executors.newFixedThreadPool(AppConstant.THREADS_COUNT);
		return executorService;
	}
}
