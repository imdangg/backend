package com.project.imdang.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BatchScheduler {

    private final JobLauncher jobLauncher;
    private final Job liftPenaltyJob;

    public BatchScheduler(
            JobLauncher jobLauncher,
            @Qualifier("liftPenaltyJob") Job liftPenaltyJob) {
        this.jobLauncher = jobLauncher;
        this.liftPenaltyJob = liftPenaltyJob;
    }

    @Scheduled(cron = "0 0 2 * * ?")
    public void runLiftPenaltyJob() {
        runJob(liftPenaltyJob, "liftPenaltyJob");
    }

    private void runJob(Job job, String jobName) {
        try {
            JobParameters params = new JobParametersBuilder()
                    .addString("jobName", jobName)
                    .addLong("runTime", System.currentTimeMillis())
                    .toJobParameters();

            JobExecution execution = jobLauncher.run(job, params);
            log.info(">>>>>>>>> success : {}, {}", jobName, execution.getStatus());

        } catch (Exception e) {
            log.error(">>>>>>>>> fail : {}, {}", jobName, e.getMessage());
            e.printStackTrace();
        }
    }
}
