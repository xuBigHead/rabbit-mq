package com.example.provider.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.constant.ExchangeNames;
import com.example.constant.RoutingKeyNames;
import com.example.entity.RabbitBean;
import com.example.provider.service.RabbitMqProviderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author xmm
 * @since 2020/2/24
 */
@Service
@Slf4j
@AllArgsConstructor
public class RabbitMqProviderServiceImpl implements RabbitMqProviderService {
    AmqpTemplate rabbitTemplate;

    @Override
    public void sendString(String message) {
        log.info("send string message: {}", message);
        rabbitTemplate.convertAndSend(ExchangeNames.DIRECT_EXCHANGE, RoutingKeyNames.DIRECT_ROUTING_KEY, message);
    }

    @Override
    public void sendObject(RabbitBean bean) {
        String messageStr = JSONObject.toJSONString(bean);
        log.info("send object message {}",messageStr);
        rabbitTemplate.convertAndSend(ExchangeNames.DIRECT_EXCHANGE_THREE, RoutingKeyNames.DIRECT_ROUTING_KEY_THREE, messageStr);
    }

    @Override
    public void send(String message) {
        log.info("fanout sender : {}", message);
        rabbitTemplate.convertAndSend("fanoutExchange","", message);
    }

    @Override
    public void sendTopic1(String message) {
        log.info("topic sender1 : " + message);
        rabbitTemplate.convertAndSend("topicExchange", "topic.message", message);
    }

    @Override
    public void sendTopic2(String message) {
        log.info("topic sender2 : " + message);
        rabbitTemplate.convertAndSend("topicExchange", "topic.message2", message);
    }

    @Override
    public void sendHeader(Map<String, Object> head, String msg){
        log.info("header send message: "+msg);
        rabbitTemplate.convertAndSend("headerExchange", "headerQueue", getMessage(head, msg));
    }

    @Override
    public void sendHeader2(Map<String, Object> head, String msg){
        log.info("header1 send message: "+msg);
        rabbitTemplate.convertAndSend("headerExchange2", "headerQueue2", getMessage(head, msg));
    }

    private Message getMessage(Map<String, Object> head, Object msg){
        MessageProperties messageProperties = new MessageProperties();
        for (Map.Entry<String, Object> entry : head.entrySet()) {
            messageProperties.setHeader(entry.getKey(), entry.getValue());
        }
        MessageConverter messageConverter = new SimpleMessageConverter();
        return messageConverter.toMessage(msg, messageProperties);
    }
}
