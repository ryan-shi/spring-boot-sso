package com.ryan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class SsoWebbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsoWebbApplication.class, args);
	}
}
