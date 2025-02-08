package com.meteo.batch.controllers;

import com.meteo.batch.services.EventSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api")
public class MessageController {

    private final EventSender eventSender;
    private final Random random = new Random();

    public MessageController(EventSender eventSender) {
        this.eventSender = eventSender;
    }

    @GetMapping("/sendMessage")
    public String sendMessage(@RequestParam String message) {
        eventSender.sendEventAlert("Message: " + message);
        return "Message sent.";

    }


}