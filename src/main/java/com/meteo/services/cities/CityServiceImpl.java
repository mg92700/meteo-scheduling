package com.meteo.services.cities;

import com.meteo.model.CityEntity;
import com.meteo.repository.CityRepository;
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
