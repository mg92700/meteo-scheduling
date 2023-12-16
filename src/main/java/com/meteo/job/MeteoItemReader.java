package com.meteo.job;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteo.model.MeteoEntity;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class MeteoItemReader extends JsonItemReader<MeteoEntity> {

    private final ObjectMapper objectMapper = new ObjectMapper();
    public MeteoItemReader(ObjectMapper objectMapper) {
        this.setResource(new FileSystemResource("src/main/resources/meteo.json"));
        this.setName("requestJsonItemReader");
        this.setJsonObjectReader(new JacksonJsonObjectReader<>(this.objectMapper, MeteoEntity.class));
    }
}
