package com.noname.lnaprocessorapi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@RequiredArgsConstructor
public class RestClientConfiguration {

    private final ConfigProperties configProperties;

    @Bean
    public RestClient sessionRestClient(final RestClient.Builder restClientBuilder) {
        return restClientBuilder.baseUrl(configProperties.getExternalUrl().get("session")).build();
    }

    @Bean
    public RestClient integrationAIRestClient(final RestClient.Builder restClientBuilder) {
        return restClientBuilder.baseUrl(configProperties.getExternalUrl().get("integrationAI")).build();
    }

}