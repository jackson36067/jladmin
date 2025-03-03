package com.jackson.vo;

import java.io.Serializable;
import java.util.Objects;

public class UserFriendVO implements Serializable {
    private Long id;
    private String username;
    private String avatarPath;

    public UserFriendVO() {
    }

    public UserFriendVO(Long id, String username, String avatarPath) {
        this.id = id;
        this.username = username;
        this.avatarPath = avatarPath;
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

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserFriendVO that = (UserFriendVO) o;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(avatarPath, that.avatarPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, avatarPath);
    }

    @Override
    public String toString() {
        return "UserFriendVO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", avatar='" + avatarPath + '\'' +
                '}';
    }
}
