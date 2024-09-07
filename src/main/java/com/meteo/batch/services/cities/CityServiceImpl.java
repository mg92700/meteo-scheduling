package com.meteo.batch.services.cities;

import com.meteo.batch.model.CityEntity;
import com.meteo.batch.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService{

    @Autowired
    CityRepository cityRepository;

    @Override
    public List<String> GetAllByInsee() {
       return cityRepository.findAll().stream()
                .map(CityEntity::getInsee)
                .collect(Collectors.toList());
    }
}
