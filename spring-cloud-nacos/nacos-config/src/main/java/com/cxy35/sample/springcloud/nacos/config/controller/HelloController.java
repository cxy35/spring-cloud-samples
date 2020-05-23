package com.cxy35.sample.springcloud.nacos.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author cxy35
 * @Date 2020/5/21 10:48
 */
@RestController
@RefreshScope
public class HelloController {
    @Value("${name}")
    String name;

    @GetMapping("/hello")
    public String hello() {
        return "hello : " + name;
    }
}
