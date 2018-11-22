package com.example.demo.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class TopicConsumer {
    /**
     * 客户端消费 Topic
     * @param consumer
     */
    @JmsListener(destination = "zh-topic")
    @SendTo("out.topic")
    public String receiveQueue(String consumer) {
        int num = (int) (Math.random()*100);
        System.out.println(consumer+":"+num+"  topic主题 消息已经接收了");
        return consumer+" " + num +"  topic 主题发送消息了 " ;
    }
    /**
     * 客户端消费 Topic
     * @param consumer
     */
    @JmsListener(destination = "zh-topic")
    public void receiveQueue1(String consumer) {
        int num = (int) (Math.random()*100);
        System.out.println("这是第二个topic的接收器:"+consumer);
    }
}
