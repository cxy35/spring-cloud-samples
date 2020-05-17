package com.cxu35.sample.springcloud.sleuth.zipkin2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SleuthZipkin2Application {

    public static void main(String[] args) {
        SpringApplication.run(SleuthZipkin2Application.class, args);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
