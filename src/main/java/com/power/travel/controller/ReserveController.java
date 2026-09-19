package com.power.travel.controller;

import com.power.travel.core.Result;
import com.power.travel.service.ReserveService;
import com.power.travel.model.Attractions;
import com.power.travel.model.Hotel;
import com.power.travel.model.UserAttractions;
import com.power.travel.model.UserHotel;
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

// 使用@Controller注解声明这是一个控制器类，用于处理预订相关的HTTP请求
// 使用@RequestMapping注解设置基础路径为"/reserve"
@Controller
@RequestMapping("/reserve")
public class ReserveController {
    // 使用@Autowired注解自动注入ReserveService，用于处理预订服务逻辑
    @Autowired
    private ReserveService reserveService;

    // 映射/reserveHotelListUI路径的GET请求到reserveHotelListUI方法，用于显示酒店预订列表界面
    @RequestMapping("/reserveHotelListUI")
    public String reserveHotelListUI(Model model, @ModelAttribute("searchName") String searchName, @PageableDefault(size = 10) Pageable pageable) {
        Page<Hotel> page = reserveService.reserveHotelListUI(searchName, pageable);
        List<Hotel> top10Hotel = reserveService.getTop10Hotel();
        List<Attractions> top10Attractions = reserveService.getTop10Attractions();
        model.addAttribute("top10Hotel", top10Hotel);
        model.addAttribute("top10Attractions", top10Attractions);
        model.addAttribute("page", page);
        return "reserve/reserve-hotel";
    }
    // 映射/hotelDetailsUI路径的GET请求到hotelDetailsUI方法，用于显示酒店详情界面
    @RequestMapping("/hotelDetailsUI")
    public String hotelDetailsUI(Model model, HttpServletRequest request, @RequestParam(name = "id") String id) {
        Hotel hotel = reserveService.findHotelById(id);
        //如果用户显示已经预约,就是查看预约列表
        Boolean flag = reserveService.isReserveHotel(request, id);
        List<Hotel> top10Hotel = reserveService.getTop10Hotel();
        List<Attractions> top10Attractions = reserveService.getTop10Attractions();
        model.addAttribute("top10Hotel", top10Hotel);
        model.addAttribute("top10Attractions", top10Attractions);
        model.addAttribute("hotel", hotel);
        model.addAttribute("flag", flag);
        return "reserve/reserve-hotel-details";
    }

    // 映射/reserveManageUI路径的GET请求到reserveManageUI方法，    用于显示用户预订管理界面
    @RequestMapping("/reserveManageUI")
    public String reserveManageUI(Model model, HttpServletRequest request) {
        List<UserHotel> userHotelList = reserveService.getReserveHotelByUser(request);
        List<UserAttractions> userAttractionsList = reserveService.getReserveAttractionsByUser(request);
        model.addAttribute("userHotelList", userHotelList);
        model.addAttribute("userAttractionsList",userAttractionsList);
        return "reserve/reserve-user-manage";
    }

    // 映射/cancelReserve路径的POST请求到cancelReserve方法，用于取消酒店预订
    @RequestMapping("/cancelReserve")
    @ResponseBody
    public Result cancelReserve(HttpServletRequest request, String id) {
        return reserveService.cancelReserve(request,id);
    }

    // 映射/reserveAttractionsListUI路径的GET请求到reserveAttractionsListUI方法，用于显示景点预订列表界面
    @RequestMapping("/reserveAttractionsListUI")
    public String reserveAttractionsListUI(Model model, @ModelAttribute("searchName") String searchName, @PageableDefault(size = 10) Pageable pageable) {
        Page<Attractions> page = reserveService.reserveAttractionsListUI(searchName,pageable);
        List<Hotel> top10Hotel = reserveService.getTop10Hotel();
        List<Attractions> top10Attractions = reserveService.getTop10Attractions();
        model.addAttribute("top10Hotel", top10Hotel);
        model.addAttribute("top10Attractions", top10Attractions);
        model.addAttribute("page", page);
        return "reserve/reserve-attractions";
    }

    // 映射/attractionsDetailsUI路径的GET请求到attractionsDetailsUI方法，用于显示景点详情界面
    @RequestMapping("/attractionsDetailsUI")
    public String attractionsDetailsUI(Model model, HttpServletRequest request, @RequestParam(name = "id") String id) {
        Attractions attractions = reserveService.findAttractionsById(id);
        //如果用户显示已经预约,就是查看预约列表
        Boolean flag = reserveService.isReserveAttractions(request, id);// 检查用户是否已经预订了该景点
        List<Hotel> top10Hotel = reserveService.getTop10Hotel();
        List<Attractions> top10Attractions = reserveService.getTop10Attractions();
        // 将数据添加到模型中，供视图层使用
        model.addAttribute("top10Hotel", top10Hotel);
        model.addAttribute("top10Attractions", top10Attractions);
        model.addAttribute("attractions", attractions);
        model.addAttribute("flag", flag);
        return "reserve/reserve-attractions-details";
    }

    // 映射/cancelAttractionsReserve路径的POST请求到cancelAttractionsReserve方法，用于取消景点预订
    @RequestMapping("/cancelAttractionsReserve")
    @ResponseBody
    public Result cancelAttractionsReserve(HttpServletRequest request,String id) {
        return reserveService.cancelAttractionsReserve(request,id);// 调用reserveService取消景点预订，并返回操作结果
    }

}
