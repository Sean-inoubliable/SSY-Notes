package com.ssycoding.activemq.mq;

/**
 * -
 * MQ消息发送服务
 *
 * @author Songxinlei 2021年12月28日
 * -
 */
public interface ActiveMqProducer {

    /**
     * 发送 Queue 消息
     */
    void sendQueue(String destinationName, Object message);

    /**
     * 发送 Topic 消息
     */
    void sendTopic(String destinationName, Object message);
}
