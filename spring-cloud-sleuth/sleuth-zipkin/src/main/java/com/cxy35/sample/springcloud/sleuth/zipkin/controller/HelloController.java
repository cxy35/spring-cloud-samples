package com.cxy35.sample.springcloud.sleuth.zipkin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author cxy35
 * @Date 2020/5/17 17:32
 */
@RestController
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    public String hello(String name) {
        logger.info("sleuth-zipkin-hello");
        return "hello " + name + " !";
    }
}
