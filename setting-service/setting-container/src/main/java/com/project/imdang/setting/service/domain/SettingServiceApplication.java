package com.project.imdang.setting.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableJpaRepositories(basePackages = "com.project.imdang.setting.service.persistence")
//@EntityScan(basePackages = "com.project.imdang.setting.service.persistence")
//@SpringBootApplication(scanBasePackages = "com.project.imdang")
public class SettingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SettingServiceApplication.class, args);
    }

}
