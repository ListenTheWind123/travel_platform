package com.power.travel.controller;

import com.power.travel.core.Result;
import com.power.travel.service.StrategyService;
import com.power.travel.model.TravelStrategy;
import com.power.travel.model.UserStrategy;
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
@RequestMapping("/strategy")
public class StrategyController {

    @Autowired
    private StrategyService strategyService;

    // 映射/travelStrategyListUI路径的GET请求到travelStrategyListUI方法，用于显示旅行攻略列表界面
    @RequestMapping("/travelStrategyListUI")
    public String travelStrategyListUI(Model model, @ModelAttribute("searchName") String searchName, @PageableDefault(size = 10) Pageable pageable) {
        // 调用strategyService的TravelStrategyListUI方法获取分页的旅行攻略列表
        Page<TravelStrategy> page = strategyService.TravelStrategyListUI(searchName, pageable);
        List<TravelStrategy> top10Strategy = strategyService.findTop10Strategy();
        model.addAttribute("top10Strategy", top10Strategy);
        model.addAttribute("page", page);
        return "strategy/travelStrategy";
    }

    // 映射/travelStrategyDetailsUI路径的GET请求到travelStrategyDetailsUI方法，用于显示旅行攻略详情界面
    @RequestMapping("/travelStrategyDetailsUI")
    public String travelStrategyDetailsUI(Model model, HttpServletRequest request, @RequestParam(name = "id") String id) {
        TravelStrategy travelStrategy = strategyService.findTravelStrategyById(id);
        //如果用户显示已经关注,就是查看关注列表
        Boolean flag = strategyService.isStrategy(request, id);
        List<TravelStrategy> top10Strategy = strategyService.findTop10Strategy();
        model.addAttribute("top10Strategy", top10Strategy);
        model.addAttribute("travelStrategy", travelStrategy);
        model.addAttribute("flag", flag);
        return "strategy/travelStrategy-details";
    }

    // 映射/cancelTravelStrategyReserve路径的POST请求到cancelTravelStrategyReserve方法，用于取消旅行攻略预订
    @RequestMapping("/cancelTravelStrategyReserve")
    @ResponseBody
    public Result cancelTravelStrategyReserve(HttpServletRequest request, String id) {
        return strategyService.cancelTravelStrategyReserve(request, id);
    }

    // 映射/strategyManageUI路径的GET请求到strategyManageUI方法，     用于显示用户旅行攻略管理界面
    @RequestMapping("/strategyManageUI")
    public String strategyManageUI(Model model, HttpServletRequest request) {
        List<UserStrategy> userStrategyList = strategyService.getTravelStrategyByUser(request);
        List<TravelStrategy> top10Strategy = strategyService.findTop10Strategy();
        model.addAttribute("top10Strategy", top10Strategy);
        model.addAttribute("userStrategyList", userStrategyList);
        return "strategy/strategy-manage";
    }

    // 映射/saveTravelStrategy路径的POST请求到saveTravelStrategy方法，用于保存旅行攻略
    @RequestMapping("/saveTravelStrategy")
    @ResponseBody
    public Result saveTravelStrategy(HttpServletRequest request, TravelStrategy travelStrategy) {
        return strategyService.saveTravelStrategy(request, travelStrategy);
    }

    // 映射/pushStrategyListUI路径的GET请求到pushStrategyListUI方法，用于显示推送的旅行攻略列表界面
    @RequestMapping("/pushStrategyListUI")
    public String pushStrategyListUI(HttpServletRequest request, Model model, @ModelAttribute("searchName") String searchName, @PageableDefault(size = 10) Pageable pageable) {
        Page<TravelStrategy> page = strategyService.PushStrategyListUI(request,searchName, pageable);
        List<TravelStrategy> top10Strategy = strategyService.findTop10Strategy();
        model.addAttribute("top10Strategy", top10Strategy);
        model.addAttribute("page", page);
        return "strategy/pushStrategy";
    }
}
