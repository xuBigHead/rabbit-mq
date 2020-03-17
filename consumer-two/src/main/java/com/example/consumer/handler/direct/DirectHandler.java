package com.example.consumer.handler.direct;

import com.alibaba.fastjson.JSON;
import com.example.constant.QueueNames;
import com.example.entity.RabbitBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author xmm
 * @since 2020/3/16
 */
@Slf4j
@Component
@RabbitListener(queues = {QueueNames.DIRECT_QUEUE_ONE,QueueNames.DIRECT_QUEUE_TWO,QueueNames.DIRECT_QUEUE_THREE})
public class DirectHandler {
    @RabbitHandler
    private void processDirect(String message){
        RabbitBean rabbitBean = null;
        try {
            rabbitBean = JSON.parseObject(message, RabbitBean.class);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        System.err.println(message);
        System.err.println(rabbitBean);
        log.info("Receiver direct: {}", message);
    }
}
