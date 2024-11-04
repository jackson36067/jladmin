package com.jackson.config;

import cn.hutool.json.JSONUtil;
import com.jackson.filter.JwtAuthenticationTokenFilter;
import com.jackson.hanlder.MyAccessDeniedHandler;
import com.jackson.hanlder.MyAuthenticationFailureHandler;
import com.jackson.result.Result;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    /**
     * 配置密码加密器
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 使用Bcrypt密码加密器
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置自定义的登录认证方式
     *
     * @param httpSecurity
     * @return
     * @throws Exception
     */
    @Bean
    public DefaultSecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(authorization -> {
                            authorization
                                    .requestMatchers("/admin/user/login", "/admin/user/code")
                                    .permitAll()
                                    .anyRequest()
                                    .authenticated();
                        }
                )
                .formLogin(
                        formLogin -> {
                            // 登录成功后执行的处理器
                            // formLogin.successHandler(new MyAuthenticationSuccessHandler());
                            // 登录失败后执行的处理器
                            formLogin.failureHandler(new MyAuthenticationFailureHandler());
                        }
                )
                .logout(logout -> logout.clearAuthentication(true)) // 退出登录时清理认证信息
                .sessionManagement(
                        sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // 异常处理
                .exceptionHandling(exceptionHandling -> {
                    // 授权异常处理
                    exceptionHandling.accessDeniedHandler(new MyAccessDeniedHandler());
                    // 登录时异常处理
                    exceptionHandling.authenticationEntryPoint((request, response, authException) -> {
                        response.setContentType("application/json;charset=utf-8");
                        response.setStatus(401);
                        Result<Object> error = Result.error("has error：" + authException.getMessage());
                        response.getWriter().write(JSONUtil.toJsonStr(error));
                    });
                })
                // 添加自定义拦截器
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    // 没有使用jwt之前, 使用spring security 默认的session登录 这个对象框架自动默认操作
    // 使用jwt, 放弃使用spring security 默认的session登录, 意味着需要自己去控制登录逻辑
    // 此时需要在spring security 认证框架规则基础上执行自定义jwt登录认证
    // 所谓认证框架爱规则基础: 还是遵循spring security认证流程
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        // 配置用户详情服务等
        return authenticationManagerBuilder.build();
    }

    /**
     * 解决跨域问题
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:5173");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
