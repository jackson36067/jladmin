package com.jackson.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "sys_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "level")
    private Integer level; // 角色级别
    @Column(name = "description")
    private String description; // 角色描述
    @Column(name = "data_scope")
    private String dataScope; // 数据权限
    @Column(name = "create_by")
    private String createBy;
    @Column(name = "update_by")
    private String updateBy;
    @Column(name = "create_time")
    private LocalDateTime createTime;
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @ManyToMany(mappedBy = "roleSet")
    private Set<User> userSet = new HashSet<>(0);


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "sys_roles_menus",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "menu_id", referencedColumnName = "menu_id")}
    )
    private Set<Menu> menuSet = new HashSet<>(0);

    public Role() {
    }

    public Role(Long id, String name, Integer level, String description, String dataScope, String createBy, String updateBy, LocalDateTime createTime, LocalDateTime updateTime, Set<User> userSet, Set<Menu> menuSet) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.description = description;
        this.dataScope = dataScope;
        this.createBy = createBy;
        this.updateBy = updateBy;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.userSet = userSet;
        this.menuSet = menuSet;
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

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDataScope() {
        return dataScope;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    public Set<Menu> getMenuSet() {
        return menuSet;
    }

    public void setMenuSet(Set<Menu> menuSet) {
        this.menuSet = menuSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) && Objects.equals(name, role.name) && Objects.equals(level, role.level) && Objects.equals(description, role.description) && Objects.equals(dataScope, role.dataScope) && Objects.equals(createBy, role.createBy) && Objects.equals(updateBy, role.updateBy) && Objects.equals(createTime, role.createTime) && Objects.equals(updateTime, role.updateTime) && Objects.equals(userSet, role.userSet) && Objects.equals(menuSet, role.menuSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, level, description, dataScope, createBy, updateBy, createTime, updateTime, userSet, menuSet);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", description='" + description + '\'' +
                ", dataScope='" + dataScope + '\'' +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", userSet=" + userSet +
                ", menuSet=" + menuSet +
                '}';
    }
}
