package com.jackson.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserVO implements Serializable {
    private Long id;
    private String username;
    private String nickName;
    private String gender;
    private String phone;
    private String email;
    private String deptName;
    private Boolean enabled;
    private LocalDateTime createTime;
    private Boolean isAdmin;
    public UserVO() {
    }

    public UserVO(Long id, String username, String nickName,String gender, String phone, String email, String deptName, Boolean enabled, LocalDateTime createTime, Boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.nickName = nickName;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.deptName = deptName;
        this.enabled = enabled;
        this.createTime = createTime;
        this.isAdmin = isAdmin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean admin) {
        isAdmin = admin;
    }
}
