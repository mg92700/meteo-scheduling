package com.meteo.controllers;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BatchController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job jobAlert;

    @PostMapping("/launchJob")
    public String launchJob() throws Exception {
        JobExecution jobExecution = jobLauncher.run(jobAlert, new JobParameters());
        return "Job Exit Status: " + jobExecution.getExitStatus();
    }
}
