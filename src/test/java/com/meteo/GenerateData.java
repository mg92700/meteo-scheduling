package com.meteo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteo.model.MeteoEntity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateData {

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<MeteoEntity> meteoDataList = generateMeteoData(1000000);

        try {
            objectMapper.writeValue(new File("src/main/resources/meteo.json"), meteoDataList);
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }

    private static List<MeteoEntity> generateMeteoData(int count) {
        List<MeteoEntity> meteoDataList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            MeteoEntity meteoData = new MeteoEntity();
            meteoData.setCity("City " + i);
            meteoData.setProbarain(String.valueOf(random.nextInt(101)));
            meteoData.setProbafrost(String.valueOf(random.nextInt(101)));
            meteoData.setProbafog(String.valueOf(random.nextInt(101)));
            meteoData.setProbawind70(String.valueOf(random.nextInt(101)));
            meteoData.setProbawind100(String.valueOf(random.nextInt(101)));
            meteoData.setTsoil1(String.valueOf(random.nextInt(101)));
            meteoData.setTemp2m(String.valueOf(random.nextInt(101)));
            meteoData.setInsee("Insee " + i);
            meteoData.setDatesaving(null);

            meteoDataList.add(meteoData);
        }

        return meteoDataList;
    }
}
