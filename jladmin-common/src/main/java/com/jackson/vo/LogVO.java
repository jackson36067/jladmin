package com.jackson.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class LogVO implements Serializable {
    private String username;
    private String requestIp;
    private String address;
    private String description;
    private String browser;
    private Long time;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    private String method;
    private String params;
    private String exceptionDetail;

    public LogVO() {
    }

    public LogVO(String username, String requestIp, String address, String description, String browser, Long time, LocalDateTime createTime, String method, String params,String exceptionDetail) {
        this.username = username;
        this.requestIp = requestIp;
        this.address = address;
        this.description = description;
        this.browser = browser;
        this.time = time;
        this.createTime = createTime;
        this.method = method;
        this.params = params;
        this.exceptionDetail = exceptionDetail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getExceptionDetail() {
        return exceptionDetail;
    }

    public void setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogVO logVO = (LogVO) o;
        return Objects.equals(username, logVO.username) && Objects.equals(requestIp, logVO.requestIp) && Objects.equals(address, logVO.address) && Objects.equals(description, logVO.description) && Objects.equals(browser, logVO.browser) && Objects.equals(time, logVO.time) && Objects.equals(createTime, logVO.createTime) && Objects.equals(method, logVO.method) && Objects.equals(params, logVO.params) && Objects.equals(exceptionDetail, logVO.exceptionDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, requestIp, address, description, browser, time, createTime, method, params, exceptionDetail);
    }

    @Override
    public String toString() {
        return "LogVO{" +
                "username='" + username + '\'' +
                ", requestIp='" + requestIp + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", browser='" + browser + '\'' +
                ", time=" + time +
                ", createTime=" + createTime +
                ", method='" + method + '\'' +
                ", params='" + params + '\'' +
                ", exceptionDetail='" + exceptionDetail + '\'' +
                '}';
    }
}
