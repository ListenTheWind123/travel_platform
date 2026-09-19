package com.power.travel.service;

import com.power.travel.core.Result;
import com.power.travel.core.ResultGenerator;
import com.power.travel.core.ServiceException;
import com.power.travel.model.*;
import com.power.travel.repository.*;
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
public class ReserveService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private AttractionsRepository attractionsRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHotelRepository userHotelRepository;

    @Autowired
    private UserAttractionsRepository userAttractionsRepository;

    // 定义一个方法，用于获取符合条件的酒店列表，并分页显示
    public Page<Hotel> reserveHotelListUI(String searchName, Pageable pageable) {
        //查询启用的酒店列表
        Page<Hotel> hotelPage = hotelRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            //status状态,查询状态为0,启动的酒店
            predicates.add((cb.equal(root.get("status"), 0)));
            //酒店name模糊查询
            if (!StringUtils.isEmpty(searchName)) {
                predicates.add((cb.like(root.get("name"), "%" + searchName + "%")));
            }
            query.where(predicates.toArray(new Predicate[]{}));
            query.orderBy(cb.desc(root.get("createDate")));
            return null;
        }, pageable);
        return hotelPage;
    }

    // 定义一个方法，根据ID查找酒店
    public Hotel findHotelById(String id) {
        return hotelRepository.findById(id).orElseThrow(() -> new ServiceException("酒店id错误!"));
    }

    // 文字匹配查询，用于获取符合条件的景点列表，并分页显示
    public Page<Attractions> reserveAttractionsListUI(String searchName, Pageable pageable) {
        //查询启用的景点列表
        Page<Attractions> attractionsPage = attractionsRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            //status状态,查询状态为0,启动的景点
            predicates.add((cb.equal(root.get("status"), 0)));
            //景点name模糊查询
            if (!StringUtils.isEmpty(searchName)) {
                predicates.add((cb.like(root.get("name"), "%" + searchName + "%")));
            }
            query.where(predicates.toArray(new Predicate[]{}));
            query.orderBy(cb.desc(root.get("createDate")));
            return null;
        }, pageable);
        // 返回查询到的景点分页对象
        return attractionsPage;
    }

    // 定义一个方法，根据ID查找景点
    public Attractions findAttractionsById(String id) {
        return attractionsRepository.findById(id).orElseThrow(() -> new ServiceException("景点id错误!"));
    }

    // 文字匹配查询，获取当前用户预约的酒店列表
    public List<UserHotel> getReserveHotelByUser(HttpServletRequest request) {
        // 从请求中获取用户名Cookie
        Cookie cookie = CookieUitl.get(request, "username");
        if (cookie == null) {
            throw new ServiceException("未能获得正确的用户名");
        }
        User user = userRepository.findUserByUsername(cookie.getValue());
        return userHotelRepository.findUserHotelsByUser(user);
    }

    // 定义一个方法，处理酒店预约的取消或添加，带有事务管理
    @Transactional(rollbackFor = Exception.class)
    public Result cancelReserve(HttpServletRequest request, String id) {
        Cookie cookie = CookieUitl.get(request, "username");
        if (cookie == null) {
            throw new ServiceException("用户没有登录!");
        }
        Hotel hotel = findHotelById(id);
        User user = userRepository.findUserByUsername(cookie.getValue());
        UserHotel userHotel = userHotelRepository.findUserHotelByHotelAndUser(hotel, user);
        //存在值就是取消预约.不存在值就是预约
        if (userHotel != null) {
            userHotelRepository.delete(userHotel);
        } else {
            UserHotel newUserHotel = new UserHotel();
            newUserHotel.setId(IdGenerator.id());
            newUserHotel.setCreateDate(new Date());
            newUserHotel.setUser(user);
            newUserHotel.setHotel(hotel);
            userHotelRepository.saveAndFlush(newUserHotel);
        }
        return ResultGenerator.genSuccessResult();
    }

    // 定义一个方法，检查当前用户是否已预约某个酒店
    public Boolean isReserveHotel(HttpServletRequest request, String id) {
        // 从请求中获取名为"username"的Cookie
        Cookie cookie = CookieUitl.get(request, "username");
        if (cookie != null) {
            User user = userRepository.findUserByUsername(cookie.getValue());
            Hotel hotel = findHotelById(id);
            UserHotel userHotel = userHotelRepository.findUserHotelByHotelAndUser(hotel, user);
            //每个酒店只能预约一次
            if (userHotel != null) {
                return true;
            }
        }
        return false;
    }

    // 定义一个方法，获取当前用户预约的景点列表
    public List<UserAttractions> getReserveAttractionsByUser(HttpServletRequest request) {
        Cookie cookie = CookieUitl.get(request, "username");
        if (cookie == null) {
            throw new ServiceException("未能获得正确的用户名");
        }
        User user = userRepository.findUserByUsername(cookie.getValue());
        return userAttractionsRepository.findUserAttractionsByUser(user);
    }

    // 定义一个方法，处理景点预约的取消或添加，带有事务管理
    @Transactional(rollbackFor = Exception.class)
    public Result cancelAttractionsReserve(HttpServletRequest request, String id) {
        Cookie cookie = CookieUitl.get(request, "username");
        if (cookie == null) {
            throw new ServiceException("用户没有登录!");
        }
        Attractions attractions = findAttractionsById(id);
        User user = userRepository.findUserByUsername(cookie.getValue());
        UserAttractions userAttractions = userAttractionsRepository.findUserAttractionsByAttractionsAndUser(attractions, user);
        //存在值就是取消预约.不存在值就是预约
        if (userAttractions != null) {
            userAttractionsRepository.delete(userAttractions);
        } else {
            UserAttractions newUserAttractions = new UserAttractions();
            newUserAttractions.setId(IdGenerator.id());
            newUserAttractions.setCreateDate(new Date());
            newUserAttractions.setUser(user);
            newUserAttractions.setAttractions(attractions);
            userAttractionsRepository.saveAndFlush(newUserAttractions);
        }
        return ResultGenerator.genSuccessResult();
    }

    // 定义一个方法，检查当前用户是否已预约某个景点
    public Boolean isReserveAttractions(HttpServletRequest request, String id) {
        Cookie cookie = CookieUitl.get(request, "username");
        if (cookie != null) {
            User user = userRepository.findUserByUsername(cookie.getValue());
            Attractions attractions = findAttractionsById(id);
            UserAttractions userAttractions = userAttractionsRepository.findUserAttractionsByAttractionsAndUser(attractions, user);
            //每个景点只能预约一次
            if (userAttractions != null) {
                return true;
            }
        }
        return false;
    }

    // 定义一个方法，获取前10个启用的酒店
    public List<Hotel> getTop10Hotel() {
        PageRequest pageable = PageRequest.of(0, 10);
        //查询启用的酒店列表
        Page<Hotel> hotelPage = hotelRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            //status状态,查询状态为0,启动的酒店
            predicates.add((cb.equal(root.get("status"), 0)));
            query.where(predicates.toArray(new Predicate[]{}));
            // 按创建日期降序排序
            query.orderBy(cb.desc(root.get("createDate")));
            return null;
        }, pageable);
        // 返回查询到的酒店列表
        return hotelPage.getContent();
    }

    // 定义一个方法，获取前10个启用的景点
    public List<Attractions> getTop10Attractions() {
        PageRequest pageable = PageRequest.of(0, 10);
        Page<Attractions> attractionsPage = attractionsRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            //status状态,查询状态为0,启动的景点
            predicates.add((cb.equal(root.get("status"), 0)));
            //景点name模糊查询
            query.where(predicates.toArray(new Predicate[]{}));
            // 按创建日期降序排序
            query.orderBy(cb.desc(root.get("createDate")));
            return null;
        }, pageable);
        return attractionsPage.getContent();
    }
}

