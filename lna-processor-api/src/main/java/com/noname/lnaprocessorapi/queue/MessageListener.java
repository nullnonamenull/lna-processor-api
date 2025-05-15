package com.noname.lnaprocessorapi.queue;


import com.noname.lnaprocessorapi.service.ProcessService;
import com.noname.lnaprocessordto.MessageRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageListener {

    private final ProcessService processService;

    @RabbitListener(queues = "queue_1")
    public void receiveMessage(final MessageRequestDTO messageRequestDTO) {
        try {
            System.out.println(messageRequestDTO.getMessage());
            System.out.println(messageRequestDTO.getSessionId());

            processService.process(messageRequestDTO);
        } catch (Exception e) {
            System.err.println("Błąd podczas przetwarzania wiadomości: " + e.getMessage());
        }
    }

}