package com.meteo.batch.controllers;

import com.meteo.batch.services.MessageSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class MessageController {

    private final MessageSender messageSender;
    private final Random random = new Random();

    public MessageController(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @GetMapping("/sendMessage")
    public String sendMessage(@RequestParam String message) {
        for (int i = 0; i < 1000; i++) {
            String randomMessage = generateRandomMessage(2501);
            messageSender.sendEmailAlert(randomMessage);
        }
        return "1 million messages sent.";
    }

    private String generateRandomMessage(int length) {
        byte[] array = new byte[length];
        random.nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }
}