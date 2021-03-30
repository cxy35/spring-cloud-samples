package com.cxy35.sample.springcloud.hystrix.client.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HystrixClientProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixClientProviderApplication.class, args);
    }

}
