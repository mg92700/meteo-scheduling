package com.meteo.utils;




import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteo.dto.RootDto;
import com.meteo.model.MeteoEntity;
import com.meteo.services.meteo.MeteoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
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


@Component
@EnableScheduling
@Slf4j
public class SchedulingMeteo {


    @Autowired
    MeteoService meteoService;

    @Autowired
    Environment env;

    private static final String USER_AGENT = "Mozilla/5.0";

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    //@Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
        log.info("Début traitement le {}", dateFormat.format(new Date()));
        try {
            Thread.sleep(30);
            sendGet();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("Fin traitement le {}", dateFormat.format(new Date()));

    }
    private List<String> cities() {
        List<String> list = new ArrayList<>();
        list.add("93031");
        list.add("92025");
        list.add("93078");
        return list;
    }

    private void sendGet() throws IOException {
        for (String city : cities()) {
            String urlApi = env.getProperty("api.url") + env.getProperty("api.token") + "&insee=" + city;
            URL obj = new URL(urlApi);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = con.getResponseCode();
            log.info("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                ObjectMapper om = new ObjectMapper();
                RootDto root = om.readValue(response.toString(), RootDto.class);
                MeteoEntity meteo = new MeteoEntity(root,city);
                meteoService.saveDepartment(meteo);
                // print result
                log.info(meteo.toString());

            } else {
                log.info("GET request not worked");
            }
        }
    }

}





