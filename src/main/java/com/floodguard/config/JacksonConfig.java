package com.floodguard.config;

import org.n52.jackson.datatype.jts.JtsModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {
    // For the API to return JSON that Leaflet.js can understand (GeoJSON format)
    @Bean
    public JtsModule jtsModule() {
        return new JtsModule();
    }
}
