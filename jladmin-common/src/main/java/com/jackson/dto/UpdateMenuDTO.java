package com.jackson.dto;

import java.util.Objects;

public class UpdateMenuDTO {
    private Long id;
    private Long pid;
    private Integer type;
    private String title;
    private String name;
    private String component;
    private Integer menuSort;
    private String icon;
    private String path;
    private Boolean iFrame;
    private Boolean cache;
    private Boolean hidden;
    private String permission;

    public UpdateMenuDTO() {
    }

    public UpdateMenuDTO(Long id, Long pid, Integer type, String title, String name, String component, Integer menuSort, String icon, String path, Boolean iFrame, Boolean cache, Boolean hidden, String permission) {
        this.id = id;
        this.pid = pid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateMenuDTO that = (UpdateMenuDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(pid, that.pid) && Objects.equals(type, that.type) && Objects.equals(title, that.title) && Objects.equals(name, that.name) && Objects.equals(component, that.component) && Objects.equals(menuSort, that.menuSort) && Objects.equals(icon, that.icon) && Objects.equals(path, that.path) && Objects.equals(iFrame, that.iFrame) && Objects.equals(cache, that.cache) && Objects.equals(hidden, that.hidden) && Objects.equals(permission, that.permission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pid, type, title, name, component, menuSort, icon, path, iFrame, cache, hidden, permission);
    }

    @Override
    public String toString() {
        return "UpdateMenuDTO{" +
                "id=" + id +
                ", pid=" + pid +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", component='" + component + '\'' +
                ", menuSort='" + menuSort + '\'' +
                ", icon='" + icon + '\'' +
                ", path='" + path + '\'' +
                ", iFrame=" + iFrame +
                ", cache=" + cache +
                ", hidden=" + hidden +
                ", permission='" + permission + '\'' +
                '}';
    }
}
