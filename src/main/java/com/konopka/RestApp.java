package com.konopka.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.lang.*;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@PropertySource("classpath:application.properties")
@Configuration
@SpringBootApplication
public class RestApp extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(RestApp.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    private static Class<RestApp> applicationClass = RestApp.class;
}