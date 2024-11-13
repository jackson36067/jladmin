package com.jackson.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class QuartzLogExportDataVO implements Serializable {
    private String jobName;
    private String className;
    private String cronExpression;
    private String exceptionDetail;
    private Long time;
    private Boolean isSuccess;
    private LocalDateTime createTime;

    public QuartzLogExportDataVO() {
    }

    public QuartzLogExportDataVO(String jobName, String className, String cronExpression, String exceptionDetail, Long time, Boolean isSuccess, LocalDateTime createTime) {
        this.jobName = jobName;
        this.className = className;
        this.cronExpression = cronExpression;
        this.exceptionDetail = exceptionDetail;
        this.time = time;
        this.isSuccess = isSuccess;
        this.createTime = createTime;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
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

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Boolean getisSuccess() {
        return isSuccess;
    }

    public void setisSuccess(Boolean success) {
        isSuccess = success;
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
        QuartzLogExportDataVO that = (QuartzLogExportDataVO) o;
        return Objects.equals(jobName, that.jobName) && Objects.equals(className, that.className) && Objects.equals(cronExpression, that.cronExpression) && Objects.equals(exceptionDetail, that.exceptionDetail) && Objects.equals(time, that.time) && Objects.equals(isSuccess, that.isSuccess) && Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobName, className, cronExpression, exceptionDetail, time, isSuccess, createTime);
    }

    @Override
    public String toString() {
        return "QuartzLogExportDataVO{" +
                "jobName='" + jobName + '\'' +
                ", className='" + className + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                ", exceptionDetail='" + exceptionDetail + '\'' +
                ", time=" + time +
                ", isSuccess=" + isSuccess +
                ", createTime=" + createTime +
                '}';
    }
}
