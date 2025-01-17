package com.backend.backendProyecto.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")  // Define el patrón de URL para los endpoints que deberían permitir CORS
                .allowedOrigins("http://localhost:3000")  // Permite solicitudes desde tu frontend (ajusta la URL si es necesario)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Métodos permitidos
                .allowedHeaders("*");  // Permite todos los encabezados
    }
}