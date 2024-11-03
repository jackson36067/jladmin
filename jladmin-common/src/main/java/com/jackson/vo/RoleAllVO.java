package com.jackson.vo;

import java.util.Objects;

public class RoleAllVO {
    private Long id;
    private String name;

    public RoleAllVO() {
    }

    public RoleAllVO(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleAllVO roleAllVO = (RoleAllVO) o;
        return Objects.equals(id, roleAllVO.id) && Objects.equals(name, roleAllVO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "RoleAllVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
