package com.meteo.job;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteo.model.MeteoEntity;
import com.meteo.services.meteo.MeteoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@Slf4j
@AllArgsConstructor
public class MeteoBatchConfiguration extends DefaultBatchConfiguration {

    private final ObjectMapper objectMapper;
    private final MeteoService meteoService;

    @Override
    protected DataSource getDataSource() {
        return new EmbeddedDatabaseBuilder()
                .addScript("classpath:org/springframework/batch/core/schema-drop-h2.sql")
                .addScript("classpath:org/springframework/batch/core/schema-h2.sql")
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }
    @Bean
    public Job jobAlert(JobRepository jobRepository, Step step) {
        return new JobBuilder("jobAlert", jobRepository)
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new StepBuilder("step", jobRepository).
                <MeteoEntity, MeteoEntity>chunk(500, platformTransactionManager)
                .reader(new MeteoItemReader(objectMapper))
                .processor(new MeteoItemProcessor())
                .writer(new MeteoItemWriter(meteoService))
                .build();
    }



}
