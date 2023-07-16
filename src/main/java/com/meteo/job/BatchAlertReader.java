package com.meteo.job;

import com.meteo.model.MeteoEntity;
import com.meteo.services.meteo.MeteoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Slf4j
public class BatchAlertReader implements ItemReader<List<MeteoEntity>> {


    @Autowired
    MeteoService meteoService;

    @Override
    public List<MeteoEntity> read() throws ParseException, NonTransientResourceException {
        if(meteoService!=null) {
            log.info("start reader");
            log.info("there is " + meteoService.all().size() + " data");
            log.info("end reader");
            return meteoService.all();
        }
        return null;
    }
}
