package com.example.demo.controller;
import com.example.demo.entity.UserInfo;
import com.example.demo.pojo.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "restTest")
public class RestTestController {
    @Autowired
    private IUserService userService;
    @RequestMapping(value = "/getUserInfo",method = RequestMethod.GET)
    public List<UserInfo> getUserInfo(){
        return userService.getAllUserInfo();
    }
    /**
     * 获取
     * */
    @RequestMapping (value = "/{id}",method = RequestMethod.GET)
    public UserInfo getUserInfoById(@PathVariable long id){
        return userService.getUserInfoById(id);
    }

    /** 更新*/
    @RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
    public void updateUserInfo(@PathVariable long id, @RequestParam String userName,@RequestParam String passWord, @RequestParam (defaultValue ="0")int rigth){
        UserInfo userInfo =new UserInfo(id,userName,passWord,rigth);
        userService.addOrUpdateUser(userInfo);
    }
    /**
     * 新增
     * */
    @RequestMapping(method = RequestMethod.POST)
    public void addUserInfo(@RequestBody UserInfo userInfo){
        userService.addOrUpdateUser(userInfo);
    }
    /**删除
     * */
    @RequestMapping(value = "/{rid}",method = RequestMethod.DELETE)
    public void deleteUserInfo(@PathVariable("rid") long id ){
         userService.delete(id);
//        System.out.println(id+"执行删除操作");
    }
    @RequestMapping(value = "/{n}/{p}",method = RequestMethod.GET)
    public String checkUserNameAndPassword(@PathVariable("n")String username,@PathVariable ("p")String password) {
        return  userService.isLogin(username,password);
    }
        /** 更新*/
    @RequestMapping(value = "/saveUserInfo",method = RequestMethod.PUT)
    public void saveUserInfo(@RequestBody UserInfo userInfo){
        userService.addOrUpdateUser(userInfo);
    }
}
