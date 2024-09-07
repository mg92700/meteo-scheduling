package com.meteo.batch.controllers;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BatchController {


    private final JobLauncher jobLauncher;

    private final Job jobAlert;

    public BatchController(JobLauncher jobLauncher, Job jobAlert) {
        this.jobLauncher = jobLauncher;
        this.jobAlert = jobAlert;
    }

    @PostMapping("/launchJob")
    public String launchJob() throws Exception {
        JobExecution jobExecution = jobLauncher.run(jobAlert, new JobParameters());
        return "Job Exit Status: " + jobExecution.getExitStatus();
    }
}
