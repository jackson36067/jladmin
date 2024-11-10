package com.jackson.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class DeptExportDataVO implements Serializable {
    private String name;
    private Boolean enabled;
    private LocalDateTime createTime;

    public DeptExportDataVO() {
    }

    public DeptExportDataVO(String name, Boolean enabled, LocalDateTime createTime) {
        this.name = name;
        this.enabled = enabled;
        this.createTime = createTime;
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
        DeptExportDataVO that = (DeptExportDataVO) o;
        return Objects.equals(name, that.name) && Objects.equals(enabled, that.enabled) && Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, enabled, createTime);
    }

    @Override
    public String toString() {
        return "DeptExportDataVO{" +
                "name='" + name + '\'' +
                ", enabled=" + enabled +
                ", createTime=" + createTime +
                '}';
    }
}
