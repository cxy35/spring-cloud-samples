package com.cxy35.sample.springcloud.resilience4j.client.provider.controller;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ProviderController {
    @Value("${server.port}")
    Integer port; // 支持启动多个实例，做负载均衡，用端口区分

    @GetMapping("/hello")
    public String hello() {
        return "hello cxy35: " + port;
    }

    @GetMapping("/testCircuitBreaker")
    public String testCircuitBreaker() {
        String s = "hello cxy35:" + port;
        System.out.println(s);
        int i = 1 / 0;
        return "testCircuitBreaker: " + s;
    }

    @GetMapping("/testRateLimiter")
    @RateLimiter(name = "rlA") // 测试限流
    public String testRateLimiter() {
        String s = "hello cxy35:" + port;
        System.out.println(new Date());
        return "testRateLimiter: " + s;
    }

    @GetMapping("/testRetry")
    public String testRetry() {
        String s = "hello cxy35:" + port;
        System.out.println(s);
        int i = 1 / 0;
        return "testRetry: " + s;
    }

}
