package com.meteo.batch;

import com.meteo.batch.controllers.MeteoController;
import com.meteo.batch.model.MeteoEntity;
import com.meteo.batch.services.meteo.MeteoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MeteoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MeteoService meteoService;

    @InjectMocks
    private MeteoController meteoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(meteoController).build();
    }

    @Test
    public void testGetAllMeteo() throws Exception {
        when(meteoService.all()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/meteo-batch/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
    }

    @Test
    public void testGetMeteoByDate() throws Exception {
        when(meteoService.all()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/meteo-batch/byDate").param("date", "2023-01-01"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
    }

    @Test
    public void testGetMeteoByDateAndInsee() throws Exception {
        MeteoEntity meteoEntity = new MeteoEntity();
        meteoEntity.setDatesaving(LocalDateTime.parse("2023-01-01T00:00:00"));
        meteoEntity.setInsee("12345");

        when(meteoService.all()).thenReturn(List.of(meteoEntity));

        mockMvc.perform(get("/meteo-batch/byDateAndInsee")
                        .param("date", "2023-01-01T00:00:00")
                        .param("location", "12345"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    public void testGetMeteoByDateAndInseeLast() throws Exception {
        MeteoEntity meteoEntity = new MeteoEntity();
        meteoEntity.setDatesaving(LocalDateTime.parse("2023-01-01T00:00:00"));
        meteoEntity.setInsee("12345");
        when(meteoService.all()).thenReturn(List.of(meteoEntity));

        mockMvc.perform(get("/meteo-batch/byDateAndInseeLast")
                        .param("date", "2023-01-01T00:00:00")
                        .param("location", "12345"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{}")); // Update this to match the expected JSON
    }

    @Test
    public void testGetMeteoByInsee() throws Exception {
        MeteoEntity meteoEntity = new MeteoEntity();
        meteoEntity.setDatesaving(LocalDateTime.parse("2023-01-01T00:00:00"));
        meteoEntity.setInsee("12345");
        when(meteoService.all()).thenReturn(List.of(meteoEntity));

        mockMvc.perform(get("/meteo-batch/byInsee").param("location", "12345"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{}")); // Update this to match the expected JSON
    }
}