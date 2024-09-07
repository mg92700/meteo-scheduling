package com.meteo.batch.repository;


import com.meteo.batch.model.MeteoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeteoRepository extends JpaRepository<MeteoEntity, Long > {
}
