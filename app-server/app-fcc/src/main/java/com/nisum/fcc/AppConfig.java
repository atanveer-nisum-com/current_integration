package com.nisum.fcc;

import com.nisum.common.constant.AppConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The Class AppConfig.
 */
@Configuration
@PropertySource("classpath:application.properties")
@EnableCassandraRepositories(basePackages = "com.nisum.fcc.cassandra.repository")
@ComponentScan(basePackages="com.nisum.fcc")
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

}
