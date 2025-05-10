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

        // Permitir todos los orígenes
        config.addAllowedOrigin("*");

        // Permitir todos los encabezados
        config.addAllowedHeader("*");

        // Permitir todos los métodos (GET, POST, PUT, DELETE, etc.)
        config.addAllowedMethod("*");

        // Tiempo que el navegador puede cachear la respuesta preflight
        config.setMaxAge(3600L);

        // Aplicar esta configuración a todas las rutas
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}