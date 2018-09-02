package com.example.demo.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;

@Component
public class TopicProducer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;
    /***
     * 服务端发送者 Topic
     * */
    @Scheduled(fixedDelay = 2000)// 每2s执行1次
    public void send() {
        int num = (int) (Math.random()*100);
        this.jmsMessagingTemplate.convertAndSend(this.topic, "hello word,activeMQ"+num);
    }
    @JmsListener(destination="out.topic")
    public void consumerMessage(String text){
        int num = (int) (Math.random()*100);
        System.out.println("从out.topic 主题收到的回复为:"+text+" : " +num);
    }
}
