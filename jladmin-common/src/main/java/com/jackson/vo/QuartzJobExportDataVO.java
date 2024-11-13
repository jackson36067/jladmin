package com.jackson.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class QuartzJobExportDataVO implements Serializable {
    private String jobName;
    private String className;
    private String cronExpression;
    private Boolean isPause;
    private String description;
    private LocalDateTime createTime;

    public QuartzJobExportDataVO() {
    }

    public QuartzJobExportDataVO(String jobName, String className, String cronExpression, Boolean isPause, String description, LocalDateTime createTime) {
        this.jobName = jobName;
        this.className = className;
        this.cronExpression = cronExpression;
        this.isPause = isPause;
        this.description = description;
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

    public Boolean getisPause() {
        return isPause;
    }

    public void setisPause(Boolean pause) {
        isPause = pause;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "QuartzJobExportDataVO{" +
                "jobName='" + jobName + '\'' +
                ", className='" + className + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                ", isPause=" + isPause +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuartzJobExportDataVO that = (QuartzJobExportDataVO) o;
        return Objects.equals(jobName, that.jobName) && Objects.equals(className, that.className) && Objects.equals(cronExpression, that.cronExpression) && Objects.equals(isPause, that.isPause) && Objects.equals(description, that.description) && Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobName, className, cronExpression, isPause, description, createTime);
    }
}
