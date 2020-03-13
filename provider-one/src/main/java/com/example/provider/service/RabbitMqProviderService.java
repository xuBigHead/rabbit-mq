package com.example.provider.service;


/**
 * @author xmm
 * @since 2020/2/24
 */
public interface RabbitMqProviderService {
    /**
     * 发送字符串类型消息 Direct exchange
     *
     * @param message message
     */
    void sendString(String message);

    /**
     * 发送对象类型消息 Direct exchange
     *  Headers exchange
     *
     * @param bean bean
     */
    void sendObject(RabbitBean bean);

    /**
     * 发送字符串类型消息 Fanout exchange
     *
     * @param message message
     */
    void send(String message);

    /**
     * 发送字符串类型消息 Topic exchange
     *
     * @param message message
     */
    void sendTopic1(String message);

    /**
     * 发送字符串类型消息 Topic exchange
     *
     * @param message message
     */
    void sendTopic2(String message);

    /**
     * @param head map
     * @param msg msg
     */
    void sendHeader(Map<String, Object> head, String msg);

    /**
     * @param head map
     * @param msg msg
     */
    void sendHeader2(Map<String, Object> head, String msg);
}
