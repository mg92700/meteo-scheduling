package com.meteo.services.job;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class JobServiceImpl implements JobService{

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private ApplicationContext context;

    @Async
    public void invokeJob(String jobName) throws JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException, JobInstanceAlreadyCompleteException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        final String date = dateFormat.format(new Date());
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("Sync date", date)
                .toJobParameters();

        var jobToStart = context.getBean(jobName, Job.class);
        jobLauncher.run(jobToStart, jobParameters);
    }
}
