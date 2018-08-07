package com.example.demo.service;

import com.example.demo.dao.IUserInfoDao;
import com.example.demo.entity.UserInfo;
import com.example.demo.pojo.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserInfoDao userInfoDao;
    @Override
    public boolean addOrUpdateUser(UserInfo userInfo) {
       UserInfo a= userInfoDao.save(userInfo);
       if(a==null)
           return false;
       return true;
    }
    @Override
    public void delete(String rid) {

            userInfoDao.deleteById(rid);
    }

    @Override
    public List<UserInfo> getAllUserInfo() {
      return (List<UserInfo>)userInfoDao.findAll();
    }

    @Override
    public String isLogin(String name, String password) {
       UserInfo userInfo= userInfoDao.findUserInfoByUserNameAndPassword(name,password);
       if(userInfo==null)
        return "login";
        String result = "login";
       switch (userInfo.getRigth())   {
           case 1:
               result = "index";
               break;
           case 3:
               result = "editUser";
               break;
           case 127:
               result = "AllUserlst";
               break;
           default:
               result = "index";
               break;
       }
       return result;
    }
}
