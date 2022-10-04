package com.example.login.controller;

import User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/*
控制页面跳转
*/
@Controller
@RequestMapping("/page")    //
public class pageController {
    @RequestMapping("/login")
    //判断跳转到登录页
    public String toLogin(@RequestParam(required = false, defaultValue = "")String target,
                          HttpSession session, @CookieValue (required = false, value = "TOKEN") Cookie cookie) {
        // 如果是已登录的用户再次访问登录页面，就要重定向
        if(cookie != null) {
            String value = cookie.getValue();
            User user = loginCache.loginUser.get(value);
            if(user != null) {
                return "redirect:" + target;
            }
        }
        // 用于登录成功后重定向地址
        session.setAttribute("target", target);
        return "login";
    }
    //判断跳转到主页
    @RequestMapping("/index")
    public String loginIndex() {
        return "login";
    }
}
