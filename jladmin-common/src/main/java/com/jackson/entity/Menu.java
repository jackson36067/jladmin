package com.jackson.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 前端菜单 -> 对应组件以及组件需要的权限
 */
@Entity
@Table(name = "sys_menu")
public class Menu {
    @Id
    @GeneratedValue
    @Column(name = "menu_id")
    private Long id;
    @Column(name = "pid")
    private Long pid; // 上级菜单id
    @Column(name = "sub_count")
    private Integer subCount; // 下级菜单数量
    @Column(name = "type")
    private Integer type; // 菜单类型 0:一级菜单 1:二级菜单 2:三级菜单
    @Column(name = "title")
    private String title; // 菜单标题
    @Column(name = "name")
    private String name; // 组件名称
    @Column(name = "component")
    private String component; // 组件->对应前端路径
    @Column(name = "menu_sort")
    private Integer menuSort; // 排序
    @Column(name = "icon")
    private String icon; // 图标
    @Column(name = "path")
    private String path; // 链接路径
    @Column(name = "i_frame")
    private Boolean iFrame; // 是否外链
    @Column(name = "cache")
    private Boolean cache; // 是否缓存
    @Column(name = "hidden")
    private Boolean hidden; // 是否隐藏
    @Column(name = "permission")
    private String permission; // 权限
    @Column(name = "create_by")
    private String createBy;
    @Column(name = "update_by")
    private String updateBy;
    @Column(name = "create_time")
    private LocalDateTime createTime;
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @ManyToMany(mappedBy = "menuSet")
    private Set<Role> roleSet = new HashSet<>(0);

    public Menu() {
    }

    public Menu(Long id, Long pid, Integer subCount, Integer type, String title, String name, String component, Integer menuSort, String icon, String path, Boolean iFrame, Boolean cache, Boolean hidden, String permission, String createBy, String updateBy, LocalDateTime createTime, LocalDateTime updateTime, Set<Role> roleSet) {
        this.id = id;
        this.pid = pid;
        this.subCount = subCount;
        this.type = type;
        this.title = title;
        this.name = name;
        this.component = component;
        this.menuSort = menuSort;
        this.icon = icon;
        this.path = path;
        this.iFrame = iFrame;
        this.cache = cache;
        this.hidden = hidden;
        this.permission = permission;
        this.createBy = createBy;
        this.updateBy = updateBy;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.roleSet = roleSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getSubCount() {
        return subCount;
    }

    public void setSubCount(Integer subCount) {
        this.subCount = subCount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Integer getMenuSort() {
        return menuSort;
    }

    public void setMenuSort(Integer menuSort) {
        this.menuSort = menuSort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getiFrame() {
        return iFrame;
    }

    public void setiFrame(Boolean iFrame) {
        this.iFrame = iFrame;
    }

    public Boolean getCache() {
        return cache;
    }

    public void setCache(Boolean cache) {
        this.cache = cache;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
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

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(id, menu.id) && Objects.equals(pid, menu.pid) && Objects.equals(subCount, menu.subCount) && Objects.equals(type, menu.type) && Objects.equals(title, menu.title) && Objects.equals(name, menu.name) && Objects.equals(component, menu.component) && Objects.equals(menuSort, menu.menuSort) && Objects.equals(icon, menu.icon) && Objects.equals(path, menu.path) && Objects.equals(iFrame, menu.iFrame) && Objects.equals(cache, menu.cache) && Objects.equals(hidden, menu.hidden) && Objects.equals(permission, menu.permission) && Objects.equals(createBy, menu.createBy) && Objects.equals(updateBy, menu.updateBy) && Objects.equals(createTime, menu.createTime) && Objects.equals(updateTime, menu.updateTime) && Objects.equals(roleSet, menu.roleSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pid, subCount, type, title, name, component, menuSort, icon, path, iFrame, cache, hidden, permission, createBy, updateBy, createTime, updateTime, roleSet);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", pid=" + pid +
                ", subCount=" + subCount +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", component='" + component + '\'' +
                ", menuSort=" + menuSort +
                ", icon='" + icon + '\'' +
                ", path='" + path + '\'' +
                ", iFrame=" + iFrame +
                ", cache=" + cache +
                ", hidden=" + hidden +
                ", permission='" + permission + '\'' +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", roleSet=" + roleSet +
                '}';
    }
}
