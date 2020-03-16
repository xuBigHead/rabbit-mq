package com.example.consumer.handler.direct.sub;

import com.example.constant.QueueNameConst;
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
public class DirectTwoHandler extends DirectHandler {
    @RabbitListener(queues = {QueueNameConst.DIRECT_QUEUE_TWO})
    public void processDirectTwo(Message message, Channel channel) {
        this.processDirect(message, channel);
    }
}