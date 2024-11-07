package com.jackson.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "sys_dept")
public class Dept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id")
    private Long id;
    @Column(name = "pid")
    private Long pid; // 上级部门id
    @Column(name = "sub_count")
    private Integer subCount; // 下级部门数量
    @Column(name = "name")
    private String name;
    @Column(name = "dept_sort")
    private Integer deptSort; // 部门排序
    @Column(name = "enabled")
    private Boolean enabled;
    @Column(name = "create_by")
    private String createBy;
    @Column(name = "update_by")
    private String updateBy;
    @Column(name = "create_time")
    private LocalDateTime createTime;
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @OneToMany(mappedBy = "dept")
    private Set<User> userSet = new HashSet<>(0);

    public Dept() {
    }

    public Dept(Long id, Long pid, Integer subCount, String name, Integer deptSort, Boolean enabled, String createBy, String updateBy, LocalDateTime createTime, LocalDateTime updateTime, Set<User> userSet) {
        this.id = id;
        this.pid = pid;
        this.subCount = subCount;
        this.name = name;
        this.deptSort = deptSort;
        this.enabled = enabled;
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

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getSubCount() {
        return subCount;
    }

    public void setSubCount(Integer subCount) {
        this.subCount = subCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDeptSort() {
        return deptSort;
    }

    public void setDeptSort(Integer deptSort) {
        this.deptSort = deptSort;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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
        Dept dept = (Dept) o;
        return Objects.equals(id, dept.id) && Objects.equals(pid, dept.pid) && Objects.equals(subCount, dept.subCount) && Objects.equals(name, dept.name) && Objects.equals(deptSort, dept.deptSort) && Objects.equals(enabled, dept.enabled) && Objects.equals(createBy, dept.createBy) && Objects.equals(updateBy, dept.updateBy) && Objects.equals(createTime, dept.createTime) && Objects.equals(updateTime, dept.updateTime) && Objects.equals(userSet, dept.userSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pid, subCount, name, deptSort, enabled, createBy, updateBy, createTime, updateTime, userSet);
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", pid=" + pid +
                ", subCount=" + subCount +
                ", name='" + name + '\'' +
                ", deptSort=" + deptSort +
                ", enabled=" + enabled +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", userSet=" + userSet +
                '}';
    }
}
