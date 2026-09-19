package com.power.travel.controller;

import com.power.travel.core.Result;
import com.power.travel.model.User;
import com.power.travel.service.UserCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 定义一个控制器类，处理与用户中心相关的请求，所有路径以/user为前缀
@Controller
@RequestMapping("/user")
public class UserCenterController {

    // 自动注入用户中心服务层对象
    @Autowired
    private UserCenterService userCenterService;

    // 映射/centerUI路径的GET请求到centerUI方法，用于显示用户中心界面
    @RequestMapping("/centerUI")
    public String centerUI(Model model, HttpServletRequest request) {
        // 获取当前登录用户的信息
        User user = userCenterService.getUser(request);
        // 将用户信息添加到模型中，供视图层使用
        model.addAttribute("user", user);
        // 返回视图名称"center/user-center"，对应用户中心页面
        return "center/user-center";
    }

    // 映射/centerEditUI路径的GET请求到centerEditUI方法，用于显示用户信息编辑界面
    @RequestMapping("/centerEditUI")
    public String centerEditUI(Model model, HttpServletRequest request) {
        User user = userCenterService.getUser(request);
        model.addAttribute("user", user);
        return "center/user-center-edit";
    }

    // 映射/centerEdit路径的POST请求到centerEdit方法，用于处理用户信息编辑请求
    @RequestMapping("/centerEdit")
    @ResponseBody
    public Result centerEdit(Model model, User user) {
        return userCenterService.centerEdit(user);
    }

    // 映射/centerEditPWUI路径的GET请求到centerEditPWUI方法，用于显示用户密码编辑界面
    @RequestMapping("/centerEditPWUI")
    public String centerEditPWUI(Model model, HttpServletRequest request) {
        User user = userCenterService.getUser(request);
        model.addAttribute("id", user.getId());
        return "center/user-center-editpw";
    }

    // 映射/centerEditPW路径的POST请求到centerEditPW方法，用于处理用户密码编辑请求
    @RequestMapping("/centerEditPW")
    @ResponseBody
    public Result centerEditPW(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam("id") String id, @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {
        return userCenterService.centerEditPW(request, response, id, oldPassword, newPassword);
    }
}
