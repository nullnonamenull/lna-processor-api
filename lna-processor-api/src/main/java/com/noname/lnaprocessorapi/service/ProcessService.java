package com.noname.lnaprocessorapi.service;

import com.noname.lnaaiintegrationdto.ChatRequest;
import com.noname.lnaaiintegrationdto.Message;
import com.noname.lnaprocessorapi.service.web.IntegrationAIWebService;
import com.noname.lnaprocessorapi.service.web.SessionWebService;
import com.noname.lnaprocessordto.MessageRequestDTO;
import com.noname.lnasessiondto.MessageDTO;
import com.noname.lnasessiondto.enumerated.Role;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcessService {

    private final SessionWebService sessionWebService;
    private final IntegrationAIWebService integrationAIWebService;

    public void process(MessageRequestDTO messageRequestDTO) {
        try {
            var x = sessionWebService.getSessionMessages(messageRequestDTO.getSessionId());
        } catch (Exception e) {
            throw new InternalException("sds", e);
        }
        try {
            sessionWebService.postSessionMessage(MessageDTO.builder()
                    .sessionId(messageRequestDTO.getSessionId())
                    .content(messageRequestDTO.getMessage())
                    .role(Role.USER)
                    .build());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new InternalException("xxx", e);
        }

        try {
            var response = integrationAIWebService.chat(ChatRequest.builder()
                    .model("gpt-4")
                    .messages(List.of(
                            Message.builder()
                                    .role("system")
                                    .content("""
                                            Twoim zadaniem jest kategoryzowanie zapytania do pasującego agenta z listy. Odpowiadasz jednym słowem nazwą agenta.
                                            Mozesz wybrac agenta tylko takiego, ktory znajduje się na liście poniżej.
                                            
                                            
                                            AGENCI:
                                            "CALENDAR_AGENT": "Agent którego zadaniem jest zarządzanie kalendarzem użytkownika. Ten agent ma możliwość, dodawania wydarzeń, uswanie oraz ich modyfikację,
                                            a takze zwracaniem zaplanowanych wydarzeń.",
                                            "COMPLEX_AGENT": "Agent który rozwiązuje złożone zadania, składające się z wielu etapów, lub wymagających doatkowej wiedzy."
                                            
                                            Odpowiadaj tylko jednym słowem jak w przykładzie:
                                            CALENDAR_AGENT
                                            """)
                                    .build(),
                            Message.builder()
                                    .role("user")
                                    .content(messageRequestDTO.getMessage())
                                    .build()))
                    .build());

            System.out.println(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new InternalException("bleeee", e);
        }


//        x.getMessages().forEach(System.out::println);

        System.out.println("elo");
    }
}
