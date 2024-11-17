package com.jackson.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class OnlineUserExportData implements Serializable {
    private String username;
    private String deptName;
    private String ipAddress;
    private String loginLocation;
    private String browser;
    private LocalDateTime loginTime;

    public OnlineUserExportData() {
    }

    public OnlineUserExportData(String username, String deptName, String ipAddress, String loginLocation, String browser, LocalDateTime loginTime) {
        this.username = username;
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
        OnlineUserExportData that = (OnlineUserExportData) o;
        return Objects.equals(username, that.username) && Objects.equals(deptName, that.deptName) && Objects.equals(ipAddress, that.ipAddress) && Objects.equals(loginLocation, that.loginLocation) && Objects.equals(browser, that.browser) && Objects.equals(loginTime, that.loginTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, deptName, ipAddress, loginLocation, browser, loginTime);
    }

    @Override
    public String toString() {
        return "OnlineUserExportData{" +
                "username='" + username + '\'' +
                ", deptName='" + deptName + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", loginLocation='" + loginLocation + '\'' +
                ", browser='" + browser + '\'' +
                ", loginTime=" + loginTime +
                '}';
    }
}
