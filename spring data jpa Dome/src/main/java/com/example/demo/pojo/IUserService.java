package com.example.demo.pojo;


import com.example.demo.entity.UserInfo;

import java.util.List;

public interface IUserService {
    //新增或者修改用户信息
    UserInfo addOrUpdateUser(UserInfo userInfo);
    //删除用户信息
    void  delete(Long rid);
    //获取用户信息
    List<UserInfo> getAllUserInfo();
    //判断是否用户存在，
    String isLogin(String name, String password);
    //获取某个用户
    UserInfo getUserInfoById(long id);
}
