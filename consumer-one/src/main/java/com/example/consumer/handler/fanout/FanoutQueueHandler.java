package com.example.consumer.handler.fanout;

import com.example.constant.QueueNames;
import com.rabbitmq.client.Channel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author xmm
 * @since 2020/3/18
 */
@Slf4j
@AllArgsConstructor
@Component
public class FanoutQueueHandler {
    @RabbitHandler
    @RabbitListener(queues = {QueueNames.FANOUT_QUEUE_ONE,
            QueueNames.FANOUT_QUEUE_TWO,
            QueueNames.FANOUT_QUEUE_THREE})
    public void processFanout(Message message, Channel channel) {
        log.info("Receiver fanout: {}", new String(message.getBody()));
        try {
            boolean success = true;
            if (success) {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        } catch (Exception e) {
            try {
                log.error("queue ack failed: {}, message body: {}", e.toString(),new String(message.getBody()));
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
