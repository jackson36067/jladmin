package com.jackson.vo;

import java.io.Serializable;
import java.util.List;

public class UserLoginVO implements Serializable {
    private Long id;
    private String nickName;
    private String avatarPath; // 头像路径
    private String token;
    // 返回的menu菜单
    private List<MenuVO> menuVOList;

    public UserLoginVO() {
    }

    public UserLoginVO(Long id, String nickName, String avatarPath, String token, List<MenuVO> menuVOList) {
        this.id = id;
        this.nickName = nickName;
        this.avatarPath = avatarPath;
        this.token = token;
        this.menuVOList = menuVOList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<MenuVO> getMenuVOList() {
        return menuVOList;
    }

    public void setMenuVOList(List<MenuVO> menuVOList) {
        this.menuVOList = menuVOList;
    }
}
