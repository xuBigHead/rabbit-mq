package com.example.consumer.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author xmm
 * @since 2020/2/24
 */

@Slf4j
@Component
public class RabbitHandler {
    @RabbitListener(queues = {"fanout.a", "fanout.b", "fanout.c"})
    public void processFanout(Message message) {
        log.info("Receiver fanout: {}", new String(message.getBody()));
    }

    @RabbitListener(queues = {"topic.message"})
    public void processTopic(Message message) {
        log.info("Receiver topic: {}", new String(message.getBody()));
    }

    @RabbitListener(queues = {"topic.message2"})
    public void processTopic2(Message message) {
        log.info("Receiver topic2: {}", new String(message.getBody()));
    }

    @RabbitListener(queues = {"headerQueue"})
    public void processHeaders(Message message) {
        log.info("Receiver header: {}", new String(message.getBody()));
    }

    @RabbitListener(queues = {"headerQueue2"})
    public void processHeaders1(Message message) {
        log.info("Receiver header2: {}", new String(message.getBody()));
    }
}
