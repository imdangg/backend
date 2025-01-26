package com.project.imdang.insight.service;

import com.project.imdang.insight.service.domain.ExchangeDomainService;
import com.project.imdang.insight.service.domain.ExchangeDomainServiceImpl;
import com.project.imdang.insight.service.domain.InsightDomainService;
import com.project.imdang.insight.service.domain.InsightDomainServiceImpl;
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
