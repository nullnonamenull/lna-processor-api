package com.noname.lnaprocessorapi.queue;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageProducer {

    private static final String EXCHANGE = "exex";
    private static final String ROUTING_KEY = "routingkey";

    private final RabbitTemplate rabbitTemplate;

    public void send(final String message) {
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, message);
    }

}