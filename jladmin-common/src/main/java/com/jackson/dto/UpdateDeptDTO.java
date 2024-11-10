package com.jackson.dto;

import java.io.Serializable;
import java.util.Objects;

public class UpdateDeptDTO implements Serializable {
    private Long id;
    private String name;
    private Integer deptSort;
    private Long pid;
    private Boolean enabled;

    public UpdateDeptDTO() {
    }

    public UpdateDeptDTO(Long id, String name, Integer deptSort, Long pid, Boolean enabled) {
        this.id = id;
        this.name = name;
        this.deptSort = deptSort;
        this.pid = pid;
        this.enabled = enabled;
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

    public Integer getDeptSort() {
        return deptSort;
    }

    public void setDeptSort(Integer deptSort) {
        this.deptSort = deptSort;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateDeptDTO that = (UpdateDeptDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(deptSort, that.deptSort) && Objects.equals(pid, that.pid) && Objects.equals(enabled, that.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, deptSort, pid, enabled);
    }

    @Override
    public String toString() {
        return "UpdateDeptDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deptSort=" + deptSort +
                ", pid=" + pid +
                ", enabled=" + enabled +
                '}';
    }
}
