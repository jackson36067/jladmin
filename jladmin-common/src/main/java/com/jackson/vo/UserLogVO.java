package com.jackson.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class UserLogVO implements Serializable {
    private String requestIp;
    private String address;
    private String description;
    private String browser;
    private Long time;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    public UserLogVO() {
    }

    public UserLogVO(String requestIp, String address, String description, String browser, Long time, LocalDateTime createTime) {
        this.requestIp = requestIp;
        this.address = address;
        this.description = description;
        this.browser = browser;
        this.time = time;
        this.createTime = createTime;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLogVO userLogVO = (UserLogVO) o;
        return Objects.equals(requestIp, userLogVO.requestIp) && Objects.equals(address, userLogVO.address) && Objects.equals(description, userLogVO.description) && Objects.equals(browser, userLogVO.browser) && Objects.equals(time, userLogVO.time) && Objects.equals(createTime, userLogVO.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestIp, address, description, browser, time, createTime);
    }

    @Override
    public String toString() {
        return "UserLogVO{" +
                "requestIp='" + requestIp + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", browser='" + browser + '\'' +
                ", time=" + time +
                ", createTime=" + createTime +
                '}';
    }
}
