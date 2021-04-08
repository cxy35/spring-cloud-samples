package com.cxy35.sample.seata.order.service;


import com.cxy35.sample.seata.order.domain.Order;

public interface OrderService {

    /**
     * 创建订单
     */
    void create(Order order);
}
