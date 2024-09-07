package com.meteo.batch.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;

public class RootDto {
    @JsonProperty("city")
    public CityDto getCity() {
        return this.cityDto; }
    public void setCity(CityDto cityDto) {
        this.cityDto = cityDto; }
    CityDto cityDto;
    @JsonProperty("update")
    public Date getUpdate() {
        return this.update; }
    public void setUpdate(Date update) {
        this.update = update; }
    Date update;
    @JsonProperty("forecast")
    public ArrayList<ForecastDto> getForecast() {
        return this.forecastDto; }
    public void setForecast(ArrayList<ForecastDto> forecastDto) {
        this.forecastDto = forecastDto; }
    ArrayList<ForecastDto> forecastDto;
}
