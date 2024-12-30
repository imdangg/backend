package com.project.imdang.insight.service.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("insightBeanConfiguration")
public class BeanConfiguration {

    @Bean
    public InsightDomainService insightDomainService() {
        return new InsightDomainServiceImpl();
    }
    @Bean
    public ExchangeDomainService exchangeDomainService() {
        return new ExchangeDomainServiceImpl();
    }
}
