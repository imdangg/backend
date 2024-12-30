package com.project.imdang.setting.service.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("settingBeanConfiguration")
public class BeanConfiguration {

//    @Bean
//    public ObjectMapper settingObjectMapper() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule()); // Java 8 날짜/시간 모듈 등록
//        objectMapper.configure(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false); // ISO 8601 포맷 사용
//        return objectMapper;
//    }

    @Bean
    public NotificationDomainService notificationDomainService() {
        return new NotificationDomainServiceImpl();
    }
    @Bean
    public TermsDomainService termsDomainService() {
        return new TermsDomainServiceImpl();
    }
}
