package com.cxy35.sample.seata.order.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan({"com.cxy35.sample.seata.order.dao"})
public class MyBatisConfig {
}
