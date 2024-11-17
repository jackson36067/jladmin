package com.jackson.utils;

import cn.hutool.json.JSONObject;
import com.jackson.constant.UserConstant;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
public class GeoIPUtils {

    /**
     * 获取登录者的ip
     *
     * @param request
     * @return
     */
    public String getClientIp(HttpServletRequest request) {
        String ip = null;
        // X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");
        if (ipAddresses == null || ipAddresses.isEmpty() || "unknown".equalsIgnoreCase(ipAddresses)) {
            // Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.isEmpty() || "unknown".equalsIgnoreCase(ipAddresses)) {
            // WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.isEmpty() || "unknown".equalsIgnoreCase(ipAddresses)) {
            // HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddresses == null || ipAddresses.isEmpty() || "unknown".equalsIgnoreCase(ipAddresses)) {
            // X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }
        if (ipAddresses == null || ipAddresses.isEmpty() || "unknown".equalsIgnoreCase(ipAddresses)) {
            // 获取真实IP
            ipAddresses = request.getRemoteAddr();
        }

        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddresses != null && !ipAddresses.isEmpty()) {
            ip = ipAddresses.split(",")[0];
        }

        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    /**
     * 获取登录者的浏览器
     *
     * @param request
     * @return
     */
    public String getBrowserInfo(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        // 定义正则表达式来提取浏览器名称和版本号
        String regex = "(Chrome|Firefox|Safari|Edge|MSIE|Trident)\\/(\\d+\\.\\d+)";

        // 创建 Pattern 和 Matcher 对象
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userAgent);

        // 如果匹配成功
        if (matcher.find()) {
            String browserName = matcher.group(1);  // 浏览器名称
            String browserVersion = matcher.group(2);  // 浏览器版本号
            // 返回格式化的浏览器信息
            return browserName + " " + browserVersion.split("\\.")[0];  // 提取主版本号
        }
        return userAgent;
    }

    /**
     * 获取登录者的登陆地
     *
     * @param ipAddress
     * @return
     */
    public String getLocationByIp(String ipAddress) {
        if ("127.0.0.1".equals(ipAddress) || "localhost".equalsIgnoreCase(ipAddress)) {
            return "内网IP";  // 或者返回其他默认值
        }
        String url = UserConstant.IP_API_URL + ipAddress;
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        JSONObject jsonResponse = new JSONObject(response);
        if ("success".equalsIgnoreCase(jsonResponse.get("status").toString())) {
            String country = jsonResponse.get("country") != null ? jsonResponse.get("country").toString() : "";
            String region = jsonResponse.get("regionName") != null ? jsonResponse.get("regionName").toString() : "";
            String city = jsonResponse.get("city") != null ? jsonResponse.get("city").toString() : "";
            return country + ", " + region + ", " + city;
        }
        return "Unknown Location";
    }
}
