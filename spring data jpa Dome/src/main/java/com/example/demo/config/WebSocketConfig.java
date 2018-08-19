package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
//开启Stomp协议来传输基于(message broker)的消息；这时控制器支持@MessageMapping 注解。
@EnableWebSocketMessageBroker
@EnableWebSocket
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

   //注册stomp协议的节点；并指定url
    @Override
    public  void registerStompEndpoints(StompEndpointRegistry registry) {
        //指定使用SockJs协议
        registry.addEndpoint("/endpointWisely").withSockJS();
    }
    //设置消息代理
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //设置主题
        registry.enableSimpleBroker("/topic");
    }
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
