package com.meteo.services.job;

import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

public interface JobService {

     void invokeJob(String jobName) throws JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException, JobInstanceAlreadyCompleteException ;
}
