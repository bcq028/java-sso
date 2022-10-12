package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class app1 {
    @Autowired
    private RestTemplate restTemplate;
    // 获取登录用户信息的 URL，就是访问登录系统的 info 接口
    private final String LOGIN_INFO_URL = "http://login.sso.com:8080/info?token="; // 验证token的登陆页面地址

    // 登录
    @RequestMapping("/index")
    public String toIndex(@CookieValue(required = false, value = "TOKEN") Cookie cookie, HttpSession session) {
        if (cookie != null) {
            // 获取cookie 中 TOKEN 的值
            String token = cookie.getValue();
            if (!ObjectUtils.isEmpty(token)) {
                // 如果 TOKEN 中有值，则发送请求获取登录用户的信息，并存到该系统的 session 缓存中
                Map map = restTemplate.getForObject(LOGIN_INFO_URL + token, Map.class);
                session.setAttribute("loginUser", map);
            } else {
                // 如果 TOKEN 没有值，代表没有登录或者已经退出，应该清除缓存中的登录用户信息
                session.setAttribute("loginUser", null);
            }
        }

        return "index";
    }

    // 注销
    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session, HttpServletResponse response) {
        // 清除session中用户登录信息
        session.setAttribute("loginUser", null);
        // 清除cookie中 TOKEN 的值
        Cookie cookie = new Cookie("TOKEN", "null");
        cookie.setDomain("sso.com");
        response.addCookie(cookie);
        // 重定向到该系统的首页
        return "redirect:/index";
    }
}
