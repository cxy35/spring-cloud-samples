package com.cxy35.sample.springcloud.hystrix.client.consumer.controller;

import com.cxy35.sample.springcloud.common.User;
import com.cxy35.sample.springcloud.hystrix.client.consumer.service.ConsumerService;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

// 注解方式
@RestController
public class ConsumerController {
    @Autowired
    ConsumerService consumerService;
    @Autowired
    RestTemplate restTemplate;

    // 服务降级/容错
    @GetMapping("/testHystrix")
    public String testHystrix() {
        return consumerService.testHystrix();
    }

    // 异步调用
    @GetMapping("/testHystrixAsync")
    public String testHystrixAsync() {
        Future<String> future = consumerService.testHystrixAsync();
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "";
    }

    // 异常处理
    @GetMapping("/testHystrixException")
    public String testHystrixException() {
        return consumerService.testHystrixException();
    }

    // 请求缓存
    @GetMapping("/testHystrixCache")
    public void testHystrixCache() {
        // 开启缓存
        // 缓存默认不会生效，我们使用缓存，都有一个缓存生命周期这样一个概念。
        // 需要初始化 HystrixRequestContext，初始化完成后，缓存开始生效。close 之后，缓存失效。
        HystrixRequestContext ctx = HystrixRequestContext.initializeContext();

        // 第一请求完，数据已经缓存下来了
        String cxy35 = consumerService.testHystrixCache("cxy35");
        System.out.println(cxy35);

        // 删除数据，同时缓存中的数据也会被删除
        consumerService.deleteUserByName("cxy35");

        // 第二次请求时，直接使用缓存数据，不会再调用 provider 。除非中间调用了 deleteUserByName 清除掉缓存
        cxy35 = consumerService.testHystrixCache("cxy35");
        System.out.println(cxy35);

        // 关闭缓存
        // 在 ctx close 之前，缓存是有效的，close 之后，缓存就失效了。
        // 访问一次本接口，provider 只会被调用一次（第二次使用的缓存，除非中间调了清除缓存的接口，如 deleteUserByName）。
        ctx.close();
    }

    // 请求合并
    @GetMapping("/testHystrixCollapser")
    public void testHystrixCollapser() throws ExecutionException, InterruptedException {
        HystrixRequestContext ctx = HystrixRequestContext.initializeContext();

        // 这3个请求会一起发起
        Future<User> q1 = consumerService.testHystrixCollapser(99);
        Future<User> q2 = consumerService.testHystrixCollapser(98);
        Future<User> q3 = consumerService.testHystrixCollapser(97);
        User u1 = q1.get();
        User u2 = q2.get();
        User u3 = q3.get();
        System.out.println(u1);
        System.out.println(u2);
        System.out.println(u3);

        Thread.sleep(2000);

        // 这个请求会单独发起
        Future<User> q4 = consumerService.testHystrixCollapser(96);
        User u4 = q4.get();
        System.out.println(u4);

        ctx.close();
    }
}
