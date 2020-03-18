package com.example.consumer.handler.fanout;

import com.example.constant.QueueNames;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author xmm
 * @since 2020/3/18
 */
@Slf4j
@AllArgsConstructor
@Component
public class FanoutQueueHandler {
    @RabbitListener(queues = {QueueNames.FANOUT_QUEUE_ONE,
            QueueNames.FANOUT_QUEUE_TWO,
            QueueNames.FANOUT_QUEUE_THREE})
    public void processFanout(Message message) {
        log.info("Receiver fanout: {}", new String(message.getBody()));
    }
}
