package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = UserInfo.TableName)
public class UserInfo {
    /**
     * 数据库表名.
     */
    public static final String TableName = "userInfo";
    private  Long rid;
    private  String userName;
    private  String password;
    private  int rigth;

    public  UserInfo (long rid,String userName,String password,int rigth){
        this.rid=rid;
        this.userName=userName;
        this.password=password;
        this.rigth=rigth;

    }
    public  UserInfo(){

    }
    @Id
    @GeneratedValue
    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }
    @Column(name="USERNAME", length=200)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @Column(name="PASSWORD", length=200)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Column(name="RIGTH", length=50)
    public int getRigth() {
        return rigth;
    }

    public void setRigth(int rigth) {
        this.rigth = rigth;
    }
}
