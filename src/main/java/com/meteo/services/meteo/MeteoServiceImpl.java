package com.meteo.services.meteo;

import com.meteo.model.MeteoEntity;
import com.meteo.repository.MeteoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MeteoServiceImpl implements MeteoService{
    @Autowired
    MeteoRepository meteoRepository;

    @Override
    @Transactional
    public MeteoEntity saveDepartment(MeteoEntity meteoEntity) {
        return meteoRepository.save(meteoEntity);
    }

    @Override
    @Transactional
    public List<MeteoEntity> all() {
        return meteoRepository.findAll();
    }

    @Override
    public void saveAll(List<MeteoEntity> meteoEntities) {
        meteoRepository.saveAll(meteoEntities);
    }


}
