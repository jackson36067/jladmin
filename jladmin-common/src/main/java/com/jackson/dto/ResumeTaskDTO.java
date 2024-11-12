package com.jackson.dto;

import java.io.Serializable;
import java.util.Objects;

public class ResumeTaskDTO implements Serializable {
    private Long id;
    private String jobName;
    private String jobGroup;

    public ResumeTaskDTO() {
    }

    public ResumeTaskDTO(Long id, String jobName, String jobGroup, Boolean isPause) {
        this.id = id;
        this.jobName = jobName;
        this.jobGroup = jobGroup;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResumeTaskDTO that = (ResumeTaskDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(jobName, that.jobName) && Objects.equals(jobGroup, that.jobGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jobName, jobGroup);
    }

    @Override
    public String toString() {
        return "ResumeTaskDTO{" +
                "id=" + id +
                ", jobName='" + jobName + '\'' +
                ", jobGroup='" + jobGroup + '\'' +
                '}';
    }
}
