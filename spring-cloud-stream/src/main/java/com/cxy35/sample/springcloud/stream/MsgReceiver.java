package com.cxy35.sample.springcloud.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import java.util.Date;

/**
 * @Author cxy35
 * @Date 2020/5/16 17:04
 */
@EnableBinding(Sink.class) // @EnableBinding 表示绑定 Sink 这个默认的消息通道
public class MsgReceiver {
    public final static Logger logger = LoggerFactory.getLogger(MsgReceiver.class);

    @StreamListener(Sink.INPUT)
    public void receive(Object payload) {
        logger.info("MsgReceiver: " + payload + " >>> " + new Date());
    }
}
