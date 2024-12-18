package com.jackson.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class UserDTO implements Serializable {
    private Long id;
    private String username;
    private String nickName;
    private String phone;
    private String email;
    private Long deptId;
    private String gender;
    private Boolean enabled;
    private List<Long> roles;
    private List<Long> jobs;

    public UserDTO() {
    }

    public UserDTO(Long id, String username, String nickName, String phone, String email, Long deptId, String gender, Boolean enabled, List<Long> roles, List<Long> jobs) {
        this.id = id;
        this.username = username;
        this.nickName = nickName;
        this.phone = phone;
        this.email = email;
        this.deptId = deptId;
        this.gender = gender;
        this.enabled = enabled;
        this.roles = roles;
        this.jobs = jobs;
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

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Long> getRoles() {
        return roles;
    }

    public void setRoles(List<Long> roles) {
        this.roles = roles;
    }

    public List<Long> getJobs() {
        return jobs;
    }

    public void setJobs(List<Long> jobs) {
        this.jobs = jobs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) && Objects.equals(username, userDTO.username) && Objects.equals(nickName, userDTO.nickName) && Objects.equals(phone, userDTO.phone) && Objects.equals(email, userDTO.email) && Objects.equals(deptId, userDTO.deptId) && Objects.equals(gender, userDTO.gender) && Objects.equals(enabled, userDTO.enabled) && Objects.equals(roles, userDTO.roles) && Objects.equals(jobs, userDTO.jobs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, nickName, phone, email, deptId, gender, enabled, roles, jobs);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nickName='" + nickName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", deptId=" + deptId +
                ", gender='" + gender + '\'' +
                ", enabled=" + enabled +
                ", roles=" + roles +
                ", jobs=" + jobs +
                '}';
    }
}
