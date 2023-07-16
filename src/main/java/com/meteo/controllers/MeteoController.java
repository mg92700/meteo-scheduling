package com.meteo.controllers;

import com.meteo.model.MeteoEntity;
import com.meteo.services.meteo.MeteoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/meteo")
@Slf4j
public class MeteoController {

    @Autowired
    MeteoService meteoService;

    @GetMapping(path = "/all")
    public @ResponseBody List<MeteoEntity> getAllMeteo() {
        log.info("Request all meteo");
        return meteoService.all();
    }
}
