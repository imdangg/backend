package com.project.imdang.insight.service.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("insightBeanConfiguration")
public class BeanConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Java 8 날짜/시간 모듈 등록
        objectMapper.configure(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false); // ISO 8601 포맷 사용
        return objectMapper;
    }

    @Bean
    public InsightDomainService insightDomainService() {
        return new InsightDomainServiceImpl();
    }
    @Bean
    public ExchangeDomainService exchangeDomainService() {
        return new ExchangeDomainServiceImpl();
    }
}
