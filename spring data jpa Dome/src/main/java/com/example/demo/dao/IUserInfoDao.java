package com.example.demo.dao;

import com.example.demo.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository(IUserInfoDao.DAO_BEAN_NAME)
public interface IUserInfoDao extends PagingAndSortingRepository<UserInfo,String> {

    /**
     * Spring注册时的名称.
     */
    public static final String DAO_BEAN_NAME = "userInfoDao";
    UserInfo findUserInfoByUserNameAndPassword(String userName,String password);

//    /*******添加新用户********/
//    @Insert("insert into user(name,age,phone) values (#{name},#{age},#{phone})")
//    public int addUser(UserInfo user);
//
//    /*******修改已有用户信息********/
//    @Update("update user set name=#{name},age=#{age},phone=#{phone} where id=#{id}")
//    public int updateUser(UserInfo user);
//
//    /*******根据id删除用户********/
//    @Delete("delete from user where id=#{id}")

}
