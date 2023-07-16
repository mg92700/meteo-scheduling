package com.meteo.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
public class MvcConfiguration extends WebMvcConfigurationSupport {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/url/**")
                .allowedOrigins("*")
                .allowedMethods("PUT", "DELETE","POST","OPTIONS","GET");
        registry.addMapping("/resources/**")
                .allowedOrigins("*")
                .allowedMethods("GET");

    }
}

