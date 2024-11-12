package com.jackson.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "sys_quartz_log")
public class QuartzLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long id;
    @Column(name = "class_name")
    private String className;
    @Column(name = "cron_expression")
    private String cronExpression;
    @Column(name = "exception_detail")
    private String exceptionDetail;
    @Column(name = "is_success")
    private Boolean isSuccess;
    @Column(name = "job_name")
    private String jobName;
    @Column(name = "time")
    private Long time;
    @Column(name = "create_time")
    private LocalDateTime createTime;

    public QuartzLog() {
    }

    public QuartzLog(Long id, String className, String cronExpression, String exceptionDetail, Boolean isSuccess, String jobName, Long time, LocalDateTime createTime) {
        this.id = id;
        this.className = className;
        this.cronExpression = cronExpression;
        this.exceptionDetail = exceptionDetail;
        this.isSuccess = isSuccess;
        this.jobName = jobName;
        this.time = time;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getExceptionDetail() {
        return exceptionDetail;
    }

    public void setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
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
        QuartzLog quartzLog = (QuartzLog) o;
        return Objects.equals(id, quartzLog.id) && Objects.equals(className, quartzLog.className) && Objects.equals(cronExpression, quartzLog.cronExpression) && Objects.equals(exceptionDetail, quartzLog.exceptionDetail) && Objects.equals(isSuccess, quartzLog.isSuccess) && Objects.equals(jobName, quartzLog.jobName) && Objects.equals(time, quartzLog.time) && Objects.equals(createTime, quartzLog.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, className, cronExpression, exceptionDetail, isSuccess, jobName, time, createTime);
    }

    @Override
    public String toString() {
        return "QuartzLog{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                ", exceptionDetail='" + exceptionDetail + '\'' +
                ", isSuccess=" + isSuccess +
                ", jobName='" + jobName + '\'' +
                ", time=" + time +
                ", createTime=" + createTime +
                '}';
    }
}