package com.power.travel.service;


import com.power.travel.core.Result;
import com.power.travel.core.ResultGenerator;
import com.power.travel.core.ServiceException;
import com.power.travel.model.TravelRoute;
import com.power.travel.model.User;
import com.power.travel.model.UserRoute;
import com.power.travel.repository.TravelRouteRepository;
import com.power.travel.repository.UserRepository;
import com.power.travel.repository.UserRouteRepository;
import com.power.travel.util.CookieUitl;
import com.power.travel.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.criteria.Predicate;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class RouteService {

    @Autowired
    private TravelRouteRepository travelRouteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRouteRepository userRouteRepository;

    // 定义一个方法，用于获取符合条件的景区游玩路线列表，并分页显示
    public Page<TravelRoute> TravelRouteListUI(String searchName, Pageable pageable) {
        //查询启用的景区游玩路线列表
        Page<TravelRoute> travelRoutePage = travelRouteRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            //status状态,查询状态为0,启动的路线
            predicates.add((cb.equal(root.get("status"), 0)));
            //景区游玩路线name模糊查询
            if (!StringUtils.isEmpty(searchName)) {
                predicates.add((cb.like(root.get("name"), "%" + searchName + "%")));
            }
            query.where(predicates.toArray(new Predicate[]{}));
            query.orderBy(cb.desc(root.get("createDate")));
            return null;
        }, pageable);
        return travelRoutePage;
    }

    // 定义一个方法，根据ID查找特定的景区游玩路线
    public TravelRoute findTravelRouteById(String id) {
        return travelRouteRepository.findById(id).orElseThrow(() -> new ServiceException("路线id错误!"));
    }

    public Boolean isRoute(HttpServletRequest request, String id) {
        Cookie cookie = CookieUitl.get(request, "username");
        if (cookie != null) {
            User user = userRepository.findUserByUsername(cookie.getValue());
            TravelRoute travelRoute = findTravelRouteById(id);
            UserRoute userRoute = userRouteRepository.findUserRouteByTravelRouteAndUser(travelRoute, user);
            //每个路线只能关注一次
            if (userRoute != null) {
                return true;
            }
        }
        return false;
    }

    // 定义一个方法，获取当前用户关注的路线列表
    public List<UserRoute> getTravelRouteByUser(HttpServletRequest request) {
        Cookie cookie = CookieUitl.get(request, "username");
        if (cookie == null) {
            throw new ServiceException("未能获得正确的用户名");
        }
        User user = userRepository.findUserByUsername(cookie.getValue());
        return userRouteRepository.findUserRouteByUser(user);
    }

    // 定义一个带有事务管理的方法，处理路线的关注或取消关注
    @Transactional(rollbackFor = Exception.class)
    public Result cancelTravelRouteReserve(HttpServletRequest request, String id) {
        Cookie cookie = CookieUitl.get(request, "username");
        if (cookie == null) {
            throw new ServiceException("用户没有登录!");
        }
        TravelRoute travelRoute = findTravelRouteById(id);
        User user = userRepository.findUserByUsername(cookie.getValue());
        UserRoute userRoute = userRouteRepository.findUserRouteByTravelRouteAndUser(travelRoute, user);
        //存在值就是取消预约.不存在值就是预约
        // 如果已关注，取消关注
        if (userRoute != null) {
            userRouteRepository.delete(userRoute);
        } else {
            // 如果未关注，添加关注
            UserRoute newUserRoute = new UserRoute();
            newUserRoute.setId(IdGenerator.id());
            newUserRoute.setCreateDate(new Date());
            newUserRoute.setUser(user);
            newUserRoute.setTravelRoute(travelRoute);
            userRouteRepository.saveAndFlush(newUserRoute);
        }
        return ResultGenerator.genSuccessResult();
    }

    // 定义一个方法，获取前10个启用的景区游玩路线
    public List<TravelRoute> findTop10Route() {
        // 创建分页请求，只获取前10条记录
        PageRequest pageable = PageRequest.of(0, 10);
        //查询启用的景区游玩路线列表
        Page<TravelRoute> travelRoutePage = travelRouteRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            //status状态,查询状态为0,启动的路线
            predicates.add((cb.equal(root.get("status"), 0)));
            query.where(predicates.toArray(new Predicate[]{}));
            query.orderBy(cb.desc(root.get("createDate")));
            return null;
        }, pageable);
        return travelRoutePage.getContent();
    }
}
