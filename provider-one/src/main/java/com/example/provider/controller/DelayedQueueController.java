package com.example.provider.controller;

import com.example.constant.ExchangeNames;
import com.example.constant.RoutingKeyNames;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

/**
 * @author xmm
 * @since 2020/3/17
 */
@Slf4j
@RestController
@RequestMapping("/provider-one")
@AllArgsConstructor
public class DelayedQueueController {
    RabbitTemplate rabbitTemplate;

    @GetMapping("delayed")
    public void delayed(int number) {
        log.warn("延迟队列发送 : {} milliseconds", number);
        // 这里的Exchange可以是业务的Exchange，为了方便测试这里直接往死信Exchange里投递消息
        rabbitTemplate.convertAndSend(ExchangeNames.DELAYED_EXCHANGE,
                RoutingKeyNames.DELAYED_ROUTING_KEY,
                number,
                (message) -> {
                    message.getMessageProperties().setDelay(number);
                    log.info("Now : {}", ZonedDateTime.now());
                    return message;
                });
    }
}
