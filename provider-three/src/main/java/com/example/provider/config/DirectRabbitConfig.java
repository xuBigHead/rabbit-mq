package com.example.provider.config;

import com.example.constant.ExchangeNameConst;
import com.example.constant.QueueNameConst;
import com.example.constant.RoutingKeyNameConst;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * direct exchange使用routing key进行消息传输
 * routing key其实就是queue和exchange的绑定
 * 适用于多工作者协同工作的场景
 *
 * @author xmm
 * @since 2020/2/24
 */
@Configuration
public class DirectRabbitConfig {
    @Bean
    public Queue direct() {
        return new Queue(QueueNameConst.DIRECT_QUEUE_THREE, true, false, false);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(ExchangeNameConst.DIRECT_EXCHANGE_THREE, true, false);
    }

    @Bean
    public Binding directBindingExchange(Queue direct, DirectExchange directExchange) {
        return BindingBuilder.bind(direct).to(directExchange).with(RoutingKeyNameConst.DIRECT_ROUTING_KEY_THREE);
    }
}
