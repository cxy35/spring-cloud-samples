package com.cxy35.sample.springcloud.zuul.client.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy // 开启网关代理
public class ZuulClientConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulClientConsumerApplication.class, args);
    }

}
