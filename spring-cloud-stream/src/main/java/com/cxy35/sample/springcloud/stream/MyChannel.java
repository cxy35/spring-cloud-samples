package com.cxy35.sample.springcloud.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @Author cxy35
 * @Date 2020/5/16 17:11
 */
// 自定义消息通道
public interface MyChannel {
    String INPUT = "cxy35-input";
    String OUTPUT = "cxy35-output";

    @Output(OUTPUT)
    MessageChannel output();

    @Input(INPUT)
    SubscribableChannel input();
}
