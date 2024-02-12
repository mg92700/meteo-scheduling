package com.meteo.utils;




import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteo.dto.RootDto;
import com.meteo.model.MeteoEntity;
import com.meteo.services.cities.CityService;
import com.meteo.services.meteo.MeteoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@EnableScheduling
@Slf4j
public class SchedulingMeteo {

    @Autowired
    MeteoService meteoService;

    @Autowired
    CityService cityService;

    @Autowired
    Environment env;

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
        log.info("Début traitement le {}", dateFormat.format(new Date()));
        try {
            sendGetAsync();
        } catch (Exception e) {
            log.error("Une erreur s'est produite lors de l'appel à l'API météo : {}", e.getMessage());
        }
        log.info("Fin traitement le {}", dateFormat.format(new Date()));
    }

    private void sendGetAsync() {
        List<String> cities = cityService.GetAllByInsee();
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (String city : cities) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    sendGetForCity(city);
                } catch (IOException e) {
                    log.error("Une erreur s'est produite lors de l'appel à l'API météo pour la ville {}: {}", city, e.getMessage());
                }
            });
            futures.add(future);
        }

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        allOf.join(); // Attendre la fin de toutes les tâches asynchrones
    }

    public void sendGetForCity(String insee) throws IOException {
        String urlApi = env.getProperty("api.url") + env.getProperty("api.token") + "&insee=" + insee;
        URL obj = new URL(urlApi);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

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
                    MeteoEntity meteo = new MeteoEntity(root, insee);
                    meteoService.saveDepartment(meteo);
                    log.info(meteo.toString());
                }
            } else {
                log.info("GET request not worked for city {}", insee);
            }
        } finally {
            con.disconnect();
        }
    }
}

