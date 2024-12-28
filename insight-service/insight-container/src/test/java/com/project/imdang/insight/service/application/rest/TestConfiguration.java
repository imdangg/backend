package com.project.imdang.insight.service.application.rest;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.project.imdang.insight.service.persistence")
@EntityScan(basePackages = "com.project.imdang.insight.service.persistence")
@SpringBootApplication(scanBasePackages = "com.project.imdang")
public class TestConfiguration {
}
