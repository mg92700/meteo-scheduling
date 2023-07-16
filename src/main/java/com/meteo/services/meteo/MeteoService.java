package com.meteo.services.meteo;

import com.meteo.model.MeteoEntity;

import java.util.List;

public interface MeteoService {
    MeteoEntity saveDepartment(MeteoEntity meteoEntity);

    List<MeteoEntity> all();

}
