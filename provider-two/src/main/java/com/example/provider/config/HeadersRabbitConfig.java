package com.example.provider.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * header exchange忽略routing key参数，用header来取代
 *
 * @author xmm
 * @since 2020/2/24
 */

@Configuration
public class HeadersRabbitConfig {

    @Bean
    public Queue headerQueue() {
        return new Queue("headerQueue");
    }

    @Bean
    public Queue headerQueue2() {
        return new Queue("headerQueue2");
    }

    @Bean
    public HeadersExchange headerExchange() {
        return new HeadersExchange("headerExchange");
    }

    @Bean
    public HeadersExchange headerExchange2() {
        return new HeadersExchange("headerExchange2");
    }

    @Bean
    public Binding bindingExchange(Queue headerQueue, HeadersExchange headerExchange) {
        Map<String, Object> headerValues = new HashMap<>(3);
        headerValues.put("param1", "value1");
        headerValues.put("param2", "value2");
        return BindingBuilder.bind(headerQueue).to(headerExchange).whereAll(headerValues).match();
    }

    @Bean
    public Binding bindingExchange2(Queue headerQueue2, HeadersExchange headerExchange2) {
        Map<String, Object> header = new HashMap<>(3);
        header.put("param1", "value1");
        header.put("param2", "value2");
        return BindingBuilder.bind(headerQueue2).to(headerExchange2).whereAny(header).match();
    }
}
