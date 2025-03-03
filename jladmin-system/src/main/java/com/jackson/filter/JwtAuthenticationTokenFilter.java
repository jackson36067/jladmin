package com.jackson.filter;

import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONUtil;
import com.jackson.Repository.UserRepository;
import com.jackson.constant.JwtConstant;
import com.jackson.constant.RedisConstant;
import com.jackson.constant.UserConstant;
import com.jackson.context.BaseContext;
import com.jackson.entity.OnlineUser;
import com.jackson.entity.User;
import com.jackson.exception.BlockException;
import com.jackson.exception.TokenExpireException;
import com.jackson.exception.UserNotExistException;
import com.jackson.utils.JwtUtils;
import com.jackson.utils.GeoIPUtils;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private UserRepository userRepository;
    @Resource
    private GeoIPUtils geoIPUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        httpServletResponse.setContentType("application/json;charset=utf-8");
        // 判断当前ip是否被拉黑
        if (isForceLogout(geoIPUtils.getClientIp(httpServletRequest))) {
            // 被拉黑,不允许访问接口,退出到登录页
            SecurityContextHolder.clearContext();
            httpServletResponse.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
            throw new BlockException(UserConstant.USER_BLOCK_ERROR);
        }
        // 登录接口不拦截
        String url = httpServletRequest.getRequestURI(); // 使用URI方法默认会去掉 http:localhost:8080// 前缀
        if (url.startsWith("/admin/user/login") // 登录请求
                || url.startsWith("/admin/user/code") // 获取验证码请求
                || url.startsWith("/doc.html") // swagger请求
                || url.startsWith("/swagger-resources")
                || url.startsWith("/v3/api-docs")
                || url.startsWith("/v2/api-docs")
                || url.startsWith("/webjars/")
                || url.startsWith("/chat") || url.startsWith("/admin/user/upload") // webSocket
        ) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        // 判断token是否为空
        String token = httpServletRequest.getHeader("Authorization");
        if (!StringUtils.hasText(token)) {
            httpServletResponse.setStatus(401);
            httpServletResponse.getWriter().write("token 不能为空");
            return;
        }
        try {
            Claims claims = JwtUtils.parseJwt(token);
            String username = String.valueOf(claims.get(JwtConstant.USERNAME));
            Long userId = Long.valueOf(claims.get(JwtConstant.USER_ID).toString());
            BaseContext.setCurrentId(userId);
            // 授权操作
            authentication(httpServletResponse, username);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (Exception e) {
            // 清空当前用户的在线状态
            clearOnlineUserInfo();
            log.info("令牌解析失败");
            httpServletResponse.setStatus(401);
            throw new TokenExpireException(JwtConstant.TOKEN_EXPIRE);
        }
    }

    /**
     * 为每个请求授权
     *
     * @param httpServletResponse
     * @param username
     */
    private void authentication(HttpServletResponse httpServletResponse, String username) {
        // 判断SecurityContextHolder中的context中是否存储Authentication用户认证对象 -> 为空说明此时需要往SecurityContextHolder中的context中存储Authentication
        // 作用: 以便springSecurity中的登录认证过滤器会直接放行, 也可以减少对数据库的访问
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            // 通过userDetails类中的loadUserByUsername方法根据获取的用户名从数据库中查询该用户, 用于获取用户的用户名,密码以及权限
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (userDetails == null) {
                httpServletResponse.setStatus(401);
                throw new UserNotExistException(UserConstant.USER_NOT_EXIST);
            }
            //  由于设置了无状态登录,在SecurityContextHolder类中就不会缓存Authentication用户信息认证对象
            //  由于后续过滤器练需要使用到Authentication用户信息认证对象(比如授权),这里需要将jwt转换成Authentication用户信息认证对象缓存到SecurityContextHolder中
            //  由于SecurityContextHolder中缓存了用户信息认证对象,所以springSecurity中的登录认证过滤器会直接放行
            authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    /**
     * 清理在线用户账号
     */
    private void clearOnlineUserInfo() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(name);
        ZSetOperations<String, String> stringStringSetOperations = stringRedisTemplate.opsForZSet();
        Set<String> members = stringStringSetOperations.range(RedisConstant.ONLINE_USER_KEY, 0, -1);// 获取所有成员
        for (String member : members) {
            OnlineUser onlineUser = JSONUtil.toBean(member, OnlineUser.class);
            if (onlineUser.getUsername().equals(user.getUsername())) {
                stringStringSetOperations.remove(RedisConstant.ONLINE_USER_KEY, onlineUser);
            }
        }
        // 令牌过期 清除springSecurity中用户数据
        SecurityContextHolder.clearContext();
    }

    /**
     * 检查用户是否被强制退出
     */
    private boolean isForceLogout(String ip) {
        return Boolean.TRUE.equals(stringRedisTemplate.opsForSet().isMember(RedisConstant.FORCE_WITHDRAW_KEY, ip));
    }
}
