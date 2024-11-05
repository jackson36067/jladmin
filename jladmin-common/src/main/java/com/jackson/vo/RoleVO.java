package com.jackson.vo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class RoleVO {
    private Long id;
    private String name;
    private String dataScope;
    private String description;
    private Integer level;
    private LocalDateTime createTime;
    private List<Long> menuIdList;

    public RoleVO() {
    }

    public RoleVO(Long id, String name, String dataScope, String description, Integer level, LocalDateTime createTime,List<Long> menuIdList) {
        this.id = id;
        this.name = name;
        this.dataScope = dataScope;
        this.description = description;
        this.level = level;
        this.createTime = createTime;
        this.menuIdList = menuIdList;
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

    public String getDataScope() {
        return dataScope;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public List<Long> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<Long> menuIdList) {
        this.menuIdList = menuIdList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleVO roleVO = (RoleVO) o;
        return Objects.equals(id, roleVO.id) && Objects.equals(name, roleVO.name) && Objects.equals(dataScope, roleVO.dataScope) && Objects.equals(description, roleVO.description) && Objects.equals(level, roleVO.level) && Objects.equals(createTime, roleVO.createTime) && Objects.equals(menuIdList, roleVO.menuIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dataScope, description, level, createTime, menuIdList);
    }

    @Override
    public String toString() {
        return "RoleVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dataScope='" + dataScope + '\'' +
                ", description='" + description + '\'' +
                ", level=" + level +
                ", createTime=" + createTime +
                ", menuIdList=" + menuIdList +
                '}';
    }
}
