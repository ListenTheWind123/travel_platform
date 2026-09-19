package com.power.travel.controller;

import com.power.travel.core.Result;
import com.power.travel.core.ResultGenerator;
import com.power.travel.model.*;
import com.power.travel.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/system")
public class SystemController {
    @Autowired
    private SystemService systemService;

    // 映射到路径""，用于显示登录界面
    @RequestMapping("")
    public String loginUI() {
        return "system/login/login";
    }

    // 映射到路径"/login"，用于处理登录请求
    @RequestMapping("/login")
    @ResponseBody
    public Result login(SysUser sysUser, HttpServletResponse response) {

       return systemService.login(sysUser,response);
    }

    // 映射到路径"/userListUI"，用于显示用户列表界面
    @RequestMapping("/userListUI")
    public String userListUI(Model model, @PageableDefault(size = 10) Pageable pageable) {
        Page<User> page = systemService.getUserPage(pageable);
        model.addAttribute("page",page);
        return "system/user/list";
    }

    //用于保存用户信息
    @RequestMapping("/saveUser")
    @ResponseBody
    public Result saveUser(User user) {
        return systemService.saveUser(user);
    }

    //根据用户ID获取用户信息
    @RequestMapping("/getUserById")
    @ResponseBody
    public Result getUserById(String id) {
        return ResultGenerator.genSuccessResult(systemService.getUserById(id));
    }

    //用于处理用户登出
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
       systemService.logout(request,response);
        return "redirect:/system";
    }

    //用于显示酒店列表界面
    @RequestMapping("/hotelListUI")
    public String hotelListUI(Model model, @PageableDefault(size = 10) Pageable pageable) {
        Page<Hotel> page = systemService.getHotelPage(pageable);
        model.addAttribute("page", page);
        return "system/hotel/list";
    }

    //用于保存酒店信息
    @RequestMapping("/saveHotel")
    @ResponseBody
    public Result saveHotel(Hotel hotel) {
        return systemService.saveHotel(hotel);
    }

    //用于更新酒店状态
    @RequestMapping("/updateStatus")
    @ResponseBody
    public Result updateStatus(String id) {
        return systemService.updateStatus(id);
    }

    //根据酒店ID获取酒店信息
    @RequestMapping("/getHotelById")
    @ResponseBody
    public Result getHotelById(String id) {
        return ResultGenerator.genSuccessResult(systemService.getHotelById(id));
    }

    //用于显示景点列表界面
    @RequestMapping("/attractionsListUI")
    public String attractionsListUI(Model model, @PageableDefault(size = 10) Pageable pageable) {
        Page<Attractions> page = systemService.getAttractionsPage(pageable);
        model.addAttribute("page", page);
        return "system/attractions/list";
    }

    //根据景点ID获取景点信息
    @RequestMapping("/getAttractionsById")
    @ResponseBody
    public Result getAttractionsById(String id) {
        return ResultGenerator.genSuccessResult(systemService.getAttractionsById(id));
    }

    //用于更新景点状态
    @RequestMapping("/updateAttractionsStatus")
    @ResponseBody
    public Result updateAttractionsStatus(String id) {
        return systemService.updateAttractionsStatus(id);
    }

    //用于保存景点信息
    @RequestMapping("/saveAttractions")
    @ResponseBody
    public Result saveAttractions(Attractions attractions) {
        return systemService.saveAttractions(attractions);
    }

    //用于显示旅游路线列表界面
    @RequestMapping("/travelRouteListUI")
    public String travelRouteListUI(Model model, @PageableDefault(size = 10) Pageable pageable) {
        Page<TravelRoute> page = systemService.getTravelRoutePage(pageable);
        model.addAttribute("page", page);
        return "system/route/list";
    }

    //根据旅游路线ID获取旅游路线信息
    @RequestMapping("/getTravelRouteById")
    @ResponseBody
    public Result getTravelRouteById(String id) {
        return ResultGenerator.genSuccessResult(systemService.getTravelRouteById(id));
    }

    //用于更新旅游路线的状态
    @RequestMapping("/updateTravelRouteStatus")
    @ResponseBody
    public Result updateTravelRouteStatus(String id) {
        return systemService.updateTravelRouteStatus(id);
    }

    //用于保存旅游路线信息
    @RequestMapping("/saveTravelRoute")
    @ResponseBody
    public Result saveTravelRoute(TravelRoute travelRoute) {
        return systemService.saveTravelRoute(travelRoute);
    }

    //用于显示旅游攻略列表界面
    @RequestMapping("/travelStrategyListUI")
    public String travelStrategyListUI(Model model, @PageableDefault(size = 10) Pageable pageable) {
        Page<TravelStrategy> page = systemService.getTravelStrategyPage(pageable);
        model.addAttribute("page", page);
        return "system/strategy/list";
    }

    //根据旅游攻略ID获取旅游攻略信息
    @RequestMapping("/getTravelStrategyById")
    @ResponseBody
    public Result getTravelStrategyById(String id) {
        return ResultGenerator.genSuccessResult(systemService.getTravelStrategyById(id));
    }

    //用于更新旅游攻略的状态
    @RequestMapping("/updateTravelStrategyStatus")
    @ResponseBody
    public Result updateTravelStrategyStatus(String id) {
        return systemService.updateTravelStrategyStatus(id);
    }

    //用于保存旅游攻略信息
    @RequestMapping("/saveTravelStrategy")
    @ResponseBody
    public Result saveTravelStrategy(HttpServletRequest request,TravelStrategy travelStrategy) {
        return systemService.saveTravelStrategy(request,travelStrategy);
    }
}
