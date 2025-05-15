//package com.noname.lnaprocessorapi.service.web;
//
//import com.noname.lnaaiintegrationdto.ChatRequest;
//import com.noname.lnaaiintegrationdto.ChatResponse;
//import com.noname.lnaprocessorapi.config.ConfigProperties;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestClient;
//
//@Service
//public class IntegrationAIWebService {
//
//    private final RestClient restClient;
//    private final ConfigProperties configProperties;
//
//    public IntegrationAIWebService(@Qualifier("integrationAIRestClient") final RestClient restClient,
//                                   final ConfigProperties configProperties) {
//        this.restClient = restClient;
//        this.configProperties = configProperties;
//    }
//
//
//    public ChatResponse chat(ChatRequest chatRequest) {
//        return restClient
//                .post()
//                .uri(configProperties.getExternalUrl().get("integrationAI") + "/chat")
//                .body(chatRequest)
//                .retrieve()
//                .body(new ParameterizedTypeReference<>() {
//                });
//    }
//}
