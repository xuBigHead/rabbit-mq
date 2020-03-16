package com.example.consumer.handler.direct;

import com.alibaba.fastjson.JSON;
import com.example.entity.RabbitBean;
import com.example.service.IRabbitBeanService;
import com.rabbitmq.client.Channel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * @author xmm
 * @since 2020/3/16
 */
@Slf4j
@AllArgsConstructor
public class DirectHandler {
    IRabbitBeanService rabbitBeanService;
    @Transactional(rollbackFor = Exception.class)
    protected void processDirect(Message message, Channel channel){
        String messageStr = new String(message.getBody());

        // TODO 可以通过redis避免消息重复消费

        RabbitBean rabbitBean;
        try {
            rabbitBean = JSON.parseObject(messageStr, RabbitBean.class);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        log.info("Receiver direct: {}", rabbitBean);
        boolean success = rabbitBeanService.save(rabbitBean);
        try {
            if(success) {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            } else {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("ack failed: {}",e.toString());
        }
    }
}
