package com.jackson.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class UpdateRoleDTO implements Serializable {
    private Long id;
    private String name;
    private Integer level;
    private String dataScope; // 角色数据描述
    private List<Long> permissionList; // 新增的角色的菜单id列表
    private String description;

    public UpdateRoleDTO() {
    }

    public UpdateRoleDTO(Long id,String name, Integer lever, String dataScope, List<Long> permissionList, String description) {
        this.id = id;
        this.name = name;
        this.level = lever;
        this.dataScope = dataScope;
        this.permissionList = permissionList;
        this.description = description;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer lever) {
        this.level = lever;
    }

    public String getDataScope() {
        return dataScope;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }

    public List<Long> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Long> permissionList) {
        this.permissionList = permissionList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateRoleDTO that = (UpdateRoleDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(level, that.level) && Objects.equals(dataScope, that.dataScope) && Objects.equals(permissionList, that.permissionList) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, level, dataScope, permissionList, description);
    }

    @Override
    public String toString() {
        return "UpdateRoleDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", dataScope='" + dataScope + '\'' +
                ", permissionList=" + permissionList +
                ", description='" + description + '\'' +
                '}';
    }
}
