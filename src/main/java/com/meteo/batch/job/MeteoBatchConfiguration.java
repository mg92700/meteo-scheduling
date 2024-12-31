package com.meteo.batch.job;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteo.batch.model.MeteoEntity;
import com.meteo.batch.services.meteo.MeteoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@Slf4j
public class MeteoBatchConfiguration extends DefaultBatchConfiguration {

    private final ObjectMapper objectMapper;
    private final MeteoService meteoService;

    public MeteoBatchConfiguration(ObjectMapper objectMapper, MeteoService meteoService) {
        this.objectMapper = objectMapper;
        this.meteoService = meteoService;
    }

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.username}")
    private String dataSourceUsername;

    @Value("${spring.datasource.password}")
    private String dataSourcePassword;


    @Override
    @Primary
    protected DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(dataSourceUrl);
        dataSource.setUsername(dataSourceUsername);
        dataSource.setPassword(dataSourcePassword);
        dataSource.setDriverClassName("org.postgresql.Driver");
        return dataSource;
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
