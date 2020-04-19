package com.cxy35.sample.springcloud.hystrix.client.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCircuitBreaker // 开启断路器的功能
// @SpringCloudApplication // 组合注解
public class HystrixClientConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixClientConsumerApplication.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
