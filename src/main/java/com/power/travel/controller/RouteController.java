package com.power.travel.controller;

import com.power.travel.service.RouteService;
import com.power.travel.core.Result;
import com.power.travel.model.TravelRoute;
import com.power.travel.model.UserRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/route")
public class RouteController {
    // 自动注入RouteService，用于处理与旅行路线相关的业务逻辑
    @Autowired
    private RouteService routeService;

    // 映射/travelRouteListUI路径的GET请求到travelRouteListUI方法，用于显示旅行路线列表界面
    @RequestMapping("/travelRouteListUI")
    public String travelRouteListUI(Model model, @ModelAttribute("searchName") String searchName, @PageableDefault(size = 10) Pageable pageable) {
        Page<TravelRoute> page = routeService.TravelRouteListUI(searchName, pageable);
        List<TravelRoute> top10Route = routeService.findTop10Route();
        model.addAttribute("top10Route", top10Route);
        model.addAttribute("page", page);
        return "route/travelRoute";// 返回视图名称"route/travelRoute"，对应旅行路线列表页面
    }

    // 映射/travelRouteDetailsUI路径的GET请求到travelRouteDetailsUI方法，用于显示旅行路线详情界面
    @RequestMapping("/travelRouteDetailsUI")
    public String travelRouteDetailsUI(Model model, HttpServletRequest request, @RequestParam(name = "id") String id) {
        TravelRoute travelRoute = routeService.findTravelRouteById(id);
        //如果用户显示已经关注,就是查看关注列表
        Boolean flag = routeService.isRoute(request, id);
        List<TravelRoute> top10Route = routeService.findTop10Route();
        model.addAttribute("top10Route", top10Route);
        model.addAttribute("travelRoute", travelRoute);
        model.addAttribute("flag", flag);
        return "route/travelRoute-details";
    }

    // 映射/routeManageUI路径的GET请求到routeManageUI方法，    用于显示用户旅行路线管理界面
    @RequestMapping("/routeManageUI")
    public String routeManageUI(Model model, HttpServletRequest request) {
        List<UserRoute> userRouteList = routeService.getTravelRouteByUser(request);
        model.addAttribute("userRouteList", userRouteList);
        return "route/route-user-manage";
    }

    // 映射/cancelTravelRouteReserve路径的POST请求到cancelTravelRouteReserve方法，用于取消旅行路线预订
    @RequestMapping("/cancelTravelRouteReserve")
    @ResponseBody
    public Result cancelTravelRouteReserve(HttpServletRequest request, String id) {
        return routeService.cancelTravelRouteReserve(request, id);
    }
}
