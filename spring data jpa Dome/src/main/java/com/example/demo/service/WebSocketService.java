package com.example.demo.service;

public class WebSocketService {
    private  String responseMessage;
    public WebSocketService(String responseMessage){
        this.responseMessage =responseMessage;
    }
    public String getResponseMessage(){
        return  responseMessage;
    }
}
