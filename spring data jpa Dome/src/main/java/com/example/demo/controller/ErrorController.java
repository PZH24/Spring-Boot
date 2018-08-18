package com.example.demo.controller;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {
    @ExceptionHandler(RuntimeException.class)
    public String  UserServiceNotFind(){
        return "error";
    }
}
