package com.power.travel.service;
//系统首页服务
import com.power.travel.core.Result;
import com.power.travel.core.ResultGenerator;
import com.power.travel.core.ServiceException;
import com.power.travel.enums.StatusEnum;
import com.power.travel.model.*;
import com.power.travel.repository.*;
import com.power.travel.util.CookieUitl;
import com.power.travel.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.criteria.Predicate;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class SystemService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private AttractionsRepository attractionsRepository;

    @Autowired
    private TravelRouteRepository travelRouteRepository;

    @Autowired
    private TravelStrategyRepository travelStrategyRepository;

    private Random random = new Random(100);

    // 定义一个方法，用于处理系统用户的登录
    public Result login(SysUser sysUser, HttpServletResponse response) {
        // 根据用户名查询系统用户
        SysUser sysUserByUsrname = sysUserRepository.findSysUserByUsername(sysUser.getUsername());
        if (sysUserByUsrname == null) {
            return ResultGenerator.genFailResult("用户名错误！");
        } else {
            // 如果密码匹配，设置用户名Cookie并返回成功结果
            if (sysUser.getPassword().equals(sysUserByUsrname.getPassword())) {
                CookieUitl.set(response, "sysUsername", sysUser.getUsername(), 3600);
                return ResultGenerator.genSuccessResult();
            } else {
                return ResultGenerator.genFailResult("密码错误");
            }
        }

    }

    // 定义一个方法，用于处理系统用户的登出
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = CookieUitl.get(request, "sysUsername");
        if (cookie != null) {
            CookieUitl.set(response, "sysUsername", null, 0);
        }
    }

    // 定义一个方法，获取用户分页信息
    public Page<User> getUserPage(Pageable pageable) {
        Page<User> userPage = userRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            query.where(predicates.toArray(new Predicate[]{}));
            query.orderBy(cb.desc(root.get("id")));
            return null;
        }, pageable);
        return userPage;
    }

    // 定义一个带有事务管理的方法，用于保存用户信息
    @Transactional(rollbackFor = Exception.class)
    public Result saveUser(User user) {

        System.out.println(user.getId());
        // 如果用户ID为空，则为新增用户
        if (StringUtils.isEmpty(user.getId())) {//没有id的情况
            user.setId(IdGenerator.id());
        } else {
            // 如果用户ID不为空，则为更新用户信息
            User oldUser = getUserById(user.getId());
            user.setUsername(oldUser.getUsername());
            user.setName(oldUser.getName());
            oldUser.setPassword(user.getPassword());
        }

        userRepository.saveAndFlush(user);
        return ResultGenerator.genSuccessResult();
    }

    // 定义一个方法，根据ID获取用户信息
    public User getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ServiceException("用户ID错误"));
        return user;
    }

    // 定义一个方法，获取酒店分页信息
    public Page<Hotel> getHotelPage(Pageable pageable) {
        Page<Hotel> hotelPage = hotelRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            query.where(predicates.toArray(new Predicate[]{}));
            query.orderBy(cb.desc(root.get("createDate")));
            return null;
        }, pageable);
        return hotelPage;
    }

    // 定义一个带有事务管理的方法，用于保存酒店信息
    @Transactional(rollbackFor = Exception.class)
    public Result saveHotel(Hotel hotel) {

        if (StringUtils.isEmpty(hotel.getId())) {//没有id的情况
            hotel.setId(IdGenerator.id());
            hotel.setStatus(StatusEnum.DOWM_STATUS.getCode());
            hotel.setCreateDate(new Date());
            int i = random.nextInt(100);
            hotel.setImage("MY_kezhan_0" + (i % 8 + 1));
        } else {
            //有id的情况
            Hotel oldHotel = getHotelById(hotel.getId());
            hotel.setStatus(oldHotel.getStatus());
            hotel.setCreateDate(oldHotel.getCreateDate());
            hotel.setImage(oldHotel.getImage());
        }
        hotelRepository.saveAndFlush(hotel);
        return ResultGenerator.genSuccessResult();
    }

    // 定义一个方法，根据ID获取酒店信息
    public Hotel getHotelById(String id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ServiceException("酒店ID错误!"));
        return hotel;
    }

    // 定义一个方法，更新酒店状态
    public Result updateStatus(String id) {
        Hotel hotel = getHotelById(id);
        if (hotel.getStatus().equals(StatusEnum.DOWM_STATUS.getCode())) {
            //改变状态
            hotel.setStatus(StatusEnum.UP_STATUS.getCode());
        } else {
            hotel.setStatus(StatusEnum.DOWM_STATUS.getCode());
        }
        hotelRepository.saveAndFlush(hotel);
        return ResultGenerator.genSuccessResult();
    }

    // 定义一个方法，获取景点分页信息
    public Page<Attractions> getAttractionsPage(Pageable pageable) {
        Page<Attractions> attractionsPage = attractionsRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            query.where(predicates.toArray(new Predicate[]{}));
            query.orderBy(cb.desc(root.get("createDate")));
            return null;
        }, pageable);
        return attractionsPage;
    }

    // 定义一个方法，根据ID获取景点信息
    public Attractions getAttractionsById(String id) {
        Attractions attractions = attractionsRepository.findById(id).orElseThrow(() -> new ServiceException("景点ID错误"));
        return attractions;
    }

    // 定义一个方法，更新景点状态
    public Result updateAttractionsStatus(String id) {
        Attractions attractions = getAttractionsById(id);
        if (attractions.getStatus().equals(StatusEnum.DOWM_STATUS.getCode())) {
            //改变状态
            attractions.setStatus(StatusEnum.UP_STATUS.getCode());
        } else {
            attractions.setStatus(StatusEnum.DOWM_STATUS.getCode());
        }
        attractionsRepository.saveAndFlush(attractions);
        return ResultGenerator.genSuccessResult();
    }

    // 定义一个带有事务管理的方法，用于保存景点信息
    @Transactional(rollbackFor = Exception.class)
    public Result saveAttractions(Attractions attractions) {
        if (StringUtils.isEmpty(attractions.getId())) {//没有id的情况
            attractions.setId(IdGenerator.id());
            attractions.setStatus(StatusEnum.DOWM_STATUS.getCode());
            attractions.setCreateDate(new Date());
            int i = random.nextInt(100);
            attractions.setImage("MY_jingdian_0" + (i % 8 + 1));
        } else {
            //有id的情况
            Attractions oldAttractions = getAttractionsById(attractions.getId());
            attractions.setStatus(oldAttractions.getStatus());
            attractions.setCreateDate(oldAttractions.getCreateDate());
            attractions.setImage(oldAttractions.getImage());
        }
        attractionsRepository.saveAndFlush(attractions);
        return ResultGenerator.genSuccessResult();
    }

    // 定义一个方法，获取旅行路线分页信息
    public Page<TravelRoute> getTravelRoutePage(Pageable pageable) {
        Page<TravelRoute> travelRoutePage = travelRouteRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            query.where(predicates.toArray(new Predicate[]{}));
            query.orderBy(cb.desc(root.get("createDate")));
            return null;
        }, pageable);
        return travelRoutePage;
    }

    // 定义一个带有事务管理的方法，用于保存旅行路线信息
    @Transactional(rollbackFor = Exception.class)
    public Result saveTravelRoute(TravelRoute travelRoute) {

        if (StringUtils.isEmpty(travelRoute.getId())) {//没有id的情况
            travelRoute.setId(IdGenerator.id());
            if (travelRoute.getStatus() == null) {
                //默认为停用
                travelRoute.setStatus(StatusEnum.DOWM_STATUS.getCode());
                travelRoute.setCollectNumber(0);
                travelRoute.setCreateDate(new Date());
            }
        } else {
            //有id的情况
            TravelRoute oldTravelRoute = getTravelRouteById(travelRoute.getId());
            travelRoute.setStatus(oldTravelRoute.getStatus());
            travelRoute.setCollectNumber(oldTravelRoute.getCollectNumber());
            travelRoute.setCreateDate(oldTravelRoute.getCreateDate());
        }
        travelRouteRepository.saveAndFlush(travelRoute);
        return ResultGenerator.genSuccessResult();
    }

    // 定义一个方法，根据ID获取旅行路线信息
    public TravelRoute getTravelRouteById(String id) {
        TravelRoute travelRoute = travelRouteRepository.findById(id).orElseThrow(() -> new ServiceException("路线ID错误!"));
        return travelRoute;
    }

    // 定义一个方法，更新旅行路线状态
    public Result updateTravelRouteStatus(String id) {
        TravelRoute travelRoute = getTravelRouteById(id);
        if (travelRoute.getStatus().equals(StatusEnum.DOWM_STATUS.getCode())) {
            //改变状态
            travelRoute.setStatus(StatusEnum.UP_STATUS.getCode());
        } else {
            travelRoute.setStatus(StatusEnum.DOWM_STATUS.getCode());
        }
        travelRouteRepository.saveAndFlush(travelRoute);
        return ResultGenerator.genSuccessResult();
    }

    // 定义一个方法，获取旅行攻略分页信息
    public Page<TravelStrategy> getTravelStrategyPage(Pageable pageable) {
        Page<TravelStrategy> travelStrategyPage = travelStrategyRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            query.where(predicates.toArray(new Predicate[]{}));
            query.orderBy(cb.desc(root.get("createDate")));
            return null;
        }, pageable);
        return travelStrategyPage;
    }

    // 定义一个方法，根据ID获取旅行攻略信息
    public TravelStrategy getTravelStrategyById(String id) {
        TravelStrategy travelStrategy = travelStrategyRepository.findById(id).orElseThrow(() -> new ServiceException("攻略ID错误!"));
        return travelStrategy;
    }

    // 定义一个方法，更新旅行攻略状态
    public Result updateTravelStrategyStatus(String id) {
        TravelStrategy travelStrategy = getTravelStrategyById(id);
        if (travelStrategy.getStatus().equals(StatusEnum.DOWM_STATUS.getCode())) {
            //改变状态
            travelStrategy.setStatus(StatusEnum.UP_STATUS.getCode());
        } else {
            travelStrategy.setStatus(StatusEnum.DOWM_STATUS.getCode());
        }
        travelStrategyRepository.saveAndFlush(travelStrategy);
        return ResultGenerator.genSuccessResult();
    }


    // 定义一个带有事务管理的方法，用于保存旅行攻略信息
    @Transactional(rollbackFor = Exception.class)
    public Result saveTravelStrategy(HttpServletRequest request, TravelStrategy travelStrategy) {

        if (StringUtils.isEmpty(travelStrategy.getId())) {//没有id的情况
            travelStrategy.setId(IdGenerator.id());
            if (travelStrategy.getStatus() == null) {
                // 如果状态为空，则默认为停用
                travelStrategy.setStatus(StatusEnum.DOWM_STATUS.getCode());
                travelStrategy.setCreateDate(new Date());
            }
        } else {
            // 如果旅行攻略ID不为空，则为更新旅行攻略信息
            TravelStrategy oldTravelStrategy = getTravelStrategyById(travelStrategy.getId());
            travelStrategy.setStatus(StatusEnum.Third_STATUS.getCode());
            travelStrategy.setCreateDate(oldTravelStrategy.getCreateDate());
            travelStrategy.setUser(oldTravelStrategy.getUser());
            travelStrategy.setTitle(oldTravelStrategy.getTitle());
            travelStrategy.setDescribe(oldTravelStrategy.getDescribe());
            // 从请求中获取错误信息参数
            travelStrategy.setErrorMessage(request.getParameter("errorMessage"));
        }
        // 保存并刷新旅行攻略信息
        travelStrategyRepository.saveAndFlush(travelStrategy);
        return ResultGenerator.genSuccessResult();
    }
}
