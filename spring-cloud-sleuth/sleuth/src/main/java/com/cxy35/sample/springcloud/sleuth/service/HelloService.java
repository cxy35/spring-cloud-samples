package com.cxy35.sample.springcloud.sleuth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @Author cxy35
 * @Date 2020/5/17 16:07
 */
@Service
public class HelloService {
    private static final Logger logger = LoggerFactory.getLogger(HelloService.class);

    @Async
    public String backgroundFun() {
        logger.info("backgroundFun");
        return "backgroundFun";
    }

    @Scheduled(cron = "0/10 * * * * ?")
    public void sche1() {
        logger.info("start:");
        backgroundFun();
        logger.info("end:");
    }
}
