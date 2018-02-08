package com.nisum.user;

import com.nisum.common.constant.AppConstant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.client.RestTemplate;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The Class AppConfig.
 */
@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages="com.nisum")
@EnableAutoConfiguration
@EnableJpaRepositories("com.nisum.user.repository")
@EntityScan("com.nisum.user.model")
public class AppConfig {
	
	@Value("${spring.mail.protocol}")
	private String springMailProtocol;
	
	@Value("${spring.mail.host}")
	private String springMailHost;
	
	@Value("${spring.mail.port}")
	private int springMailPort;
	
	@Value("${spring.mail.username}")
	private String springMailUsername;
	
	@Value("${spring.mail.password}")
	private String springMailPassword;
	
	@Value("${spring.mail.properties.mail.smtp.auth}")
	private String springMailPropertiesMailSMTPAUth;
	
	@Value("${spring.mail.properties.mail.smtp.starttls.enable}")
	private String springMailPropertiesMailSMTPStarttlsEnable;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

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
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	
	/**
	 * @author mjamil
	 * @return
	 */
	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(springMailHost);
        mailSender.setPort(springMailPort);
         
        mailSender.setUsername(springMailUsername);
        mailSender.setPassword(springMailPassword);
         
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", springMailProtocol);
        props.put("mail.smtp.auth", springMailPropertiesMailSMTPAUth);
        props.put("mail.smtp.starttls.enable", springMailPropertiesMailSMTPStarttlsEnable);
        props.put("mail.debug", "true");
         
        return mailSender;
	}

}
