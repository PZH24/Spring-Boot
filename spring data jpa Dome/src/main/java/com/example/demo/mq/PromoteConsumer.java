package com.example.demo.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class PromoteConsumer {
    /**
     * 客户端消费
     * @param text
     * JmsListener：监听器；destination:目的地
     * SendTo 发送消息
     */
    @JmsListener(destination = "promoteAct")
    @SendTo("out.queue")
    public String receiveQueue(String text) {
        int num = (int) (Math.random()*100);
        System.out.println("Consumer2收到的报文为:"+text);
        return "return message:"+text+num;
    }
}
