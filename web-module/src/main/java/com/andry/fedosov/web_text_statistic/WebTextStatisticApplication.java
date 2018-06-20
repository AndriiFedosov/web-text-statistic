package com.andry.fedosov.web_text_statistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebTextStatisticApplication {

	@Autowired
	Config config;

	public static void main(String[] args) {
		SpringApplication.run(WebTextStatisticApplication.class, args);
	}
}
