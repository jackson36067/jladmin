package com.jackson.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 岗位
 */
@Entity
@Table(name = "sys_job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "enabled")
    private Boolean enabled;
    @Column(name = "job_sort")
    private Integer sort;
    @Column(name = "create_by")
    private String createBy;
    @Column(name = "update_by")
    private String updateBy;
    @Column(name = "create_time")
    private LocalDateTime createTime;
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @ManyToMany(mappedBy = "jobSet")
    private Set<User> userSet = new HashSet<>(0);


    public Job() {
    }

    public Job(Long id, String name, Boolean enabled, Integer sort, String createBy, String updateBy, LocalDateTime createTime, LocalDateTime updateTime, Set<User> userSet) {
        this.id = id;
        this.name = name;
        this.enabled = enabled;
        this.sort = sort;
        this.createBy = createBy;
        this.updateBy = updateBy;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.userSet = userSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
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

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return Objects.equals(id, job.id) && Objects.equals(name, job.name) && Objects.equals(enabled, job.enabled) && Objects.equals(sort, job.sort) && Objects.equals(createBy, job.createBy) && Objects.equals(updateBy, job.updateBy) && Objects.equals(createTime, job.createTime) && Objects.equals(updateTime, job.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, enabled, sort, createBy, updateBy, createTime, updateTime);
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                ", sort=" + sort +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
