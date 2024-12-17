package rest;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.project.imdang")
@EnableJpaRepositories(basePackages = "com.project.imdang.member.persistence")
@EntityScan(basePackages = "com.project.imdang.member.persistence")
public class TestConfiguration {
}
