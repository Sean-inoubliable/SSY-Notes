package com.ssycoding.activemq.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.xml.soap.Text;

/**
 * -
 * MQ消息监听
 *      Topic And Queue
 *
 *      destination: 监听指定地址的消息
 *      containerFactory: 监听工厂(Topic Or Queue)
 *
 * @author Songxinlei 2021年12月28日
 * -
 */

@Component
public class ActiveMqConsumerJmsListener {

    @JmsListener(destination = "Topic", containerFactory = "jmsListenerContainerTopic")
    public void topicReceiver(Object message) throws JMSException {
        if (message instanceof TextMessage) {
            TextMessage msg = (TextMessage) message;
            System.out.println("topicReceiver: " + msg.getText());
        }
    }

    @JmsListener(destination = "Queue", containerFactory = "jmsListenerContainerQueue")
    public void queueReceiver(Object message) throws JMSException {
        if (message instanceof TextMessage) {
            TextMessage msg = (TextMessage) message;
            System.out.println("topicReceiver: " + msg.getText());
        }
    }
}
