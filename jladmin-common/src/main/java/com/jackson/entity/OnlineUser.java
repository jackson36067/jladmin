package com.jackson.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Objects;

public class OnlineUser {
    private String username;
    private String name;
    private String deptName;
    private String ipAddress;
    private String loginLocation;
    private String browser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginTime;

    public OnlineUser() {
    }

    public OnlineUser(String username, String name, String deptName, String ipAddress, String loginLocation, String browser, LocalDateTime loginTime) {
        this.username = username;
        this.name = name;
        this.deptName = deptName;
        this.ipAddress = ipAddress;
        this.loginLocation = loginLocation;
        this.browser = browser;
        this.loginTime = loginTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getLoginLocation() {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation) {
        this.loginLocation = loginLocation;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OnlineUser that = (OnlineUser) o;
        return Objects.equals(username, that.username) && Objects.equals(name, that.name) && Objects.equals(deptName, that.deptName) && Objects.equals(ipAddress, that.ipAddress) && Objects.equals(loginLocation, that.loginLocation) && Objects.equals(browser, that.browser) && Objects.equals(loginTime, that.loginTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, name, deptName, ipAddress, loginLocation, browser, loginTime);
    }

    @Override
    public String toString() {
        return "OnlineUser{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", deptName='" + deptName + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", loginLocation='" + loginLocation + '\'' +
                ", browser='" + browser + '\'' +
                ", loginTime=" + loginTime +
                '}';
    }
}
