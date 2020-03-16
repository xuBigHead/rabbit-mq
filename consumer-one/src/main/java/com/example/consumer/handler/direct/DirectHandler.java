package com.example.consumer.handler.direct;

import com.alibaba.fastjson.JSON;
import com.example.entity.RabbitBean;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * @author xmm
 * @since 2020/3/16
 */
@Slf4j
public class DirectHandler {
    @Transactional(rollbackFor = Exception.class)
    protected void processDirect(Message message, Channel channel){
        String messageStr = new String(message.getBody());
        RabbitBean rabbitBean;
        try {
            rabbitBean = JSON.parseObject(messageStr, RabbitBean.class);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        log.info("Receiver direct: {}", rabbitBean);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (IOException e) {
            e.printStackTrace();
            try {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            log.error("ack failed");
        }
    }
}
