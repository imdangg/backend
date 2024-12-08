package com.project.imdang.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.project.imdang.insight.service.persistence", "com.project.imdang.member.persistence", "com.project.imdang.setting.service.persistence"})
@EntityScan(basePackages = {"com.project.imdang.insight.service.persistence","com.project.imdang.member.persistence", "com.project.imdang.setting.service.persistence"} )
@SpringBootApplication(scanBasePackages = "com.project.imdang")
public class ImdangApplication {
    public static void main(String[] args) {
        SpringApplication.run(ImdangApplication.class, args);
    }
}
