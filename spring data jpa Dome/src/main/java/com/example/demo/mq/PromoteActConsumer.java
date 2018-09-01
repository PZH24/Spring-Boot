package com.example.demo.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class PromoteActConsumer {
    /**
     * 客户端消费
     * @param consumer
     */
    @JmsListener(destination = "promoteAct")
    public void receiveQueue(String consumer) {
        System.out.println(consumer+"消息已经接收了");
    }

}
