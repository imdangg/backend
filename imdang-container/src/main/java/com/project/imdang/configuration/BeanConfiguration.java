package com.project.imdang.configuration;

import com.project.imdang.insight.domain.InsightDomainService;
import com.project.imdang.insight.domain.InsightDomainServiceImpl;
import com.project.imdang.member.domain.MemberDomainService;
import com.project.imdang.member.domain.MemberDomainServiceImpl;
import com.project.imdang.setting.domain.NotificationDomainService;
import com.project.imdang.setting.domain.NotificationDomainServiceImpl;
import com.project.imdang.setting.domain.TermsDomainService;
import com.project.imdang.setting.domain.TermsDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public InsightDomainService insightDomainService() {
        return new InsightDomainServiceImpl();
    }

    @Bean
    public MemberDomainService memberDomainService() {
        return new MemberDomainServiceImpl();
    }

    @Bean
    public NotificationDomainService notificationDomainService() {
        return new NotificationDomainServiceImpl();
    }

    @Bean
    public TermsDomainService termsDomainService() {
        return new TermsDomainServiceImpl();
    }
}
