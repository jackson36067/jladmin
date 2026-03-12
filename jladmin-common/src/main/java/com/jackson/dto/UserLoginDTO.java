package com.jackson.dto;


import java.io.Serializable;
import java.util.Objects;


public class UserLoginDTO implements Serializable {
    private String username;
    private String password;
    private Boolean isAdmin;
    private String code;

    public UserLoginDTO() {
    }

    public UserLoginDTO(String username, String password, String code, Boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.code = code;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "UserLoginDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                ", code='" + code + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLoginDTO that = (UserLoginDTO) o;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(isAdmin, that.isAdmin) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, isAdmin, code);
    }
}