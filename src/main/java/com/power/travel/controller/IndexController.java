package com.power.travel.controller;

import com.power.travel.model.TravelRoute;
import com.power.travel.service.ReserveService;
import com.power.travel.service.RouteService;
import com.power.travel.service.StrategyService;
import com.power.travel.model.Attractions;
import com.power.travel.model.Hotel;
import com.power.travel.model.TravelStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

// 声明这是一个控制器类，用于处理HTTP请求
@Controller
public class IndexController {

    // 使用@Autowired注解自动注入ReserveService，用于获取预订相关的服务
    @Autowired
    private ReserveService reserveService;

    // 使用@Autowired注解自动注入RouteService，用于获取路线相关的服务
    @Autowired
    private RouteService routeService;

    // 使用@Autowired注解自动注入StrategyService，用于获取攻略相关的服务
    @Autowired
    private StrategyService strategyService;

    // 使用@RequestMapping注解映射根路径"/"的GET请求到index方法
    @RequestMapping("/")
    public String index(Model model) {
        // 调用reserveService获取预订最多的前10个酒店
        List<Hotel> top10Hotel = reserveService.getTop10Hotel();
        // 调用reserveService获取最受欢迎的前10个景点
        List<Attractions> top10Attractions = reserveService.getTop10Attractions();
        // 调用routeService获取最受欢迎的前10条旅游路线
        List<TravelRoute> top10Route = routeService.findTop10Route();
        // 调用strategyService获取最受欢迎的前10条旅游攻略
        List<TravelStrategy> top10Strategy = strategyService.findTop10Strategy();

        // 将获取到的数据添加到Model中，以便在视图层使用
        model.addAttribute("top10Strategy",top10Strategy);
        model.addAttribute("top10Route", top10Route);
        model.addAttribute("top10Hotel", top10Hotel);
        model.addAttribute("top10Attractions", top10Attractions);
        return "index";// 返回视图名称"index"，用于渲染首页
    }
}
