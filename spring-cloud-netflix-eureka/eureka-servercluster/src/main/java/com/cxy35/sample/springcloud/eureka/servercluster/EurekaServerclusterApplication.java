package com.cxy35.sample.springcloud.eureka.servercluster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerclusterApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerclusterApplication.class, args);
    }

}
