package com.meteo.batch.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
public class MvcConfiguration extends WebMvcConfigurationSupport {

	// This configuration is for CORS support
	// Allow all origins, methods and headers
	// This is only for development purposes
	// For production, you should configure it properly
	// For more info, please refer to https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS
	// This configuration is for development purposes only
	// For production, you should configure it properly
	// For more info, please refer to https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS
	// This configuration is for development purposes only
	// For production, you should configure it properly
	// For more info, please refer to https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS
	// This configuration is for development purposes only
	// For production, you should configure it properly
	// For more info, please refer to https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS
	// This configuration is for development purposes only
	// For production, you should configure it properly
	// For more info, please refer to https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS
	// This configuration is for development purposes only
	// For production, you should configure it properly
	// For more info, please refer to https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS
	// This configuration is for development purposes only
	// For production, you should configure it properly
	// For more info, please refer to https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS
	// This configuration is for development purposes only
	// For production, you should configure it properly
	// For more info, please refer to https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS
	// This configuration is for development purposes only
	// For production, you should configure it properly
	// For more info, please refer to https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS
	// This configuration is for development purposes only
	// For production, you should configure it properly
	// For more info, please refer to https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS
	// This configuration is for development purposes only
	// For production, you should configure it properly
	// For more info, please refer to https://developer.mozilla.org/en-US/docs/Web/HTTP

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

