package com.jackson.aop;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.jackson.Repository.LogRepository;
import com.jackson.Repository.UserRepository;
import com.jackson.annotation.SysLog;
import com.jackson.context.BaseContext;
import com.jackson.entity.Log;
import com.jackson.entity.User;
import com.jackson.utils.GeoIPUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 切面类获取操作日志
 */
@Component
@Aspect
public class LogAspect {

    @Resource
    private GeoIPUtils geoIPUtils;
    @Resource
    private LogRepository logRepository;
    @Resource
    private HttpServletRequest request; // 获取request
    @Resource
    private UserRepository userRepository;

    @Pointcut("@annotation(com.jackson.annotation.SysLog)")
    public void logPointcut() {
    }

    @Around("logPointcut()")
    @Transactional
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        try {
            // 执行方法
            Object proceed = point.proceed();
            // 执行保存日志方法
            saveLog(point, System.currentTimeMillis() - beginTime, null);
            return proceed;
        } catch (Exception e) {
            saveLog(point, System.currentTimeMillis() - beginTime, e.getMessage());
            throw e;
        }
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time, String exceptionDetail) throws ClassNotFoundException, NoSuchMethodException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log log = new Log();
        // 获取注解上的描述信息以及操作类型
        SysLog sysLog = method.getAnnotation(SysLog.class);
        if (sysLog != null) {
            log.setDescription(sysLog.value());
            log.setLogType(sysLog.type().name());
        }
        // 获取请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.setMethod(className + "." + methodName + "()");

        // 获取参数名
        String[] parameterNames = signature.getParameterNames();
        // 获取参数值
        Object[] parameterValues = joinPoint.getArgs();
        // 将参数组织为 key:value 形式
        Map<String, Object> params = new HashMap<>();
        for (int i = 0; i < parameterNames.length; i++) {
            Object paramValue = parameterValues[i];
            // 如果参数值是 JavaBean，则将其展开为 JSON 格式
            if (isJavaBean(paramValue)) {
                HashMap<String, Object> map = new HashMap<>();
                params.put(parameterNames[i], BeanUtil.beanToMap(paramValue, map, false, true));
            } else {
                params.put(parameterNames[i], paramValue);
            }
        }
        if (methodName.equals("login")) {
            // 去除参数中多余的request
            params.remove("request");
        }
        // 转换为 JSON 格式
        String jsonParams = JSONUtil.toJsonStr(params);
        log.setParams(jsonParams);

        // 设置IP地址
        String ip = geoIPUtils.getClientIp(request);
        log.setRequestIp(ip);

        // 设置浏览器信息
        log.setBrowser(geoIPUtils.getBrowserInfo(request));

        // 设置操作者地址
        log.setAddress(geoIPUtils.getLocationByIp(ip));

        // 设置其他信息
        log.setTime(time);

        // 如果操作出现异常设置异常信息 (没异常直接传递null值过来)
        if (exceptionDetail != null) {
            log.setExceptionDetail(exceptionDetail);
        }

        // 设置当前登录用户名
        if (methodName.equals("login")) {
            // 如果是登录请求,那么用户名就是第一个参数中的第一个属性
            try {
                Field firstField = parameterValues[0].getClass().getDeclaredField("username");
                // 获取第一个属性
                // 设置属性可访问
                firstField.setAccessible(true);
                // 返回第一个属性的值
                log.setUsername(firstField.get(parameterValues[0]).toString());
            } catch (IllegalAccessException | NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        } else {
            Long currentId = BaseContext.getCurrentId();
            User user = userRepository.findById(currentId).get();
            log.setUsername(user.getUsername());
        }
        // 保存操作日志
        logRepository.save(log);
    }

    /**
     * 判断是否为 JavaBean 对象（排除基本类型、包装类、String 等）
     */
    private boolean isJavaBean(Object obj) {
        if (obj == null) {
            return false;
        }

        Class<?> clazz = obj.getClass();

        // 判断是否为基本类型或常见不可展开类型
        if (clazz.isPrimitive() ||
                clazz.isEnum() ||
                clazz.equals(String.class) ||
                Number.class.isAssignableFrom(clazz) ||
                Boolean.class.isAssignableFrom(clazz) ||
                clazz.getPackage().getName().startsWith("java.")) {
            return false;
        }

        // 判断是否为动态代理
        if (Proxy.isProxyClass(clazz)) {
            return false;
        }

        // 判断是否具有默认构造器（JavaBean 的典型特征）
        try {
            clazz.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            return false;
        }
        return true;
    }

}

