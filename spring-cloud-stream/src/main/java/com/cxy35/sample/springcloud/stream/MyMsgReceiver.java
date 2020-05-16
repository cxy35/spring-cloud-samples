package com.cxy35.sample.springcloud.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.Date;

/**
 * @Author cxy35
 * @Date 2020/5/16 17:12
 */
@EnableBinding(MyChannel.class) // @EnableBinding 表示绑定 MyMsgReceiver 这个自定义的消息通道
public class MyMsgReceiver {
    public final static Logger logger = LoggerFactory.getLogger(MyMsgReceiver.class);

    @StreamListener(MyChannel.INPUT)
    public void receive(Object payload) {
        logger.info("MyMsgReceiver: " + payload + " >>> " + new Date());
    }
}
