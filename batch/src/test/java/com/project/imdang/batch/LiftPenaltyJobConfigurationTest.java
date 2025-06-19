package com.project.imdang.batch;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

@ActiveProfiles("test2")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBatchTest    // 배치 관련 테스트 유틸 활성화
@SpringBootTest
class LiftPenaltyJobConfigurationTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Qualifier("liftPenaltyJob")
    @Autowired
    private Job liftPenaltyJob;

    @BeforeEach
    void setUp() {
        jobLauncherTestUtils.setJob(liftPenaltyJob);
        // 데이터 생성
        jdbcTemplate.update("INSERT INTO member " +
                        "(id, auth_id, auth_type, birth_date, device_token, exchange_count, " +
                        "gender, insight_count, is_deleted, is_login, nickname, refresh_token, " +
                        "accused_count, penalty_from, penalty_to, rejected_count, status) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                UUID.randomUUID().toString(),
                "1111222227",
                "KAKAO",
                null,
                null,
                0,
                0,
                0,
                0,
                1,
                "nickname_test567",
                null,
                10,
                LocalDate.now().minusDays(3),
                LocalDate.now().minusDays(1),
                0,
                "TEMPORARY_BANNED");
    }

    @Test
    void liftPenaltyJob() throws Exception {
        // Given: 배치 실행을 위한 파라미터 설정
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        // When: 배치 실행
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        // Then: 배치가 정상적으로 완료되었는지 검증
        Assertions.assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
        Assertions.assertThat(jobExecution.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);

        Collection<StepExecution> stepExecutions = jobExecution.getStepExecutions();
        for (StepExecution stepExecution : stepExecutions) {
            Assertions.assertThat(stepExecution.getReadCount()).isEqualTo(1);
            Assertions.assertThat(stepExecution.getCommitCount()).isEqualTo(1);
            Assertions.assertThat(stepExecution.getWriteCount()).isEqualTo(1);
        }
    }
}
