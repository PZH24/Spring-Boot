package com.example.demo.controller;

import com.example.demo.entity.UserInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RestTemplateController {
        @Autowired
        private RestTemplate restTemplate;
        private int PAGE_INIT = 1;
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
        @CacheEvict(value = "userinfo",key = "#id")
        public  void  deleteUserInfo(@PathVariable Long id){
                restTemplate.delete(URI.create(this.userServicePath+id));
        }
        @PostMapping("template/{id}")
        public  void addUserInfo(UserInfo userInfo){
                restTemplate.postForObject(URI.create(this.userServicePath),userInfo,UserInfo.class);
//                restTemplate.postForEntity(URI.create(this.userServicePath),userInfo,UserInfo.class);
//                restTemplate.postForLocation(this.userServicePath,userInfo);
        }
        //get
        @GetMapping("findWWWByUrl/{url}")
        public Object findWWWByUrl(@PathVariable("url") String type){
//                return restTemplate.getForObject(URI.create("https://www.jianshu.com/"),Object.class);

                return null;
        }
}
