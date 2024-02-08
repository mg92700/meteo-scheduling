package com.meteo;
import com.meteo.services.cities.CityService;
import com.meteo.services.meteo.MeteoService;
import com.meteo.utils.SchedulingMeteo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SchedulingMeteoTest {

    @InjectMocks
    private SchedulingMeteo schedulingMeteo;

    @Mock
    private MeteoService meteoService;

    @Mock
    private CityService cityService;

    @Mock
    private Environment env;

    private static final String FAKE_URL = "http://fakeapi.com/";
    private static final String FAKE_TOKEN = "fakeToken";
    private static final String FAKE_USER_AGENT = "Mozilla/5.0";

    @Before
    public void setUp() {
        // Initialisation des mocks si nécessaire
    }

    @Test
    public void testReportCurrentTime() throws IOException {
        // Créer une liste de villes fictive pour les tests
        List<String> fakeCities = Arrays.asList("12345", "23456", "34567");

        // Mock des méthodes nécessaires
        when(cityService.GetAllByInsee()).thenReturn(fakeCities);
        when(env.getProperty("api.url")).thenReturn(FAKE_URL);
        when(env.getProperty("api.token")).thenReturn(FAKE_TOKEN);

        // Exécuter la méthode à tester
        schedulingMeteo.reportCurrentTime();

        // Vérifier que les méthodes appropriées ont été appelées
        verify(cityService, times(1)).GetAllByInsee();
    }

    @Test
    public void testSendGetForCity() throws IOException {
        // Mock des dépendances nécessaires pour sendGetForCity()
        String fakeInsee = "12345";
        URL fakeUrl = new URL(FAKE_URL + FAKE_TOKEN + "&insee=" + fakeInsee);
        HttpURLConnection fakeConnection = mock(HttpURLConnection.class);
        InputStream fakeInputStream = new ByteArrayInputStream("fakeResponse".getBytes());

        when(env.getProperty("api.url")).thenReturn(FAKE_URL);
        when(env.getProperty("api.token")).thenReturn(FAKE_TOKEN);

        // Mock des méthodes nécessaires pour sendGetForCity()
        when(fakeConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);
        when(fakeConnection.getInputStream()).thenReturn(fakeInputStream);

        // Exécuter la méthode à tester
        schedulingMeteo.sendGetForCity(fakeInsee);

        // Vérifier que les méthodes appropriées ont été appelées
    }
}
