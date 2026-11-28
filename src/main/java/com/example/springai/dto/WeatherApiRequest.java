package com.example.springai.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record WeatherApiRequest(double longitude,
                                double latitude,
                                LocalDate startDate,
                                LocalDate endDate
                                ) {
}
