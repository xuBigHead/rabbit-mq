package com.example.provider.config;

import com.example.constant.ExchangeNameConst;
import com.example.constant.QueueNameConst;
import com.example.constant.RoutingKeyNameConst;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.namespace.QName;

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

    @Bean(QueueNameConst.DIRECT_QUEUE_ONE)
    public Queue direct() {
        return new Queue(QueueNameConst.DIRECT_QUEUE_ONE, true, false, false);
    }

    @Bean(ExchangeNameConst.DIRECT_EXCHANGE_ONE)
    public DirectExchange directExchange() {
        return new DirectExchange(ExchangeNameConst.DIRECT_EXCHANGE_ONE, true, false);
    }

    @Bean
    public Binding directBindingExchange(@Qualifier(QueueNameConst.DIRECT_QUEUE_ONE) Queue direct,
                                         @Qualifier(ExchangeNameConst.DIRECT_EXCHANGE_ONE) DirectExchange directExchange) {
        return BindingBuilder.bind(direct).to(directExchange).with(RoutingKeyNameConst.DIRECT_ROUTING_KEY_ONE);
    }
}
