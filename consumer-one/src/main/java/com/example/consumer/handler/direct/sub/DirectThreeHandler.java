package com.example.consumer.handler.direct.sub;

import com.example.constant.QueueNameConst;
import com.example.consumer.handler.direct.DirectHandler;
import com.example.service.IRabbitBeanService;
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
public class DirectThreeHandler extends DirectHandler {
    public DirectThreeHandler(IRabbitBeanService rabbitBeanService) {
        super(rabbitBeanService);
    }

    @RabbitListener(queues = {QueueNameConst.DIRECT_QUEUE_THREE})
    public void processDirectThree(Message message, Channel channel) {
        this.processDirect(message, channel);
    }
}
