package com.meteo.batch.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventSender {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public EventSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendEventAlert(String message) {
        rabbitTemplate.convertAndSend("alerte-email", message);
    }
}