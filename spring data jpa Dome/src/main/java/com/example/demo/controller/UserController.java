package com.example.demo.controller;

import com.example.demo.entity.UserInfo;
import com.example.demo.pojo.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/verifyUser")
    public String verifyUser( String username,  String password) {
     return  userService.isLogin(username,password);
    }
    @RequestMapping("/addUser")
    public boolean addUser( String username, String password,@RequestParam(defaultValue = "0") int rigth) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(username);
        userInfo.setPassword(password);
        userInfo.setRigth(rigth);
        //"redirect：":表示重定向、
        //当它发现视图格式中以“forward:”作为前缀时，请求将会前往（forward）指定的URL路径
       return userService.addOrUpdateUser(userInfo)!=null?true:false;
    }
    @RequestMapping("/getAllUserInfo")
    public Object getAllUserInfo(){
       return userService.getAllUserInfo();
    }

}
