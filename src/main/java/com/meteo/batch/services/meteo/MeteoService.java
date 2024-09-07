package com.meteo.batch.services.meteo;

import com.meteo.batch.model.MeteoEntity;

import java.util.List;

public interface MeteoService {
    MeteoEntity saveDepartment(MeteoEntity meteoEntity);

    List<MeteoEntity> all();

    void saveAll(List<MeteoEntity> meteoEntities);

}
