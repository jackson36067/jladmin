package com.jackson.vo;

import java.time.LocalDateTime;
import java.util.Objects;

public class RoleExportDataVO {
    private String name;
    private Integer level;
    private String description;
    private LocalDateTime createTime;

    public RoleExportDataVO() {
    }

    public RoleExportDataVO(String name, Integer level, String description, LocalDateTime createTime) {
        this.name = name;
        this.level = level;
        this.description = description;
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleExportDataVO that = (RoleExportDataVO) o;
        return Objects.equals(name, that.name) && Objects.equals(level, that.level) && Objects.equals(description, that.description) && Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level, description, createTime);
    }

    @Override
    public String toString() {
        return "RoleExportDataVO{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
