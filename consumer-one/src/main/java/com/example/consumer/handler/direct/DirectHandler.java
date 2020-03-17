package com.example.consumer.handler.direct;

import com.alibaba.fastjson.JSON;
import com.example.entity.RabbitBean;
import com.example.service.IRabbitBeanService;
import com.rabbitmq.client.Channel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xmm
 * @since 2020/3/16
 */
@Slf4j
@AllArgsConstructor
@Component
public class DirectHandler {
    IRabbitBeanService rabbitBeanService;
    @Transactional(rollbackFor = Exception.class)
    public void processDirect(Message message, Channel channel){
        String messageStr = new String(message.getBody());
        // 通过读取数据库中数据来保证消息不被重复消费
        RabbitBean rabbitBean;
        try {
            rabbitBean = JSON.parseObject(messageStr, RabbitBean.class);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        log.info("Receiver direct: {}", rabbitBean);
        try {
            boolean success = rabbitBeanService.save(rabbitBean);
            if(success) {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            } else {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        } catch (Exception e) {
            try {
                // 设置requeue为false，表示将消息发到死信队列中
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            log.error("ack failed: {}",e.toString());
        }
    }
}
