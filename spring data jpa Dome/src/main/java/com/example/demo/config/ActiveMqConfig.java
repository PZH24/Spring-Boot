package com.example.demo.config;


import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Topic;

@Configuration
@EnableJms
public class ActiveMqConfig {
    /**
     * 点对点
     * @return
     * */
    @Bean
    public ActiveMQQueue queue() {
        return new ActiveMQQueue("promoteAct");
    }

    /**
     * 发布/订阅
     * @return
     */
    @Bean
    public Topic topic(){
        return new ActiveMQTopic("zh-topic");
    }

    //需要给topic定义独立的JmsListenerContainer
//    @Bean
//    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ConnectionFactory activeMQConnectionFactory) {
//        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
//        bean.setPubSubDomain(true);
//        bean.setConnectionFactory(activeMQConnectionFactory);
//        return bean;
//    }


//    @JmsListener(destination = "sample.topic", containerFactory="jmsListenerContainerTopic")
//    public void receiveTopic(String text) {
//        System.out.println(text);
//    }
}
