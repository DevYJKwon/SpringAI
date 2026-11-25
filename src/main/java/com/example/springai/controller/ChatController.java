package com.example.springai.controller;

import com.example.springai.model.ActorFilms;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatClient chatClient;

    public ResponseEntity<?> getModel(){
        return null;
    }

    @GetMapping("/ask")
    public ChatResponse question(String text){
        return chatClient.prompt()
                .user(text)
                .call()
                .chatResponse();
    }

    @GetMapping("/movies")
    public ActorFilms movieQuestion(){

        return chatClient.prompt()
                .user("랜덤한 배우의 필모그래피 만들어줘")
                .call()
                .entity(ActorFilms.class);
    }

    @GetMapping(path = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamQuestion(String text){

        return chatClient.prompt()
                .user(text)
                .stream()
                .content();
    }
}
