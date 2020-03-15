package com.example.provider.controller;

import com.example.entity.RabbitBean;
import com.example.provider.service.RabbitMqProviderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xmm
 * @since 2020/2/24
 */
@RestController
@RequestMapping("/provider-two")
@AllArgsConstructor
public class RabbitMqProviderController {
    RabbitMqProviderService rabbitMqProviderService;

    @GetMapping("/direct/string")
    public void sendString(String message) {
        rabbitMqProviderService.sendString(message);
    }

    @PostMapping("/direct/object")
    public void sendObject(@RequestBody RabbitBean bean) {
        rabbitMqProviderService.sendObject(bean);
    }

    @GetMapping("/fanout/string")
    public void send(String message) {
        rabbitMqProviderService.send(message);
    }

    @GetMapping("/topic/string")
    public void sendTopic(String message) {
        rabbitMqProviderService.sendTopic1(message);
    }

    @GetMapping("/topic2/string")
    public void sendTopic2(String message) {
        rabbitMqProviderService.sendTopic2(message);
    }

    @GetMapping("/header/string")
    public void sendHeader(String message) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("bean",new RabbitBean());
        rabbitMqProviderService.sendHeader(map,message);
    }

    @GetMapping("/header2/string")
    public void sendHeader2(String message) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("bean",new RabbitBean());
        rabbitMqProviderService.sendHeader2(map,message);
    }
}
