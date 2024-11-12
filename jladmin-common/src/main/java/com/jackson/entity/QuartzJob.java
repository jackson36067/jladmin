package com.jackson.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Table(name = "sys_quartz_job")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class QuartzJob implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long id;

    @Column(name = "class_name")
    private String className;

    @Column(name = "cron_expression")
    private String cronExpression;

    @Column(name = "is_pause")
    private Boolean isPause;

    @Column(name = "job_name")
    private String jobName;

    @Column(name = "job_group")
    private String jobGroup;

    @Column(name = "description")
    private String description;

    @Column(name = "person_in_charge")
    private String personInCharge;

    @Column(name = "email")
    private String email;

    @Column(name = "create_by")
    @CreatedBy
    private String createBy;

    @Column(name = "update_by")
    @LastModifiedBy
    private String update_by;

    @Column(name = "create_time")
    @CreatedDate
    private LocalDateTime createTime;

    @Column(name = "update_time")
    @LastModifiedDate
    private LocalDateTime updateTime;

    public QuartzJob() {
    }

    public QuartzJob(Long id, String className, String cronExpression, Boolean isPause, String jobName, String jobGroup, String description, String personInCharge, String email, String createBy, String update_by, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.className = className;
        this.cronExpression = cronExpression;
        this.isPause = isPause;
        this.jobName = jobName;
        this.jobGroup = jobGroup;
        this.description = description;
        this.personInCharge = personInCharge;
        this.email = email;
        this.createBy = createBy;
        this.update_by = update_by;
        this.createTime = createTime;
        this.updateTime = updateTime;
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

    public Boolean getisPause() {
        return isPause;
    }

    public void setisPause(Boolean pause) {
        isPause = pause;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPersonInCharge() {
        return personInCharge;
    }

    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuartzJob quartzJob = (QuartzJob) o;
        return Objects.equals(id, quartzJob.id) && Objects.equals(className, quartzJob.className) && Objects.equals(cronExpression, quartzJob.cronExpression) && Objects.equals(isPause, quartzJob.isPause) && Objects.equals(jobName, quartzJob.jobName) && Objects.equals(jobGroup, quartzJob.jobGroup) && Objects.equals(description, quartzJob.description) && Objects.equals(personInCharge, quartzJob.personInCharge) && Objects.equals(email, quartzJob.email) && Objects.equals(createBy, quartzJob.createBy) && Objects.equals(update_by, quartzJob.update_by) && Objects.equals(createTime, quartzJob.createTime) && Objects.equals(updateTime, quartzJob.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, className, cronExpression, isPause, jobName, jobGroup, description, personInCharge, email, createBy, update_by, createTime, updateTime);
    }

    @Override
    public String toString() {
        return "QuartzJob{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                ", isPause=" + isPause +
                ", jobName='" + jobName + '\'' +
                ", jobGroup='" + jobGroup + '\'' +
                ", description='" + description + '\'' +
                ", personInCharge='" + personInCharge + '\'' +
                ", email='" + email + '\'' +
                ", createBy='" + createBy + '\'' +
                ", update_by='" + update_by + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
