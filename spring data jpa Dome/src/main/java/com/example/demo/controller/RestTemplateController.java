package com.example.demo.controller;

import com.example.demo.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
public class RestTemplateController {
        @Autowired
        private RestTemplate restTemplate;
        //restful的地址
        @Value("${userServicePath}")
        private  String userServicePath;
        //get
        @GetMapping("template/{id}")
        public UserInfo findOne(@PathVariable("id") long id){
            return restTemplate.getForObject(this.userServicePath+id,UserInfo.class);
        }
        @PutMapping("template/{id}")
        public void updateUserInfo(UserInfo userInfo){
                restTemplate.put(URI.create(this.userServicePath+"saveUserInfo"),userInfo);
        }
        @DeleteMapping("template/{id}")
        public  void  deleteUserInfo(@PathVariable String id){
                restTemplate.delete(URI.create(this.userServicePath+id));
        }
        @PostMapping("template/{id}")
        public  void addUserInfo(UserInfo userInfo){
                restTemplate.postForObject(URI.create(this.userServicePath),userInfo,UserInfo.class);
//                restTemplate.postForEntity(URI.create(this.userServicePath),userInfo,UserInfo.class);
//                restTemplate.postForLocation(this.userServicePath,userInfo);
        }
}
