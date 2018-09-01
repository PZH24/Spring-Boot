package com.example.demo.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

@Component
@EnableScheduling
public class PromoteActProducer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @Scheduled(fixedDelay = 2000)    // 每2s执行1次
    public void send() {
        this.jmsMessagingTemplate.convertAndSend(this.queue, "hello,activeMQ");
    }

}
