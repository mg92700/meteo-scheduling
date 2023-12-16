package com.meteo.job;

import com.meteo.model.MeteoEntity;
import org.springframework.batch.item.ItemProcessor;

public class MeteoItemProcessor implements ItemProcessor<MeteoEntity, MeteoEntity> {

    @Override
    public MeteoEntity process(MeteoEntity item) {
        // Vous pouvez effectuer des traitements supplémentaires ici si nécessaire
        return item;
    }
}

