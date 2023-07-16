package com.meteo.job;

import com.meteo.model.MeteoEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@Slf4j
public class BatchAlertWritter implements ItemWriter<List<MeteoEntity>> {
    @Override
    public void write(Chunk<? extends List<MeteoEntity>> chunk) throws Exception {
        log.info("start writter");

        log.info("end writter");
    }
}
