package com.project.imdang.member.service;

import com.project.imdang.member.service.domain.MemberCouponDomainService;
import com.project.imdang.member.service.domain.MemberCouponDomainServiceImpl;
import com.project.imdang.member.service.domain.MemberDomainService;
import com.project.imdang.member.service.domain.MemberDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration("memberBeanConfiguration")
public class BeanConfiguration {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public MemberDomainService memberDomainService() {
        return new MemberDomainServiceImpl();
    }

    @Bean
    public MemberCouponDomainService memberCouponDomainService() {
        return new MemberCouponDomainServiceImpl();
    }
}
