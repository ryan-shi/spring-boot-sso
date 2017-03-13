package com.ryan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
public class SsoResourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsoResourceApplication.class, args);
	}
}
