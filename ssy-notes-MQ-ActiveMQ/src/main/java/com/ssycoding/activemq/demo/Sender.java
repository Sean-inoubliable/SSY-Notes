package com.ssycoding.activemq.demo;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * -
 * demo
 * 消息的创建 & 发送
 * producer 创建者
 *
 * @author Songxinlei 2021年12月26日
 * -
 */
public class Sender {

    public static void main(String[] args) throws JMSException, InterruptedException {

        // 1. 获取ActiveMQ链接工厂
        ActiveMQConnectionFactory mqConnectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                "tcp://localhost:61616"
        );

        // 2. 创建一个向ActiveMQ通讯的链接
        Connection connection = mqConnectionFactory.createConnection();

        // 3. 创建session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 4-1. 创建目标地，destination。 消费端，也会从这个目标地取消息
        Queue queue = session.createQueue("QUEUE-TITLE");

        // 4-2. 创建目标地，destination。 消费端，也会从这个目标地取消息
        // Topic topic = session.createTopic("TOPIC-TITLE");

        // 5. 设置目标地 destination 。
        MessageProducer producer = session.createProducer(queue);

        // 6. 创建 并 发送消息。消息种类：TestMessage \ MapMessage \ BytesMessage \ StreamMessage \ ObjectMessage
        for (int i = 1; i <= 5; i++) {
            MapMessage mapMessage = session.createMapMessage();
            TextMessage textMessage = session.createTextMessage("This is No." + i + " message");
            producer.send(textMessage);
            Thread.sleep(3000);
        }

        // 8. 关闭链接
        connection.close();
    }
}
