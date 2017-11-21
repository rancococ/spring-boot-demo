package com.catvgd.springbootdemo.config.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置Mybatis的Mapper扫描包路径
 */
@Configuration
@MapperScan(basePackages = {"com.catvgd.springbootdemo.common.*.mapper", // 包含tkmapper和公共的mapper
        "com.catvgd.springbootdemo.biz.*.mapper"}) // 业务模块的mapper
public class MybatisConfig {
}
