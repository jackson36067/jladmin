package com.jackson.vo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class UserExportDataVO {
    private String username;
    private List<String> roles;
    private String deptName;
    private List<String> jobs;
    private String email;
    private Boolean enabled;
    private String phone;
    private LocalDateTime PwdResetTime;
    private LocalDateTime createTime;

    public UserExportDataVO() {
    }

    public UserExportDataVO(String username, List<String> roles, String deptName, List<String> jobs, String email, Boolean enabled, String phone, LocalDateTime PwdResetTime, LocalDateTime createTime) {
        this.username = username;
        this.roles = roles;
        this.deptName = deptName;
        this.jobs = jobs;
        this.email = email;
        this.enabled = enabled;
        this.phone = phone;
        this.PwdResetTime = PwdResetTime;
        this.createTime = createTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<String> getJobs() {
        return jobs;
    }

    public void setJobs(List<String> jobs) {
        this.jobs = jobs;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getPwdResetTime() {
        return PwdResetTime;
    }

    public void setPwdResetTime(LocalDateTime pwdResetTime) {
        this.PwdResetTime = pwdResetTime;
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
        UserExportDataVO that = (UserExportDataVO) o;
        return Objects.equals(username, that.username) && Objects.equals(roles, that.roles) && Objects.equals(deptName, that.deptName) && Objects.equals(jobs, that.jobs) && Objects.equals(email, that.email) && Objects.equals(enabled, that.enabled) && Objects.equals(phone, that.phone) && Objects.equals(PwdResetTime, that.PwdResetTime) && Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, roles, deptName, jobs, email, enabled, phone, PwdResetTime, createTime);
    }

    @Override
    public String toString() {
        return "UserExportDataVO{" +
                "username='" + username + '\'' +
                ", roles=" + roles +
                ", deptName='" + deptName + '\'' +
                ", jobs=" + jobs +
                ", email='" + email + '\'' +
                ", isEnabled='" + enabled + '\'' +
                ", phone='" + phone + '\'' +
                ", updatePasswordTime=" + PwdResetTime +
                ", createTime=" + createTime +
                '}';
    }
}
