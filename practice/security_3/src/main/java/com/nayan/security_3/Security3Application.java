package com.nayan.security_3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Security3Application {

	public static void main(String[] args) {
		SpringApplication.run(Security3Application.class, args);
	}

}
