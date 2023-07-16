package com.meteo.job;


import com.meteo.model.MeteoEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
@Slf4j
@EnableBatchProcessing
public class BatchAlertConfiguration {




    @Bean
    public Job jobAlert(JobRepository jobRepository,  Step step1) {
        return new JobBuilder("jobAlert", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step1", jobRepository)
                .<List<MeteoEntity>, List<MeteoEntity>> chunk(500, transactionManager)
                .reader(new BatchAlertReader())
                .processor(new BatchAlertProcessor())
                .writer(new BatchAlertWritter())
                .build();
    }




}
