package com.jackson.dto;

import java.util.Objects;

public class UpdateTaskDTO {
    private Long id;
    private String jobName;
    private String jobGroup;
    private String className;
    private String description;
    private String cronExpression;
    private String personInCharge;
    private String email;
    private Boolean isPause;

    public UpdateTaskDTO() {
    }

    public UpdateTaskDTO(Long id,String jobName, String jobGroup, String className, String description, String cronExpression, String personInCharge, String email, Boolean isPause) {
        this.id = id;
        this.jobName = jobName;
        this.jobGroup = jobGroup;
        this.className = className;
        this.description = description;
        this.cronExpression = cronExpression;
        this.personInCharge = personInCharge;
        this.email = email;
        this.isPause = isPause;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
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

    public Boolean getisPause() {
        return isPause;
    }

    public void setisPause(Boolean pause) {
        isPause = pause;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateTaskDTO that = (UpdateTaskDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(jobName, that.jobName) && Objects.equals(jobGroup, that.jobGroup) && Objects.equals(className, that.className) && Objects.equals(description, that.description) && Objects.equals(cronExpression, that.cronExpression) && Objects.equals(personInCharge, that.personInCharge) && Objects.equals(email, that.email) && Objects.equals(isPause, that.isPause);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jobName, jobGroup, className, description, cronExpression, personInCharge, email, isPause);
    }

    @Override
    public String toString() {
        return "UpdateTaskDTO{" +
                "id=" + id +
                ", jobName='" + jobName + '\'' +
                ", jobGroup='" + jobGroup + '\'' +
                ", className='" + className + '\'' +
                ", description='" + description + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                ", personInCharge='" + personInCharge + '\'' +
                ", email='" + email + '\'' +
                ", isPause=" + isPause +
                '}';
    }
}
