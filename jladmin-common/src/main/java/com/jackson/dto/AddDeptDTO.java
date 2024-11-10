package com.jackson.dto;

import java.io.Serializable;
import java.util.Objects;

public class AddDeptDTO implements Serializable {
    private String name;
    private Integer deptSort;
    private Boolean enabled;
    private Long pid;

    public AddDeptDTO() {
    }

    public AddDeptDTO(String name, Integer deptSort, Boolean enabled, Long pid) {
        this.name = name;
        this.deptSort = deptSort;
        this.enabled = enabled;
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

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddDeptDTO that = (AddDeptDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(deptSort, that.deptSort) && Objects.equals(enabled, that.enabled) && Objects.equals(pid, that.pid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, deptSort, enabled, pid);
    }

    @Override
    public String toString() {
        return "AddDeptDTO{" +
                "name='" + name + '\'' +
                ", deptSort=" + deptSort +
                ", enabled=" + enabled +
                ", pid=" + pid +
                '}';
    }
}
