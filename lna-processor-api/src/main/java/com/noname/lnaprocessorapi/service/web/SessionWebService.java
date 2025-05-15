package com.noname.lnaprocessorapi.service.web;

import com.noname.lnaprocessorapi.config.ConfigProperties;
import com.noname.lnasessiondto.MessageDTO;
import com.noname.lnasessiondto.SessionMessagesResponseDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Service
public class SessionWebService {

    private final RestClient restClient;
    private final ConfigProperties configProperties;

    public SessionWebService(@Qualifier("sessionRestClient") final RestClient restClient,
                             final ConfigProperties configProperties) {
        this.restClient = restClient;
        this.configProperties = configProperties;
    }

    public SessionMessagesResponseDTO getSessionMessages(final UUID sessionId) {
        return restClient
                .get()
                .uri(configProperties.getExternalUrl().get("session") + "/{sessionId}/message", uriBuilder ->
                        uriBuilder
                                .queryParam("sessionId", sessionId)
                                .build(sessionId))
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    public void postSessionMessage(final MessageDTO messageDTO) {
        restClient
                .post()
                .uri(configProperties.getExternalUrl().get("session") + "/message/")
                .body(messageDTO)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }
}