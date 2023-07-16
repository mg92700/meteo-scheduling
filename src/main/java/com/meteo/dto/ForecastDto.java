package com.meteo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ForecastDto {
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

    @JsonProperty("datetime")
    public Date getDatetime() {
        return this.datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    Date datetime;

    @JsonProperty("temp2m")
    public int getTemp2m() {
        return this.temp2m;
    }

    public void setTemp2m(int temp2m) {
        this.temp2m = temp2m;
    }

    int temp2m;

    @JsonProperty("rh2m")
    public int getRh2m() {
        return this.rh2m;
    }

    public void setRh2m(int rh2m) {
        this.rh2m = rh2m;
    }

    int rh2m;

    @JsonProperty("wind10m")
    public int getWind10m() {
        return this.wind10m;
    }

    public void setWind10m(int wind10m) {
        this.wind10m = wind10m;
    }

    int wind10m;

    @JsonProperty("gust10m")
    public int getGust10m() {
        return this.gust10m;
    }

    public void setGust10m(int gust10m) {
        this.gust10m = gust10m;
    }

    int gust10m;

    @JsonProperty("dirwind10m")
    public int getDirwind10m() {
        return this.dirwind10m;
    }

    public void setDirwind10m(int dirwind10m) {
        this.dirwind10m = dirwind10m;
    }

    int dirwind10m;

    @JsonProperty("rr10")
    public int getRr10() {
        return this.rr10;
    }

    public void setRr10(int rr10) {
        this.rr10 = rr10;
    }

    int rr10;

    @JsonProperty("rr1")
    public int getRr1() {
        return this.rr1;
    }

    public void setRr1(int rr1) {
        this.rr1 = rr1;
    }

    int rr1;

    @JsonProperty("probarain")
    public int getProbarain() {
        return this.probarain;
    }

    public void setProbarain(int probarain) {
        this.probarain = probarain;
    }

    int probarain;

    @JsonProperty("weather")
    public int getWeather() {
        return this.weather;
    }

    public void setWeather(int weather) {
        this.weather = weather;
    }

    int weather;

    @JsonProperty("probafrost")
    public int getProbafrost() {
        return this.probafrost;
    }

    public void setProbafrost(int probafrost) {
        this.probafrost = probafrost;
    }

    int probafrost;

    @JsonProperty("probafog")
    public int getProbafog() {
        return this.probafog;
    }

    public void setProbafog(int probafog) {
        this.probafog = probafog;
    }

    int probafog;

    @JsonProperty("probawind70")
    public int getProbawind70() {
        return this.probawind70;
    }

    public void setProbawind70(int probawind70) {
        this.probawind70 = probawind70;
    }

    int probawind70;

    @JsonProperty("probawind100")
    public int getProbawind100() {
        return this.probawind100;
    }

    public void setProbawind100(int probawind100) {
        this.probawind100 = probawind100;
    }

    int probawind100;

    @JsonProperty("tsoil1")
    public int getTsoil1() {
        return this.tsoil1;
    }

    public void setTsoil1(int tsoil1) {
        this.tsoil1 = tsoil1;
    }

    int tsoil1;

    @JsonProperty("tsoil2")
    public int getTsoil2() {
        return this.tsoil2;
    }

    public void setTsoil2(int tsoil2) {
        this.tsoil2 = tsoil2;
    }

    int tsoil2;

    @JsonProperty("gustx")
    public int getGustx() {
        return this.gustx;
    }

    public void setGustx(int gustx) {
        this.gustx = gustx;
    }

    int gustx;

    @JsonProperty("iso0")
    public int getIso0() {
        return this.iso0;
    }

    public void setIso0(int iso0) {
        this.iso0 = iso0;
    }

    int iso0;
}

