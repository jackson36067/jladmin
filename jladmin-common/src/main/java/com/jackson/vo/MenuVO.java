package com.jackson.vo;

import java.io.Serializable;
import java.util.List;

public class MenuVO implements Serializable {
    private Long id; // 菜单id
    private String title; // 一级菜单标题
    private String name; // 组件名称
    private String component; // 组件路径
    private String icon; // 一级菜单对应的图标
    private String path; // 一级菜单对应的路径
    private List<SubMenuVO> subMenuVOList; // 一级菜单中的二级菜单

    public MenuVO() {
    }

    public MenuVO(Long id, String title, String name, String component, String icon,String path, List<SubMenuVO> subMenuVOList) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.component = component;
        this.icon = icon;
        this.path = path;
        this.subMenuVOList = subMenuVOList;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public List<SubMenuVO> getSubMenuVOList() {
        return subMenuVOList;
    }

    public void setSubMenuVOList(List<SubMenuVO> subMenuVOList) {
        this.subMenuVOList = subMenuVOList;
    }
}