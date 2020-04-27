package com.cxy35.sample.springcloud.resilience4j.client.consumer.controller;

import com.cxy35.sample.springcloud.resilience4j.client.consumer.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {
    @Autowired
    ConsumerService consumerService;

    @GetMapping("/testCircuitBreaker")
    public String testCircuitBreaker() {
        return consumerService.testCircuitBreaker();
    }

    @GetMapping("/testRateLimiter")
    public String testRateLimiter() {
        return consumerService.testRateLimiter();
    }

    @GetMapping("/testRetry")
    public String testRetry() {
        return consumerService.testRetry();
    }
}
