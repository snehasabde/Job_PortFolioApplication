package com.example.portfolio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow all /api/** endpoints to be accessed from your frontend origin
        registry.addMapping("/api/**")
                // IMPORTANT: Replace with the actual URL(s) your frontend will be served from.
                // If using VS Code Live Server, it's typically http://127.0.0.1:5500 or http://localhost:5500
                .allowedOrigins("http://127.0.0.1:5500", "http://localhost:5500")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow common HTTP methods
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true) // Allow cookies, authorization headers, etc.
                .maxAge(3600); // How long the pre-flight request can be cached
    }
}
