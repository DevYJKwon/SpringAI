package com.example.springai.tool;

import com.example.springai.dto.WeatherApiRequest;
import com.example.springai.dto.WeatherApiResponse;
import com.example.springai.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class WeatherTools {
    private final WeatherService weatherService;

    @Tool(description = "사용자가 말하고 있는 지역을 유추하거나 입력 받아서 해당 지역에 맞는 위도, 경도와 날짜 범위(startDate ~ endDate)의 날씨 예보를 조회합니다.")
    public WeatherApiResponse getWeather(
            @ToolParam(description = "위도 (예: 서울 37.5665)") double latitude,
            @ToolParam(description = "경도 (예: 서울 126.9780)") double longitude,
            @ToolParam(description = "조회 시작일 (yyyy-MM-dd 형식)") String startDate,
            @ToolParam(description = "조회 종료일 (yyyy-MM-dd 형식)") String endDate
    ) {
        return weatherService.getWeather(WeatherApiRequest.builder()
                .latitude(latitude)
                .longitude(longitude)
                .startDate(LocalDate.parse(startDate))
                .endDate(LocalDate.parse(endDate))
                .build());
    }
}
