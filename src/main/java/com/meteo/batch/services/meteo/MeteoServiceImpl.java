package com.meteo.batch.services.meteo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteo.batch.dto.RootDto;
import com.meteo.batch.repository.MeteoRepository;
import com.meteo.batch.model.MeteoEntity;
import com.meteo.batch.services.cities.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@Slf4j
public class MeteoServiceImpl implements MeteoService {

    @Autowired
    MeteoRepository meteoRepository;

    @Autowired
    CityService cityService;

    @Autowired
    Environment env;

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    @Override
    @Transactional
    public MeteoEntity saveDepartment(MeteoEntity meteoEntity) {
        return meteoRepository.save(meteoEntity);
    }

    @Override
    @Transactional
    public List<MeteoEntity> all() {
        return meteoRepository.findAll();
    }

    @Override
    public void saveAll(List<MeteoEntity> meteoEntities) {
        meteoRepository.saveAll(meteoEntities);
    }

    @Override
    public MeteoEntity getMeteoForCity(String insee) throws IOException {
        String urlApi = env.getProperty("api.url") + env.getProperty("api.token") + "&insee=" + insee;
        URL obj = new URL(urlApi);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        MeteoEntity meteo = null;
        try {
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            log.info("GET Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    StringBuffer response = new StringBuffer();
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    RootDto root = objectMapper.readValue(response.toString(), RootDto.class);
                    meteo = new MeteoEntity(root, insee);
                    log.info(meteo.toString());
                }
            } else {
                log.info("GET request not worked for city {}", insee);
            }
        } finally {
            con.disconnect();
        }
        return meteo;
    }

}
