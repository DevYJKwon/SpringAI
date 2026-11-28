package com.example.springai.controller;

import com.example.springai.dto.WeatherApiRequest;
import com.example.springai.dto.WeatherApiResponse;
import com.example.springai.service.WeatherService;
import com.example.springai.tool.WeatherTools;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatClient chatClient;
    private final WeatherService weatherService;
    private final WeatherTools weatherTools;
    @Value("classpath:/prompt/weather/system.st")
    private Resource weatherSystemPrompt;


    @GetMapping("/weather")
    public WeatherApiResponse getWeather(LocalDate startDate, LocalDate endDate, double longitude, double latitude){
        return weatherService.getWeather(WeatherApiRequest.builder()
                .longitude(longitude)
                .latitude(latitude)
                .startDate(startDate)
                .endDate(endDate)
                .build());
    }

    @GetMapping(path = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamQuestion(String text){

        return chatClient.prompt()
                .system(s-> s.text(weatherSystemPrompt).param("today",LocalDate.now().toString()))
                .tools(weatherTools)
                .user(text)
                .stream()
                .content();
    }
}
