package com.jackson.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "sys_log")
@EntityListeners(AuditingEntityListener.class)
public class Log implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long logId;

    @Column(name = "description")
    private String description;

    @Column(name = "log_type")
    private String logType;

    @Column(name = "method")
    private String method;

    @Column(name = "params")
    private String params;

    @Column(name = "request_ip")
    private String requestIp;

    @Column(name = "time")
    private Long time;

    @Column(name = "username")
    private String username;

    @Column(name = "address")
    private String address;

    @Column(name = "browser")
    private String browser;

    @Column(name = "exception_detail")
    private String exceptionDetail;

    @Column(name = "create_time")
    @CreatedDate
    private LocalDateTime createTime;

    public Log() {
    }

    public Log(Long logId, String description, String logType, String method, String params, String requestIp, Long time, String username, String address, String browser, String exceptionDetail, LocalDateTime createTime) {
        this.logId = logId;
        this.description = description;
        this.logType = logType;
        this.method = method;
        this.params = params;
        this.requestIp = requestIp;
        this.time = time;
        this.username = username;
        this.address = address;
        this.browser = browser;
        this.exceptionDetail = exceptionDetail;
        this.createTime = createTime;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
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

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
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
        Log log = (Log) o;
        return Objects.equals(logId, log.logId) && Objects.equals(description, log.description) && Objects.equals(logType, log.logType) && Objects.equals(method, log.method) && Objects.equals(params, log.params) && Objects.equals(requestIp, log.requestIp) && Objects.equals(time, log.time) && Objects.equals(username, log.username) && Objects.equals(address, log.address) && Objects.equals(browser, log.browser) && Objects.equals(exceptionDetail, log.exceptionDetail) && Objects.equals(createTime, log.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logId, description, logType, method, params, requestIp, time, username, address, browser, exceptionDetail, createTime);
    }

    @Override
    public String toString() {
        return "Log{" +
                "logId=" + logId +
                ", description='" + description + '\'' +
                ", logType='" + logType + '\'' +
                ", method='" + method + '\'' +
                ", params='" + params + '\'' +
                ", requestIp='" + requestIp + '\'' +
                ", time=" + time +
                ", username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", browser='" + browser + '\'' +
                ", exceptionDetail='" + exceptionDetail + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}