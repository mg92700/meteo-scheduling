package com.meteo.batch.job;

import com.meteo.batch.model.MeteoEntity;
import com.meteo.batch.services.meteo.MeteoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@RequiredArgsConstructor
@Slf4j
public class MeteoItemProcessor implements ItemProcessor<String, MeteoEntity> {

    private final MeteoService meteoService;

    @Override
    public MeteoEntity process(String inseeCode) throws Exception {
        log.info("Processing Insee code: " + inseeCode);
        MeteoEntity meteoEntity = meteoService.getMeteoForCity(inseeCode);
        if (meteoEntity != null) {
            log.info("Meteo data retrieved for Insee code: " + inseeCode);
        } else {
            log.warn("No meteo data found for Insee code: " + inseeCode);
        }
        return meteoEntity;
    }
}
