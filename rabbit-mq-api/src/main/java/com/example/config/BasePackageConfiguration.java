package com.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author xmm
 * @since 2020/3/16
 */
@Configuration
@ComponentScan(basePackages = {"com.example.*"})
public class BasePackageConfiguration {
}
