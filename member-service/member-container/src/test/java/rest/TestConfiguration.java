package rest;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.project.imdang")
@EnableJpaRepositories(basePackages = "com.project.imdang.member.service.persistence")
@EntityScan(basePackages = "com.project.imdang.member.service.persistence")
public class TestConfiguration {
}
