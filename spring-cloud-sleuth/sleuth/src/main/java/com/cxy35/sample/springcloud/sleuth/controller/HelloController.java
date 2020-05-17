package com.cxy35.sample.springcloud.sleuth.controller;

import com.cxy35.sample.springcloud.sleuth.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author cxy35
 * @Date 2020/5/17 15:49
 */
@RestController
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    HelloService helloService;

    @GetMapping("/hello")
    public String hello() {
        logger.info("Hello Spring Cloud Sleuth");
        return "Hello Spring Cloud Sleuth";
    }

    @GetMapping("/hello2")
    public String hello2() throws InterruptedException {
        logger.info("hello2");
        Thread.sleep(500);
        return restTemplate.getForObject("http://127.0.0.1:8080/hello3", String.class);
    }

    @GetMapping("/hello3")
    public String hello3() throws InterruptedException {
        logger.info("hello3");
        Thread.sleep(500);
        return "hello3";
    }

    @GetMapping("/hello4")
    public String hello4() {
        logger.info("hello4");
        helloService.backgroundFun();
        return "hello4";
    }
}
