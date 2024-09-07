package com.meteo.batch.job;

import com.meteo.batch.model.MeteoEntity;
import com.meteo.batch.services.meteo.MeteoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class MeteoItemWriter implements ItemWriter<MeteoEntity> {

    private final MeteoService meteoService;
    @Override
    public void write(Chunk<? extends MeteoEntity> chunk) throws Exception {
        log.info("info");

        meteoService.saveAll((List<MeteoEntity>) chunk.getItems());
    }
}

