package com.api.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Value;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/producer")
public class ProducerController {

    @Value("${msg:default value}")
    private String product;

    @GetMapping
    @SentinelResource("product")
    public Mono<String> getProduct(){
        return Mono.just(product);
    }
}
