package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserHtml5Controll {
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    @RequestMapping("/editUser")
    public String editUser() {
        return "editUser";
    }
    @RequestMapping("/AllUserlst")
    public String AllUserlst() {
        return "AllUserlst";
    }
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
    @RequestMapping("/regiter")
    public String regiter() {
        return "regiter";
    }
}
