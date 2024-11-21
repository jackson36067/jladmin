package com.jackson.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class UserLoginVO implements Serializable {
    private Long id;
    private String nickName;
    private String avatarPath; // 头像路径
    private String token;
    // 返回的menu菜单
    private List<MenuVO> menuVOList;
    // 部门名称
    private String deptName;
    private String username;
    private String phone;
    private String email;
    private String gender;

    public UserLoginVO() {
    }

    public UserLoginVO(Long id, String nickName, String avatarPath, String token, List<MenuVO> menuVOList,String deptName,String username,String phone,String email,String gender) {
        this.id = id;
        this.nickName = nickName;
        this.avatarPath = avatarPath;
        this.token = token;
        this.menuVOList = menuVOList;
        this.deptName = deptName;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLoginVO that = (UserLoginVO) o;
        return Objects.equals(id, that.id) && Objects.equals(nickName, that.nickName) && Objects.equals(avatarPath, that.avatarPath) && Objects.equals(token, that.token) && Objects.equals(menuVOList, that.menuVOList) && Objects.equals(deptName, that.deptName) && Objects.equals(username, that.username) && Objects.equals(phone, that.phone) && Objects.equals(email, that.email) && Objects.equals(gender, that.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickName, avatarPath, token, menuVOList, deptName, username, phone, email, gender);
    }

    @Override
    public String toString() {
        return "UserLoginVO{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", avatarPath='" + avatarPath + '\'' +
                ", token='" + token + '\'' +
                ", menuVOList=" + menuVOList +
                ", deptName='" + deptName + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
