package com.example.fasthoandlee.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 허용할 엔드포인트 패턴을 지정
                .allowedOrigins("*") // 허용할 Origin을 명시
                .allowedMethods("GET", "POST", "PUT", "DELETE"); // 허용할 HTTP 메서드를 지정
    }
}
