package com.cxy35.sample.springcloud.bus.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer // 开启 Conﬁg Server 功能
public class BusConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusConfigServerApplication.class, args);
    }

}
