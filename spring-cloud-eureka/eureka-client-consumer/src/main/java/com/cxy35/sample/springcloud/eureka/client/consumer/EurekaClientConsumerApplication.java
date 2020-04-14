package com.cxy35.sample.springcloud.eureka.client.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EurekaClientConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientConsumerApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @LoadBalanced // 开启负载均衡
    RestTemplate restTemplateLoadBalanced() {
        return new RestTemplate();
    }
}
