package com.example.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author xmm
 * @since 2020/3/16
 */
@Configuration
@MapperScan(basePackages = {"com.example.mapper","com.example.handler"})
public class MybatisPlusConfiguration {

}
