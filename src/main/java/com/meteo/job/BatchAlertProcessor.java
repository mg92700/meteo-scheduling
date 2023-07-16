package com.meteo.job;

import com.meteo.model.MeteoEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class BatchAlertProcessor implements ItemProcessor<List<MeteoEntity>,List<MeteoEntity>> {
    @Override
    public List<MeteoEntity> process(List<MeteoEntity> item) throws Exception {
        if(item!=null) {
            log.info("start processor");
            return item.stream().filter(c -> c.getDatesaving().getDayOfYear() == LocalDate.now().getDayOfYear()).collect(Collectors.toList());

        }
        else {
            return null;
        }
    }

}
