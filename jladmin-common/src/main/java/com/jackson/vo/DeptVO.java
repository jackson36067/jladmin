package com.jackson.vo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class DeptVO {
    private Long id;
    private Long pid;
    private String name;
    private Integer deptSort; // 部门排序
    private Boolean enabled; // 状态
    private LocalDateTime createTime;
    private List<DeptVO> subDeptVOList;

    public DeptVO() {
    }


    public DeptVO(Long id, Long pid, String name, Integer deptSort, Boolean enabled, LocalDateTime createTime, List<DeptVO> subDeptVOList) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.deptSort = deptSort;
        this.enabled = enabled;
        this.createTime = createTime;
        this.subDeptVOList = subDeptVOList;
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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public List<DeptVO> getSubDeptVOList() {
        return subDeptVOList;
    }

    public void setSubDeptVOList(List<DeptVO> subDeptVOList) {
        this.subDeptVOList = subDeptVOList;
    }

    @Override
    public String toString() {
        return "DeptVO{" +
                "name='" + name + '\'' +
                ", deptSort=" + deptSort +
                ", isEnabled=" + enabled +
                ", createTime=" + createTime +
                ", subDeptVOList=" + subDeptVOList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeptVO deptVO = (DeptVO) o;
        return Objects.equals(name, deptVO.name) && Objects.equals(deptSort, deptVO.deptSort) && Objects.equals(enabled, deptVO.enabled) && Objects.equals(createTime, deptVO.createTime) && Objects.equals(subDeptVOList, deptVO.subDeptVOList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, deptSort, enabled, createTime, subDeptVOList);
    }
}
