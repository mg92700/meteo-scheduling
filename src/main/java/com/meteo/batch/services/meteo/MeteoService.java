package com.meteo.batch.services.meteo;

import com.meteo.batch.model.MeteoEntity;

import java.io.IOException;
import java.util.List;

public interface MeteoService {
    MeteoEntity saveDepartment(MeteoEntity meteoEntity);

    List<MeteoEntity> all();

    void saveAll(List<MeteoEntity> meteoEntities);

    MeteoEntity  getMeteoForCity(String insee) throws IOException;

}
