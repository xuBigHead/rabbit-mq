package com.example.provider.config.fanout;

import com.example.constant.ExchangeNames;
import com.example.constant.QueueNames;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Bean(QueueNames.FANOUT_QUEUE_TWO)
    public Queue queueOne(){
        return new Queue(QueueNames.FANOUT_QUEUE_TWO,true,false,false,null);
    }

    @Bean(ExchangeNames.FANOUT_EXCHANGE_TWO)
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(ExchangeNames.FANOUT_EXCHANGE_TWO,true,false,null);
    }

    @Bean
    public Binding bindingExchangeA(@Qualifier(QueueNames.FANOUT_QUEUE_TWO) Queue queue,
                                    @Qualifier(ExchangeNames.FANOUT_EXCHANGE_TWO) FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }
}
