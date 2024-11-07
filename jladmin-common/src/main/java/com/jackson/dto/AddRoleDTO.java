package com.jackson.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class AddRoleDTO implements Serializable {
    private String name;
    private Integer level;
    private String dataScope; // 角色数据描述
    private List<Long> permissionList; // 新增的角色的菜单id列表
    private String description;

    public AddRoleDTO() {
    }

    public AddRoleDTO(String name, Integer lever, String dataScope, List<Long> permissionList, String description) {
        this.name = name;
        this.level = lever;
        this.dataScope = dataScope;
        this.permissionList = permissionList;
        this.description = description;
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
        AddRoleDTO that = (AddRoleDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(level, that.level) && Objects.equals(dataScope, that.dataScope) && Objects.equals(permissionList, that.permissionList) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level, dataScope, permissionList, description);
    }

    @Override
    public String toString() {
        return "AddRoleDTO{" +
                "name='" + name + '\'' +
                ", lever=" + level +
                ", dataScope='" + dataScope + '\'' +
                ", permissionList=" + permissionList +
                ", description='" + description + '\'' +
                '}';
    }
}
