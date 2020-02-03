package com.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Value("${msg:Config Server is not working. Please check...}")
    public String message;

    @RequestMapping
    public Mono<String> getMessages(){
      return Mono.just(message);
    }
}
