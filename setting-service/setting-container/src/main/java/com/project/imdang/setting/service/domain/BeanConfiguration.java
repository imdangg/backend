package com.project.imdang.setting.service.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("settingBeanConfiguration")
public class BeanConfiguration {

    @Bean
    public NotificationDomainService notificationDomainService() {
        return new NotificationDomainServiceImpl();
    }
    @Bean
    public TermsDomainService termsDomainService() {
        return new TermsDomainServiceImpl();
    }
}
