package com.meteo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cities")
@Data
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "gps_coordinates")
    private String gpsCoordinates;

    @Column(name = "postal_code")
    private String postalCode;

    @Column
    private String insee;

    // Constructeurs, getters et setters

    public CityEntity() {
        // Constructeur par défaut nécessaire pour Hibernate
    }

    public String getInsee() {
        return insee;
    }
}
