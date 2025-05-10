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

        // Permitir específicamente tu origen de Netlify
        config.addAllowedOrigin("https://globalinelogistic.netlify.app");
        // También puedes permitir localhost para desarrollo
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedOrigin("http://localhost:5000");
        // O permitir todos los orígenes si es necesario
        // config.addAllowedOrigin("*");

        // Permitir todos los encabezados
        config.addAllowedHeader("*");

        // Permitir todos los métodos, incluyendo OPTIONS explícitamente
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("OPTIONS");

        // Tiempo que el navegador puede cachear la respuesta preflight
        config.setMaxAge(3600L);

        // Aplicar esta configuración a todas las rutas
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}