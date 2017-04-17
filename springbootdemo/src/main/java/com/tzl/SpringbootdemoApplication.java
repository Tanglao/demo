package com.tzl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringbootdemoApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SpringbootdemoApplication.class, args);
	}

}
