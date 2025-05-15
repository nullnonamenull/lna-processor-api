package com.noname.lnaprocessorapi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Configuration
@ConfigurationProperties("properties")
public class ConfigProperties {

    private Map<String, String> externalUrl = new HashMap<>();

}