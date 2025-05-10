package com.globaline.logistic.api.globaline_logistic_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Dominios permitidos (Netlify + locales)
        config.setAllowedOrigins(Arrays.asList(
                "https://globalinelogistic.netlify.app",
                "http://localhost:3000",
                "http://localhost:5000"
        ));

        config.setAllowCredentials(true);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*"); // Permite todos los métodos (GET, POST, etc.)
        config.setMaxAge(3600L);

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }}