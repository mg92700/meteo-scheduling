package com.meteo.batch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CityDto {
    @JsonProperty("insee")
    public String getInsee() {
        return this.insee;
    }

    public void setInsee(String insee) {
        this.insee = insee;
    }

    String insee;

    @JsonProperty("cp")
    public int getCp() {
        return this.cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    int cp;

    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;

    @JsonProperty("latitude")
    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    double latitude;

    @JsonProperty("longitude")
    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    double longitude;

    @JsonProperty("altitude")
    public int getAltitude() {
        return this.altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    int altitude;
}
