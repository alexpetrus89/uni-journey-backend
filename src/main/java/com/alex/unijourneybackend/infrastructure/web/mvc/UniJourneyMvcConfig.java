package com.alex.unijourneybackend.infrastructure.web.mvc;


import java.io.Serializable;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;




@Configuration
public class UniJourneyMvcConfig implements WebMvcConfigurer, Serializable {

    /**
     * Configure CORS
     * @return
     */
    @Override
    @SuppressWarnings("unused")
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/api/**")
            .allowedOrigins("http://localhost:4200")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true);
    }


}

