package com.project.imdang.member.service.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class LoginConfig {
    @Bean
    public RestTemplate getRestTemplete() {
        return new RestTemplate();
    }
}
