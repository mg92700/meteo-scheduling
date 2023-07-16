package com.meteo.repository;


import com.meteo.model.MeteoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeteoRepository extends JpaRepository<MeteoEntity, Long > {
}
