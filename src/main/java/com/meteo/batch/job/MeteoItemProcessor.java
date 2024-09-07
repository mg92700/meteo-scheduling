package com.meteo.batch.job;

import com.meteo.batch.model.MeteoEntity;
import org.springframework.batch.item.ItemProcessor;

public class MeteoItemProcessor implements ItemProcessor<MeteoEntity, MeteoEntity> {

    @Override
    public MeteoEntity process(MeteoEntity item) {
        // Vous pouvez effectuer des traitements supplémentaires ici si nécessaire
        return item;
    }
}

