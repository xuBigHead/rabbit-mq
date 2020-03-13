package com.example.provider.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * topic exchange通过routing key和通配符来路由消息
 * 适用于发布订阅场景
 *
 * @author xmm
 * @since 2020/2/24
 */
@Configuration
public class TopicRabbitConfig {

    @Bean
    public Queue queueMessage() {
        return new Queue("topic.message");
    }

    @Bean
    public Queue queueMessage2() {
        return new Queue("topic.message2");
    }

    /**
     * 将队列绑定到Topic交换器
     */
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }

    /**
     * 将队列绑定到Topic交换器
     */
    @Bean
    public Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    /**
     * 将队列绑定到Topic交换器 采用#的方式
     */
    @Bean
    Binding bindingExchangeMessage2(TopicExchange exchange, Queue queueMessage2) {
        return BindingBuilder.bind(queueMessage2).to(exchange).with("topic.#");
    }
}