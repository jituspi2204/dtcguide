package com.dtcguide.backend.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){
        corsRegistry.addMapping("/api/v1/**")
                .allowedOrigins("http://localhost:3000", "https://dtc-633s.onrender.com")
                .allowedMethods("GET")
                .allowCredentials(false);
    }
}
