package com.example.springai.service;

import com.example.springai.config.WeatherApiProperties;
import com.example.springai.dto.WeatherApiRequest;
import com.example.springai.dto.WeatherApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final RestClient weatherRestClient;
    private final WeatherApiProperties weatherApiProperties;

    public WeatherApiResponse getWeather(WeatherApiRequest request){
        return weatherRestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/forecast")
                        .queryParam("latitude",request.latitude())
                        .queryParam("longitude",request.longitude())
                        .queryParam("start_date",request.startDate())
                        .queryParam("end_date",request.endDate())
                        .queryParam("daily", String.join(",", weatherApiProperties.daily()))
                        .queryParam("hourly", String.join(",", weatherApiProperties.hourly()))
                        .queryParam("models", weatherApiProperties.model())
                        .queryParam("timezone", weatherApiProperties.timezone())
                        .build())
                .retrieve()
                .body(WeatherApiResponse.class);
    }
}
