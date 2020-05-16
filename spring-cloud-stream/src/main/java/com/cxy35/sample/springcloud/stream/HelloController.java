package com.cxy35.sample.springcloud.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author cxy35
 * @Date 2020/5/16 17:15
 */
@RestController
public class HelloController {
    public final static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    MyChannel myChannel;

    @GetMapping("/hello")
    public void hello() {
        myChannel.output().send(MessageBuilder.withPayload("Hello Stream").build());
    }

    @GetMapping("/hello2")
    public void hello2() {
        logger.info("send msg:" + new Date());
        myChannel.output().send(MessageBuilder.withPayload("Hello Stream 2").setHeader("x-delay", 3000).build());
    }
}
