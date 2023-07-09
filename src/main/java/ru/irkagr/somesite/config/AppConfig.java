package ru.irkagr.somesite.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = "classpath:mail.properties", encoding = "UTF-8")
@PropertySource(value = "classpath:statistic.properties", encoding = "UTF-8")
@PropertySource(value = "classpath:common.properties", encoding = "UTF-8")
public class AppConfig {

    Environment environment;

    @Autowired
    public AppConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public Environment configPropertyReader() {
        return this.environment;
    }


}
