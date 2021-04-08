package com.cxy35.sample.seata.account.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan({"com.cxy35.sample.seata.account.dao"})
public class MyBatisConfig {
}
