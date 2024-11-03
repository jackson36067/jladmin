package com.jackson.vo;

import java.io.Serializable;

public class SubMenuVO implements Serializable {
    private Long pid; // 所属一级菜单id
    private String title; // 一级菜单标题
    private String name; // 组件名称
    private String component; // 组件路径
    private String path; // 一级菜单对应的路径
    private String icon; // 一级菜单对应的图标

    public SubMenuVO() {
    }

    public SubMenuVO(Long pid, String title, String name, String component, String path, String icon) {
        this.pid = pid;
        this.title = title;
        this.name = name;
        this.component = component;
        this.path = path;
        this.icon = icon;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
