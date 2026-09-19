package com.power.travel.controller;

import com.power.travel.core.Result;
import com.power.travel.model.User;
import com.power.travel.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 使用@Controller注解声明这是一个控制器类，用于处理登录相关的HTTP请求
@Controller
public class LoginController {

    // 使用@Autowired注解自动注入LoginService，用于处理登录逻辑
    @Autowired
    private LoginService loginService;

    // 映射/loginUI路径的GET请求到loginUI方法，用于显示登录页面
    @RequestMapping("/loginUI")
    public String loginUI() {
        return "login/index-login";
    }

    // 映射/login路径的POST请求到login方法，该方法用于处理登录请求
    @RequestMapping("/login")
    @ResponseBody
    public Result login(Model model, User user, HttpServletResponse response) {
        return loginService.login(user, response);
    }

    // 映射/logout路径的GET请求到logout方法，用于处理用户登出
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        loginService.logout(request, response);
        //SpringMVC重定向
        return "redirect:/";
    }

    // 映射/registerUI路径的GET请求到registerUI方法，用于显示注册页面
    @RequestMapping("/registerUI")
    public String registerUI() {
        return "login/index-register";
    }

    // 映射/register路径的POST请求到register方法，该方法用于处理用户注册请求
    @RequestMapping("/register")
    @ResponseBody
    public Result register(Model model, User user) {
        // 调用loginService的register方法进行用户注册，并返回注册结果
        return loginService.register(user);
    }
}
