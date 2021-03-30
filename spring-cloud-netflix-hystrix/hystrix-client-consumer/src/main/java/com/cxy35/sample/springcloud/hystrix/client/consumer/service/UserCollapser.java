package com.cxy35.sample.springcloud.hystrix.client.consumer.service;

import com.cxy35.sample.springcloud.common.User;
import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// 请求命令方式，实现请求合并
public class UserCollapser extends HystrixCollapser<List<User>, User, Integer> {
    private UserService userService;
    private Integer id;

    public UserCollapser(UserService userService, Integer id) {
        super(Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("userCollapserKey")).andCollapserPropertiesDefaults(HystrixCollapserProperties.Setter().withTimerDelayInMilliseconds(200)));
        this.userService = userService;
        this.id = id;
    }

    /**
     * 请求参数
     *
     * @return
     */
    @Override
    public Integer getRequestArgument() {
        return id;
    }

    /**
     * 请求合并的方法
     *
     * @param collection
     * @return
     */
    @Override
    protected HystrixCommand<List<User>> createCommand(Collection<CollapsedRequest<User, Integer>> collection) {
        List<Integer> ids = new ArrayList<>(collection.size());
        for (CollapsedRequest<User, Integer> userIntegerCollapsedRequest : collection) {
            ids.add(userIntegerCollapsedRequest.getArgument());
        }
        return new UserCommand(ids, userService);
    }

    /**
     * 请求结果分发
     *
     * @param users
     * @param collection
     */
    @Override
    protected void mapResponseToRequests(List<User> users, Collection<CollapsedRequest<User, Integer>> collection) {
        int count = 0;
        for (CollapsedRequest<User, Integer> request : collection) {
            request.setResponse(users.get(count++));
        }
    }
}
