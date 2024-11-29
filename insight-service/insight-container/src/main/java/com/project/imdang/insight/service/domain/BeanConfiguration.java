package com.project.imdang.insight.service.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public InsightDomainService insightDomainService() {
        return new InsightDomainServiceImpl();
    }
}
