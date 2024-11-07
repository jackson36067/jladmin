package com.jackson.vo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class MenuListVO {
    private Long id;
    private String title;
    private String icon;
    private Integer menuSort;
    private String permission;
    private String component;
    private Boolean iFrame;
    private Boolean cache;
    private Boolean hidden;
    private LocalDateTime createTime;
    private List<MenuListVO> menuListVOList; // 子菜单集合

    public MenuListVO() {
    }

    public MenuListVO(Long id, String title, String icon, Integer menuSort, String permission, String component, Boolean iFrame, Boolean cache, Boolean hidden, LocalDateTime createTime, List<MenuListVO> menuListVOList) {
        this.id = id;
        this.title = title;
        this.icon = icon;
        this.menuSort = menuSort;
        this.permission = permission;
        this.component = component;
        this.iFrame = iFrame;
        this.cache = cache;
        this.hidden = hidden;
        this.createTime = createTime;
        this.menuListVOList = menuListVOList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getMenuSort() {
        return menuSort;
    }

    public void setMenuSort(Integer menuSort) {
        this.menuSort = menuSort;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public List<MenuListVO> getMenuListVOList() {
        return menuListVOList;
    }

    public void setMenuListVOList(List<MenuListVO> menuListVOList) {
        this.menuListVOList = menuListVOList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuListVO that = (MenuListVO) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(icon, that.icon) && Objects.equals(menuSort, that.menuSort) && Objects.equals(permission, that.permission) && Objects.equals(component, that.component) && Objects.equals(iFrame, that.iFrame) && Objects.equals(cache, that.cache) && Objects.equals(hidden, that.hidden) && Objects.equals(createTime, that.createTime) && Objects.equals(menuListVOList, that.menuListVOList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, icon, menuSort, permission, component, iFrame, cache, hidden, createTime, menuListVOList);
    }

    @Override
    public String toString() {
        return "MenuListVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", menuSort=" + menuSort +
                ", permission='" + permission + '\'' +
                ", component='" + component + '\'' +
                ", iFrame=" + iFrame +
                ", cache=" + cache +
                ", hidden=" + hidden +
                ", createTime=" + createTime +
                ", menuListVOList=" + menuListVOList +
                '}';
    }
}
