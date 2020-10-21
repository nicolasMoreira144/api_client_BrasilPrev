package com.spring.course.profiles;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("spring-datasource")
public class DBConfiguration {


	@Profile("dev")
	@Bean
	public String devDatabaseConnection() {
		System.out.println("test conection database dev ---------------->>>>");
		return "";
	}

	@Profile("prod")
	@Bean
	public String prodDatabaseConnection() {
		System.out.println("test conection database production ---------------->>>>>>>>>>>>>>>");
		return "";
	}
}
