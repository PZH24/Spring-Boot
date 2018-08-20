package com.example.demo.entity;

import javax.persistence.Entity;

//@Entity
public class WebSocketInfo {
    private  String name;
    private  String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
