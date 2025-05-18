package com.project.imdang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ImdangApplication {
    public static void main(String[] args) {
        SpringApplication.run(ImdangApplication.class, args);
    }
}
