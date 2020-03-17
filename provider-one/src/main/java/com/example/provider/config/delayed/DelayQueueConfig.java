package com.example.provider.config.delayed;

import com.example.constant.ExchangeNames;
import com.example.constant.QueueNames;
import com.example.constant.RoutingKeyNames;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xmm
 * @since 2020/3/17
 */
@Configuration
public class DelayQueueConfig {
    @Bean("delayExchange")
    public Exchange delayExchange() {
        Map<String, Object> args = new HashMap<>(1);
        // x-delayed-type声明延迟队列Exchange的类型
        args.put("x-delayed-type", "direct");
        return new CustomExchange(ExchangeNames.DELAYED_EXCHANGE, "x-delayed-message",true, false,args);
    }

    @Bean("delayQueue")
    public Queue delayQueue() {
        return QueueBuilder.durable(QueueNames.DELAYED_QUEUE).build();
    }

    @Bean
    public Binding delayQueueBindExchange() {
        return new Binding(QueueNames.DELAYED_QUEUE,
                Binding.DestinationType.QUEUE,
                ExchangeNames.DELAYED_EXCHANGE,
                RoutingKeyNames.DELAYED_ROUTING_KEY, null);
    }
}
