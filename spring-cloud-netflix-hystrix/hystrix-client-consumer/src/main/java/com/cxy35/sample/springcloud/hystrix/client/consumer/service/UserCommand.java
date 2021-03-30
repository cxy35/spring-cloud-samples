package com.cxy35.sample.springcloud.hystrix.client.consumer.service;

import com.cxy35.sample.springcloud.common.User;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

import java.util.List;

// 请求命令方式，实现请求合并
public class UserCommand extends HystrixCommand<List<User>> {
    private List<Integer> ids;
    private UserService userService;

    public UserCommand(List<Integer> ids, UserService userService) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("userCommandGroupKey")).andCommandKey(HystrixCommandKey.Factory.asKey("userCommandKey")));
        this.ids = ids;
        this.userService = userService;
    }

    @Override
    protected List<User> run() throws Exception {
        return userService.getUsersByIds(ids);
    }

    @Override
    protected List<User> getFallback() {
        return null;
    }
}
