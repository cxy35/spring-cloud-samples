package com.cxy35.sample.springcloud.hystrix.client.consumer.service;

import com.cxy35.sample.springcloud.common.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

// 注解方式
@Service
public class ConsumerService {
    @Autowired
    RestTemplate restTemplate;

    /**
     * 在这个方法中，我们将发起一个远程调用，去调用 hystrix-client-provider 中提供的 /hello 接口
     * <p>
     * 但是，这个调用可能会失败，可以采用关闭 hystrix-client-provider 来模拟。
     * <p>
     * 我们在这个方法上添加 @HystrixCommand 注解，配置 fallbackMethod 属性，这个属性表示该方法调用失败时的临时替代方法
     *
     * @return
     */
    // 服务降级/容错
    @HystrixCommand(fallbackMethod = "error")
    public String testHystrix() {
        return restTemplate.getForObject("http://hystrix-client-provider/hello", String.class);
    }

    /**
     * 实现服务容错/降级：这个方法就是请求失败的回调
     * <p>
     * 注意：这个方法名字要和上述 fallbackMethod 中指定的一致，方法返回值也要和对应的方法一致
     *
     * @return
     */
    // 服务降级/容错，这里简单实现
    public String error() {
        return "error";
    }

    // ===========================================

    // 异步调用
    @HystrixCommand(fallbackMethod = "error")
    public Future<String> testHystrixAsync() {
        return new AsyncResult<String>() {
            @Override
            public String invoke() {
                return restTemplate.getForObject("http://hystrix-client-provider/hello", String.class);
            }
        };
    }

    // ===========================================

    // 异常处理
    @HystrixCommand(fallbackMethod = "error2")
    // @HystrixCommand(fallbackMethod = "error2", ignoreExceptions = ArithmeticException.class)
    public String testHystrixException() {
        int i = 1 / 0; // 抛异常，会自动进行服务降级
        return restTemplate.getForObject("http://hystrix-client-provider/hello", String.class);
    }

    // 异常处理
    public String error2(Throwable t) {
        return "error: " + t.getMessage();
    }

    // ===========================================

    // 请求缓存
    @HystrixCommand(fallbackMethod = "error3")
    // 这个注解表示该方法的请求结果会被缓存起来
    // 默认缓存的 key 为所有参数的值（可通过 @CacheKey 修改，如指定某一个参数），缓存的 value 为方法的返回值
    @CacheResult
    // 下面的配置，虽然有两个参数，但是缓存时以 name 为准。
    // 也就是说，两次请求中，只要 name 一样，即使 age 不一样，第二次请求也可以使用第一次请求缓存的结果。
    // public String testHystrixCache(@CacheKey String name, Integer age) {
    public String testHystrixCache(String name) {
        return restTemplate.getForObject("http://hystrix-client-provider/hello2?name={1}", String.class, name);
    }

    // 请求缓存：删除数据库中的数据，同时删除缓存中的数据
    @HystrixCommand
    // 必须指定 commandKey 属性，commandKey 其实就是缓存方法的名字，指定了 commandKey，@CacheRemove 才能找到数据缓存在哪里了，进而才能成功删除掉数据。
    @CacheRemove(commandKey = "testHystrixCache")
    public String deleteUserByName(String name) {
        return null;
    }

    // 请求缓存
    public String error3(String name) {
        return "error: " + name;
    }

    // ===========================================

    // 请求合并，必须要用异步调用方式，并指定批处理的方法为 getUsersByIds
    // 这里还配置了一个属性 timerDelayInMilliseconds 为 200 毫秒
    @HystrixCollapser(batchMethod = "getUsersByIds", collapserProperties = {@HystrixProperty(name = "timerDelayInMilliseconds", value = "200")})
    public Future<User> testHystrixCollapser(Integer id) {
        return null;
    }

    // 请求合并
    @HystrixCommand
    public List<User> getUsersByIds(List<Integer> ids) {
        User[] users = restTemplate.getForObject("http://hystrix-client-provider/user/{1}", User[].class, StringUtils.join(ids, ","));
        return Arrays.asList(users);
    }
}
