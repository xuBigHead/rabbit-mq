package com.example.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xmm
 * @since 2020/2/24
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.example.config"})
public class ConsumerTwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerTwoApplication.class, args);
    }

}
