package com.cxy35.sample.springcloud.hystrix.client.consumer.controller;

import com.cxy35.sample.springcloud.common.User;
import com.cxy35.sample.springcloud.hystrix.client.consumer.service.ConsumerService2;
import com.cxy35.sample.springcloud.hystrix.client.consumer.service.UserCollapser;
import com.cxy35.sample.springcloud.hystrix.client.consumer.service.UserService;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

// 请求命令方式
@RestController
public class ConsumerController2 {
    @Autowired
    RestTemplate restTemplate;

    // 服务降级/容错
    @GetMapping("/testHystrix2")
    public String testHystrix2() {
        // 1. 直接执行
        ConsumerService2 command = new ConsumerService2(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("cxy35")), restTemplate);
        return command.execute();
    }

    // 异步调用
    @GetMapping("/testHystrixAsync2")
    public String testHystrixAsync2() {
        // 2. 先入队，后执行
        ConsumerService2 command = new ConsumerService2(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("cxy35")), restTemplate);
        try {
            Future<String> queue = command.queue();
            return queue.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "";
    }

    // 异常处理
    @GetMapping("/testHystrixException2")
    public String testHystrixException2() {
        ConsumerService2 command = new ConsumerService2(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("cxy35")), restTemplate);
        return command.execute();
    }

    // 请求缓存
    @GetMapping("/testHystrixCache2")
    public void testHystrixCache2() {
        // 开启缓存
        // 缓存默认不会生效，我们使用缓存，都有一个缓存生命周期这样一个概念。
        // 需要初始化 HystrixRequestContext，初始化完成后，缓存开始生效。close 之后，缓存失效。
        HystrixRequestContext ctx = HystrixRequestContext.initializeContext();

        // 第一请求完，数据已经缓存下来了
        ConsumerService2 command = new ConsumerService2(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("cxy35")), restTemplate, "cxy35");
        String r = command.execute();
        System.out.println(r);

        // 第二次请求时，直接使用缓存数据，不会再调用 provider 。除非中间调用了 deleteUserByName 清除掉缓存
        command = new ConsumerService2(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("cxy35")), restTemplate, "cxy35");
        r = command.execute();
        System.out.println(r);

        // 关闭缓存
        // 在 ctx close 之前，缓存是有效的，close 之后，缓存就失效了。
        // 访问一次本接口，provider 只会被调用一次（第二次使用的缓存，除非中间调了清除缓存的接口，如 deleteUserByName）。
        ctx.close();
    }

    @Autowired
    UserService userService;

    // 请求合并
    @GetMapping("/testHystrixCollapser2")
    public void testHystrixCollapser2() throws ExecutionException, InterruptedException {
        HystrixRequestContext ctx = HystrixRequestContext.initializeContext();

        // 这3个请求会一起发起
        UserCollapser collapser1 = new UserCollapser(userService, 99);
        UserCollapser collapser2 = new UserCollapser(userService, 98);
        UserCollapser collapser3 = new UserCollapser(userService, 97);
        Future<User> q1 = collapser1.queue();
        Future<User> q2 = collapser2.queue();
        Future<User> q3 = collapser3.queue();
        User u1 = q1.get();
        User u2 = q2.get();
        User u3 = q3.get();
        System.out.println(u1);
        System.out.println(u2);
        System.out.println(u3);

        Thread.sleep(2000);

        // 这个请求会单独发起
        UserCollapser collapser4 = new UserCollapser(userService, 96);
        Future<User> q4 = collapser4.queue();
        User u4 = q4.get();
        System.out.println(u4);

        ctx.close();
    }
}
