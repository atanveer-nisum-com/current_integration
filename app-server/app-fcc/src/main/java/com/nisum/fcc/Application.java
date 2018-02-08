package com.nisum.fcc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


/**
 * The Class Application.
 */
@EnableCaching
@SpringBootApplication
public class Application implements CommandLineRunner {

		
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	
	/**
	 * implemented run method to start cassandra migration 
	 **/
	@Override
	public void run(String... args) throws Exception {
//		System.out.println("Starting Cassandra Migration");
//		cassandraMigration.migrate();
	}

}
