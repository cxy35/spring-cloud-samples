package com.cxy35.sample.springcloud.openfeign.client.consumer;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients // 开启 OpenFeign 功能
public class OpenfeignClientConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenfeignClientConsumerApplication.class, args);
    }

    @Bean
    Logger.Level loggerLevel() {
        return Logger.Level.FULL;
    }

}
