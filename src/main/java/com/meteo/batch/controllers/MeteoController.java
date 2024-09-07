package com.meteo.batch.controllers;

import com.meteo.batch.model.MeteoEntity;
import com.meteo.batch.services.meteo.MeteoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/meteo-batch")
@Slf4j
public class MeteoController {


   private final MeteoService meteoService;

    public MeteoController(MeteoService meteoService) {
        this.meteoService = meteoService;
    }

    @GetMapping(path = "/all")
    public @ResponseBody List<MeteoEntity> getAllMeteo() {
        log.info("Request all meteo");
        return meteoService.all();
    }

    @GetMapping(path ="/byInsee")
    public @ResponseBody Optional<MeteoEntity> geMeteoByInsee(@RequestParam String location) {
        log.info("Request all byInsee");
        return meteoService.all().stream()
                .filter(c -> c.getInsee().equals(location))
                .sorted(Comparator.comparing(MeteoEntity::getDatesaving).reversed())
                .findFirst();
    }

}
