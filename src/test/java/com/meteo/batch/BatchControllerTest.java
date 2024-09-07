package com.meteo.batch;

import com.meteo.batch.controllers.BatchController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BatchControllerTest {

    @InjectMocks
    private BatchController batchController;

    @Mock
    private JobLauncher jobLauncher;

    @Mock
    private Job jobAlert;

    @Before
    public void setUp() {
        // Initialisation des mocks si nécessaire
    }

    @Test
    public void testLaunchJob() throws Exception {
        // Création d'un objet JobExecution fictif
        JobExecution jobExecution = mock(JobExecution.class);
        when(jobLauncher.run(eq(jobAlert), any(JobParameters.class))).thenReturn(jobExecution);
        when(jobExecution.getExitStatus()).thenReturn(org.springframework.batch.core.ExitStatus.COMPLETED);

        // Appel de la méthode à tester
        String result = batchController.launchJob();

        // Vérification du résultat
        assert result.equals("Job Exit Status: " + org.springframework.batch.core.ExitStatus.COMPLETED);

        // Vérification des appels aux méthodes nécessaires
        verify(jobLauncher, times(1)).run(eq(jobAlert), any(JobParameters.class));
        verify(jobExecution, times(1)).getExitStatus();
    }
}
