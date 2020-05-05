package com.cxy35.sample.springcloud.gateway.client.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayClientConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayClientConsumerApplication.class, args);
    }

    /*@Bean
    RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("cxy35_route", r ->
                        r.path("/get").uri("http://httpbin.org")) // 这是一个测试的地址
                .build();
    }*/
}
