package com.project.imdang.container;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class DemoConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173") // 프론트엔드 도메인
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // OPTIONS 추가
                        .allowedHeaders("Authorization", "Content-Type") // 허용할 헤더
                        .exposedHeaders("Authorization") // 클라이언트에서 Authorization 헤더 볼 수 있도록
                        .allowCredentials(true); // 쿠키 & 인증 포함 허용
            }
        };
    }
}
