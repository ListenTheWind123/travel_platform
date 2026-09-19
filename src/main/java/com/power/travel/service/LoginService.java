package com.power.travel.service;

import com.power.travel.core.Result;
import com.power.travel.core.ResultGenerator;
import com.power.travel.model.User;
import com.power.travel.util.CookieUitl;
import com.power.travel.util.IdGenerator;
import com.power.travel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    // 定义一个登录方法，接收用户信息对象和HttpServletResponse对象作为参数
    public Result login(User user, HttpServletResponse response) {
        // 根据用户名查询用户信息
        User userByUsername = userRepository.findUserByUsername(user.getUsername());
        // 如果用户不存在，返回失败结果，提示用户名错误
        if (userByUsername == null) {
            return ResultGenerator.genFailResult("用户名错误!");
        } else {
            if (user.getPassword().equals(userByUsername.getPassword())) {

                Cookie cookie = new Cookie("username", user.getUsername());
                cookie.setPath("/");
                cookie.setMaxAge(3600);// 有效期1小时
                response.addCookie(cookie);
                return ResultGenerator.genSuccessResult();
            } else {
                return ResultGenerator.genFailResult("密码错误!");
            }
        }

    }

    // 定义一个登出方法，接收HttpServletRequest和HttpServletResponse对象作为参数
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = CookieUitl.get(request, "username");
        if(cookie != null){
            CookieUitl.set(response,"username",null,0);
        }


    }

    // 定义一个注册方法，接收用户信息对象作为参数
    public Result register(User user) {
        User userByUsername = userRepository.findUserByUsername(user.getUsername());
        if(userByUsername != null){
            return ResultGenerator.genFailResult("用户名重复!");
        }
        //Todo 这里有一个事务操作
        user.setId(IdGenerator.id());
        // 保存新用户信息到数据库
        userRepository.save(user);
        return ResultGenerator.genSuccessResult();
    }
}
