package com.example.login.controller;
/*
管理用户信息
 */
public class User {
    private String uname;
    private String upwd;
    public User(String uname, String upwd) {
        super();
        this.uname = uname;
        this.upwd = upwd;
    }
    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getUpwd() {
        return upwd;
    }
    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

}
