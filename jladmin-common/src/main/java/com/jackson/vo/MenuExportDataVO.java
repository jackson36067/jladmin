package com.jackson.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class MenuExportDataVO implements Serializable {
    private String title;
    private Integer type;
    private String permission;
    private Boolean iFrame;
    private Boolean hidden;
    private Boolean cache;
    private LocalDateTime createTime;

    public MenuExportDataVO() {
    }

    public MenuExportDataVO(String title, Integer type, String permission, Boolean iFrame, Boolean hidden, Boolean cache, LocalDateTime createTime) {
        this.title = title;
        this.type = type;
        this.permission = permission;
        this.iFrame = iFrame;
        this.hidden = hidden;
        this.cache = cache;
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Boolean getiFrame() {
        return iFrame;
    }

    public void setiFrame(Boolean iFrame) {
        this.iFrame = iFrame;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Boolean getCache() {
        return cache;
    }

    public void setCache(Boolean cache) {
        this.cache = cache;
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
        MenuExportDataVO that = (MenuExportDataVO) o;
        return Objects.equals(title, that.title) && Objects.equals(type, that.type) && Objects.equals(permission, that.permission) && Objects.equals(iFrame, that.iFrame) && Objects.equals(hidden, that.hidden) && Objects.equals(cache, that.cache) && Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, type, permission, iFrame, hidden, cache, createTime);
    }

    @Override
    public String toString() {
        return "MenuExportDataVO{" +
                "title='" + title + '\'' +
                ", type=" + type +
                ", permission='" + permission + '\'' +
                ", iFrame=" + iFrame +
                ", hidden=" + hidden +
                ", cache=" + cache +
                ", createTime=" + createTime +
                '}';
    }
}
