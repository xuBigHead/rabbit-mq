package com.example.consumer.handler.delayed;

import com.example.constant.QueueNames;
import com.example.service.IRabbitBeanService;
import com.rabbitmq.client.Channel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Random;

/**
 * @author xmm
 * @since 2020/3/17
 */
@Slf4j
@Component
@AllArgsConstructor
@RabbitListener(queues = QueueNames.DELAYED_QUEUE)
public class DelayedQueueHandler {
    IRabbitBeanService rabbitBeanService;

    @RabbitHandler
    public void receiveDelayMessage(Message message, Channel channel){
        String milliseconds = new String(message.getBody());
        Random random = new Random();
        int i = random.nextInt(10);
        try {
            log.info("DelayQueueConsumer Time : {}， and the millis : {}", ZonedDateTime.now(), milliseconds);
            if((i&1)==1) {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            } else {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
