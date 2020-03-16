package com.example.provider.config.exchange;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * fanout exchange就是广播模式，把消息路有给所有的绑定队列
 * 适用于群聊天的场景
 *
 * @author xmm
 * @since 2020/2/24
 */
@Configuration
public class FanoutRabbitConfig {
    @Bean
    public Queue queueA(){
        return new Queue("fanout.a");
    }

    @Bean
    public Queue queueB(){
        return new Queue("fanout.b");
    }

    @Bean
    public Queue queueC(){
        return new Queue("fanout.c");
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    public Binding bindingExchangeA(Queue queueA, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueA).to(fanoutExchange);
    }

    @Bean
    public Binding bindingExchangeB(Queue queueB, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueB).to(fanoutExchange);
    }

    @Bean
    public Binding bindingExchangeC(Queue queueC, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueC).to(fanoutExchange);
    }
}
