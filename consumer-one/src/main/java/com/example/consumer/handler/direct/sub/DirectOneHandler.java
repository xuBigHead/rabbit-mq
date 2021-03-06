package com.example.consumer.handler.direct.sub;

import com.example.constant.QueueNames;
import com.example.consumer.handler.direct.DirectHandler;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author xmm
 * @since 2020/3/16
 */
@Slf4j
@Component
public class DirectOneHandler {
    private DirectHandler directHandler;

    public DirectOneHandler(DirectHandler directHandler) {
        this.directHandler = directHandler;
    }

    @RabbitListener(queues = {QueueNames.DIRECT_QUEUE_ONE})
    public void processDirectOne(Message message, Channel channel) {
        directHandler.processDirect(message, channel);
    }
}
