package com.example.springai.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "weather.api")
public record WeatherApiProperties(String baseUrl,
                                   String model,
                                   List<String> daily,
                                   List<String> hourly,
                                   String timezone) {
}
