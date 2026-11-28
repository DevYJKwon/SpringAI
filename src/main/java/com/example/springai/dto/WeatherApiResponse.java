package com.example.springai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record WeatherApiResponse(
        double latitude,
        double longitude,
        String timezone,
        Daily daily,
        Hourly hourly
) {
    public record Daily(
            List<String> time,
            @JsonProperty("weather_code") List<Integer> weatherCode,
            @JsonProperty("temperature_2m_max") List<Double> temperature2mMax,
            @JsonProperty("temperature_2m_min") List<Double> temperature2mMin,
            @JsonProperty("rain_sum") List<Double> rainSum,
            @JsonProperty("snowfall_sum") List<Double> snowfallSum
    ) {}

    public record Hourly(
            List<String> time,
            @JsonProperty("temperature_2m") List<Double> temperature2m,
            @JsonProperty("weather_code") List<Integer> weatherCode,
            List<Double> rain,
            List<Double> snowfall,
            @JsonProperty("wind_speed_10m") List<Double> windSpeed10m,
            @JsonProperty("apparent_temperature") List<Double> apparentTemperature,
            @JsonProperty("is_day") List<Integer> isDay
    ) {}
}
