package com.ssycoding.activemq.controller;

import com.ssycoding.activemq.mq.ActiveMqProducer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * -
 *
 * @author Songxinlei 2021年12月28日
 * -
 */
@RestController
public class MainController {

    @Resource
    private ActiveMqProducer producer;

    @RequestMapping
    public void send() {
        for (int i = 1; i <= 3; i++) {
            producer.sendQueue("Queue", "This Queue No." + i + " test message !");
            producer.sendTopic("Topic", "This Topic No." + i + " test message !");
        }
    }
}
