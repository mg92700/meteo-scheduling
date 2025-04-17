package com.meteo.batch.job;

import com.meteo.batch.model.MeteoEntity;
import com.meteo.batch.services.meteo.MeteoService;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
@Slf4j
public class MeteoBatchConfiguration {


    private final MeteoService meteoService;

    public MeteoBatchConfiguration( MeteoService meteoService) {
        this.meteoService = meteoService;
    }

    @Bean
    public Job meteoJob(JobRepository jobRepository, Step step) {
        return new JobBuilder("meteoJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public JpaPagingItemReader<String> cityInseeReader(EntityManagerFactory entityManagerFactory) {
        JpaPagingItemReader<String> reader = new JpaPagingItemReader<>();
        reader.setQueryString("SELECT c.insee FROM CityEntity c");
        reader.setEntityManagerFactory(entityManagerFactory);
        reader.setPageSize(10);
        reader.setName("cityInseeReader");
        reader.setSaveState(false);
        return reader;
    }



    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager,EntityManagerFactory entityManagerFactory) {
        return new StepBuilder("meteoStep", jobRepository)
                .<String, MeteoEntity>chunk(10, transactionManager)
                .reader(cityInseeReader(entityManagerFactory))
                .processor(new MeteoItemProcessor(meteoService))
                .writer(new MeteoItemWriter(meteoService))
                .build();
    }
}
