package com.jackson.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class UpdateRoleMenuDTO implements Serializable {
    private Long id;
    private List<Long> menuIdList;

    public UpdateRoleMenuDTO() {
    }

    public UpdateRoleMenuDTO(Long id, List<Long> menuIdList) {
        this.id = id;
        this.menuIdList = menuIdList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        UpdateRoleMenuDTO that = (UpdateRoleMenuDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(menuIdList, that.menuIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, menuIdList);
    }

    @Override
    public String toString() {
        return "UpdateRoleMenuDTO{" +
                "id=" + id +
                ", menuIdList=" + menuIdList +
                '}';
    }
}
