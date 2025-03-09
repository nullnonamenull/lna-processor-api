package com.noname.lnaprocessorapi.queue;


import com.noname.lnaprocessordto.MessageRequestDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class MessageListener {


    @RabbitListener(queues = "queue_1")  // same queue name as in your definitions.json
    public void receiveMessage(MessageRequestDTO messageRequestDTO) {
        System.out.println(messageRequestDTO.getMessage());
        // Or simply do:
        // System.out.println("Received: " + messageRequestDTO);

        // Add your logic here, e.g., store in DB, call another service, etc.
    }
}