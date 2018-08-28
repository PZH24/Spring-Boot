package com.example.demo.service;

import com.example.demo.dao.IUserInfoDao;
import com.example.demo.entity.UserInfo;
import com.example.demo.pojo.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserInfoDao userInfoDao;
    @Override
    @CachePut(value = "userinfo",key = "#result.rid")
    public UserInfo addOrUpdateUser(UserInfo userInfo) {
       UserInfo a= userInfoDao.save(userInfo);
       return a;
    }
    @Override
//    @CacheEvict(value = "userinfo",key = "#id")
    public void delete(Long id) {
        System.out.println(id+"执行删除操作");
            //userInfoDao.delete(getUserInfoById(rid));
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

    @Override
    @Cacheable(value = "userinfo",key = "#id")
    public UserInfo getUserInfoById(long id) {
      return userInfoDao.findByRid(id);
    }
}
