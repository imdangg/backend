package com.project.imdang.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@EnableBatchProcessing
@Import(DataSourceConfiguration.class)
@RequiredArgsConstructor
@Configuration
public class LiftPenaltyJobConfiguration {
    // Member
    // TODO : Partitioning 기능을 통한 멀티 스레드 구조로 Chunk 기반 프로세스 구현

    private final DataSource dataSource;

    private final int CHUNK_SIZE = 10;

    @Bean
    public Job liftPenaltyJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) throws Exception {
        return new JobBuilder("liftPenaltyJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(liftPenaltyStep(jobRepository, platformTransactionManager))
                .build();
    }

    @Bean
    public Step liftPenaltyStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) throws Exception {
        return new StepBuilder("liftPenaltyStep", jobRepository)
                .allowStartIfComplete(true)
                .<Member, Member>chunk(CHUNK_SIZE, platformTransactionManager)
                .reader(memberItemReader())
                .processor(memberItemProcessor())
                .writer(memberItemWriter())
                .build();
    }

    @Bean
    public ItemReader<Member> memberItemReader() throws Exception {
        return new JdbcPagingItemReaderBuilder<Member>()
                .name("jdbcPagingMemberItemReader")
                .dataSource(dataSource)
                .queryProvider(createQueryProvider())
                .rowMapper((rs, rowNum) -> new Member(
                        rs.getString("id"),
                        rs.getDate("penalty_from").toLocalDate(),
                        rs.getDate("penalty_to").toLocalDate(),
                        rs.getString("status")))
                // TODO : pageSize vs fetchSize
                .pageSize(10)
                .fetchSize(CHUNK_SIZE)
                .build();
    }

    private PagingQueryProvider createQueryProvider() throws Exception {
        SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
        queryProvider.setDataSource(dataSource);
        queryProvider.setSelectClause("SELECT id, penalty_from, penalty_to, status");
        queryProvider.setFromClause("FROM member");
        queryProvider.setWhereClause("WHERE status <> 'ACTIVE' AND penalty_to < CURDATE()");

        Map<String, Order> sortKeys = new HashMap<>(1);
        sortKeys.put("id", Order.ASCENDING);

        queryProvider.setSortKeys(sortKeys);
        return queryProvider.getObject();
    }

    @Bean
    public ItemProcessor<Member, Member> memberItemProcessor() {
        return member -> {
            member.setStatus("ACTIVE");
            return member;
        };
    }

    @Bean
    public ItemWriter<Member> memberItemWriter() {
//        return new ItemWriter<Member>() {
//            @Override
//            public void write(Chunk<? extends Member> chunk) throws Exception {
//                List<? extends Member> members = chunk.getItems();
//                for (Member member : members) {
//                    System.out.println(member);
//                }
//            }
//        };
        return new JdbcBatchItemWriterBuilder<Member>()
                .dataSource(dataSource)
                .sql("UPDATE member SET status = :status WHERE id = :id")
                .beanMapped()
                .assertUpdates(true)
                .build();
    }
}
