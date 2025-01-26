package com.project.imdang.setting.service;

import com.project.imdang.setting.service.domain.NotificationDomainService;
import com.project.imdang.setting.service.domain.NotificationDomainServiceImpl;
import com.project.imdang.setting.service.domain.TermsDomainService;
import com.project.imdang.setting.service.domain.TermsDomainServiceImpl;
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
