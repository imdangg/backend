package com.project.imdang.insight.service.domain;

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
    @Bean
    public RequestDomainService requestDomainService() {
        return new RequestDomainServiceImpl();
    }
}
