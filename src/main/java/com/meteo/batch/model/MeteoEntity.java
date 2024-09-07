package com.meteo.batch.model;




import com.meteo.batch.dto.RootDto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table(name = "meteo")
@Data
public class MeteoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "METEO_SEQ")
    @SequenceGenerator(name = "METEO_SEQ", sequenceName = "METEO_SEQ", allocationSize = 1)
    private long id;
    private static final AtomicInteger count = new AtomicInteger(-0);

    private String city;

    private String probarain;

    private String probafrost;

    private String probafog;

    private String probawind70;

    private String probawind100;

    private String tsoil1;

    private String temp2m;

    private String insee;

    private LocalDateTime datesaving;

    public MeteoEntity(RootDto rootDto,String iseen){
        this.probarain = String.valueOf(rootDto.getForecast().get(0).getProbarain());
        this.probafrost = String.valueOf(rootDto.getForecast().get(0).getProbafrost());
        this.probafog = String.valueOf(rootDto.getForecast().get(0).getProbafog());
        this.probawind70 = String.valueOf(rootDto.getForecast().get(0).getProbawind70());
        this.probawind100 = String.valueOf(rootDto.getForecast().get(0).getProbawind100());
        this.tsoil1 = String.valueOf(rootDto.getForecast().get(0).getTsoil1());
        this.temp2m = String.valueOf(rootDto.getForecast().get(0).getTemp2m());
        this.insee=iseen;
        this.city=rootDto.getCity().getName();
        this.datesaving = LocalDateTime.now();
    }


    public MeteoEntity(){}

}
