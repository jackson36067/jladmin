package com.jackson.aop;

import cn.hutool.json.JSONUtil;
import com.jackson.Repository.UserRepository;
import com.jackson.constant.RedisConstant;
import com.jackson.dto.UserLoginDTO;
import com.jackson.entity.OnlineUser;
import com.jackson.entity.User;
import com.jackson.utils.GeoIPUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 实现登录后自动保存用户信息,用于在线用户信息获取
 */
@Component
@Aspect
public class OnlineUserInfoCacheAspect {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private UserRepository userRepository;
    @Resource
    private GeoIPUtils geoIPUtils;

    /**
     * 切入点
     */
    //满足两个切入点表达式才能匹配上公共字段自动填充逻辑的处理
    @Pointcut("execution(* com.jackson.service.impl.*.*(..)) && @annotation(com.jackson.annotation.CacheOnlineUserInfo)")
    public void cacheOnlineUserInfoPointCut() {
    }

    @After("cacheOnlineUserInfoPointCut()")
    public void cacheOnlineUserInfo(JoinPoint joinPoint) {
        // 规定第一个参数为UserLoginDTO,第二个是HttpServletRequest, 第三个是HttpServletResponse
        Object[] args = joinPoint.getArgs();
        UserLoginDTO userLoginDTO = (UserLoginDTO) args[0];
        HttpServletRequest request = (HttpServletRequest) args[1];
        // 获取用户数据
        User user = userRepository.findUserByUsername(userLoginDTO.getUsername());
        // 获取登录者的ip
        String clientIp = geoIPUtils.getClientIp(request);
        // 获取登录者的地址
        String locationByIp = geoIPUtils.getLocationByIp(clientIp);
        // 获取登录者使用的浏览器
        String browserInfo = geoIPUtils.getBrowserInfo(request);
        OnlineUser onlineUser = new OnlineUser(user.getUsername(), user.getNickName(), user.getDept().getName(), clientIp, locationByIp, browserInfo, LocalDateTime.now());
        // 使用用户id区分不同登录用户的
        // 判断用户是否已经登录

        // 用户判断是否有一致内容
        AtomicReference<Boolean> exists = new AtomicReference<>(false);
        Set<String> onlineUserJsonSet = stringRedisTemplate.opsForZSet().range(RedisConstant.ONLINE_USER_KEY, 0, -1);
        if (onlineUserJsonSet.size() != 0) {
            onlineUserJsonSet.forEach(onlineUserJson -> {
                OnlineUser onlineUser1 = JSONUtil.toBean(onlineUserJson, OnlineUser.class);
                // 比对用户名以及对应ip,存在就不加
                if (onlineUser1.getUsername().equals(onlineUser.getUsername()) && onlineUser1.getIpAddress().equals(onlineUser.getIpAddress())) {
                    // 有一致项,那就改变值
                    exists.set(true);
                }
            });
        }
        // 如果为false就加
        if (!exists.get()) {
            stringRedisTemplate.opsForZSet().add(RedisConstant.ONLINE_USER_KEY, JSONUtil.toJsonStr(onlineUser), System.currentTimeMillis());
        }
    }
}
