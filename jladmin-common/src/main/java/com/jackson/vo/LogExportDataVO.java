package com.jackson.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class LogExportDataVO implements Serializable {
    private String username;
    private String requestIp;
    private String address;
    private String description;
    private String browser;
    private Long time;
    private String exceptionDetail;
    private LocalDateTime createTime;

    public LogExportDataVO() {
    }

    public LogExportDataVO(String username, String requestIp, String address, String description, String browser, Long time, String exceptionDetail, LocalDateTime createTime) {
        this.username = username;
        this.requestIp = requestIp;
        this.address = address;
        this.description = description;
        this.browser = browser;
        this.time = time;
        this.exceptionDetail = exceptionDetail;
        this.createTime = createTime;
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

    public String getExceptionDetail() {
        return exceptionDetail;
    }

    public void setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
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
        LogExportDataVO that = (LogExportDataVO) o;
        return Objects.equals(username, that.username) && Objects.equals(requestIp, that.requestIp) && Objects.equals(address, that.address) && Objects.equals(description, that.description) && Objects.equals(browser, that.browser) && Objects.equals(time, that.time) && Objects.equals(exceptionDetail, that.exceptionDetail) && Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, requestIp, address, description, browser, time, exceptionDetail, createTime);
    }

    @Override
    public String toString() {
        return "LogExportDataVO{" +
                "username='" + username + '\'' +
                ", requestIp='" + requestIp + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", browser='" + browser + '\'' +
                ", time=" + time +
                ", exceptionDetail='" + exceptionDetail + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
