package com.cxy35.sample.springcloud.resilience4j.client.consumer.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@CircuitBreaker(name = "cbA", fallbackMethod = "error") // 测试断路器，服务降级/容错
@Retry(name = "retryA") // 测试请求重试
public class ConsumerService {
    @Autowired
    RestTemplate restTemplate;

    public String testCircuitBreaker() {
        return restTemplate.getForObject("http://resilience4j-client-provider/testCircuitBreaker", String.class);
    }

    public String error(Throwable t) {
        return "error";
    }

    public String testRateLimiter() {
        for (int i = 0; i < 5; i++) {
            restTemplate.getForObject("http://resilience4j-client-provider/testRateLimiter", String.class);
        }
        return "success";
    }

    public String testRetry() {
        return restTemplate.getForObject("http://resilience4j-client-provider/testRetry", String.class);
    }
}
