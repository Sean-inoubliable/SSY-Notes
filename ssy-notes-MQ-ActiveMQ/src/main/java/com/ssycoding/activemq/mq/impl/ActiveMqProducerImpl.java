package com.ssycoding.activemq.mq.impl;

import com.ssycoding.activemq.mq.ActiveMqProducer;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * -
 *
 * @author Songxinlei 2021年12月28日
 * -
 */
@Service
public class ActiveMqProducerImpl implements ActiveMqProducer {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Override
    public void sendQueue(String destinationName, Object message) {
        jmsMessagingTemplate.convertAndSend(new ActiveMQQueue(destinationName), message);
    }

    @Override
    public void sendTopic(String destinationName, Object message) {
        jmsMessagingTemplate.convertAndSend(new ActiveMQTopic(destinationName), message);
    }
}
