package com.cxy35.sample.springcloud.consul.client.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // 开启服务发现的功能
public class ConsulClientProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulClientProviderApplication.class, args);
    }

}
