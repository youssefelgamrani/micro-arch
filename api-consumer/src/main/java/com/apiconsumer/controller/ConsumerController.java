package com.apiconsumer.controller;

import com.alibaba.csp.sentinel.adapter.reactor.SentinelReactorTransformer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

  @Autowired
  private WebClient.Builder webClientBuilder;

  @GetMapping
  @SentinelResource(value = "getConsumer", fallback = "getCustomerFallBack")
  public Mono<String> getConsumer() {
    return webClientBuilder
        .build()
        .get()
        .uri("http://api-producer/producer")
        .retrieve()
        .bodyToMono(String.class)
        .transform(new SentinelReactorTransformer<>("otherResourceName"));
  }

  public String getCustomerFallBack(Throwable t) {
    return "default_fallback";
  }
}
