package com.noname.lnaprocessorapi.queue;


import com.noname.lnaprocessordto.MessageRequestDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    @RabbitListener(queues = "queue_1")
    public void receiveMessage(MessageRequestDTO messageRequestDTO) {
        System.out.println(messageRequestDTO.getMessage());
    }
}