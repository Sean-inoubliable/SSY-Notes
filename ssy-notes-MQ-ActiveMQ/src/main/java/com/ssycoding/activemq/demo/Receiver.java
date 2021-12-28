package com.ssycoding.activemq.demo;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Arrays;

/**
 * -
 * demo
 * 消息的获取
 * consumer 消费者
 *
 * @author Songxinlei 2021年12月26日
 * -
 */
public class Receiver {

    public static void main(String[] args) throws JMSException {

        // 1. 获取ActiveMQ链接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                "tcp://localhost:61616"
        );

        // 添加信任的持久化类型 PackageList
        connectionFactory.setTrustedPackages(Arrays.asList());

        // 2. 创建一个向ActiveMQ通讯的链接
        Connection connection = connectionFactory.createConnection();

        connection.start();

        // 3. 创建session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 4-1. 创建目标地，destination。 消费端，也会从这个目标地取消息
        Queue queue = session.createQueue("QUEUE-TITLE");

        // 4-2. 创建目标地，destination。 消费端，也会从这个目标地取消息
        // Topic topic = session.createTopic("TOPIC-TITLE");

        // 5. 设置目标地 destination 。
        MessageConsumer consumer = session.createConsumer(queue);

        // 6. 设置消息的监听
        consumer.setMessageListener(message -> {

            if (message instanceof TextMessage) {

                TextMessage msg = (TextMessage) message;

                try {
                    System.out.println(msg.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
