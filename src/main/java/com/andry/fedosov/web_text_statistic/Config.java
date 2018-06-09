package com.andry.fedosov.web_text_statistic;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "com.andry.fedosov.web_text_statistic")
@EnableJpaRepositories(basePackages = "com.andry.fedosov.web_text_statistic.repositories")
@EnableAutoConfiguration
public class Config {
}
