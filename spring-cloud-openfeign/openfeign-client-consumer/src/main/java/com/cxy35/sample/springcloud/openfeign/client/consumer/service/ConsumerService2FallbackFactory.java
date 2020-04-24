package com.cxy35.sample.springcloud.openfeign.client.consumer.service;

import com.cxy35.sample.springcloud.common.User;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class ConsumerService2FallbackFactory implements FallbackFactory<ConsumerService2> {
    @Override
    public ConsumerService2 create(Throwable throwable) {
        return new ConsumerService2() {
            @Override
            public String hello() {
                return "error-fallbackFactory";
            }

            @Override
            public String hello2(String name) {
                return "error2-fallbackFactory";
            }

            @Override
            public User addUser(User user) {
                return null;
            }

            @Override
            public void deleteUser(Integer id) {
            }

            @Override
            public void getUserByName(String name) throws UnsupportedEncodingException {
            }
        };
    }
}
