package com.globaline.logistic.api.globaline_logistic_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Allow your Netlify domain
        config.addAllowedOrigin("https://globalinelogistic.netlify.app");

        // Allow localhost for development
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedOrigin("http://localhost:5000");

        // Allow credentials
        config.setAllowCredentials(true);

        // Allow all headers
        config.addAllowedHeader("*");

        // Allow all methods
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("OPTIONS");

        // Set max age for preflight requests
        config.setMaxAge(3600L);

        // Apply this configuration to all routes
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}