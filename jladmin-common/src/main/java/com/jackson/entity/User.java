package com.jackson.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "sys_user")
public class User {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "nick_name")
    private String nickName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "avatar_name")
    private String avatarName;
    @Column(name = "avatar_path")
    private String avatarPath;
    @Column(name = "password")
    private String password;
    @Column(name = "is_admin")
    private Boolean isAdmin;
    @Column(name = "enabled")
    private Boolean enabled;
    @Column(name = "create_by")
    private String createBy;
    @Column(name = "update_by")
    private String updateBy;
    @Column(name = "pwd_reset_time")
    private LocalDateTime PwdResetTime;
    @Column(name = "create_time")
    private LocalDateTime createTime;
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @ManyToOne()
    @JoinColumn(name = "dept_id", referencedColumnName = "dept_id")
    private Dept dept;

    @ManyToMany()
    @JoinTable(
            name = "sys_users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")}
    )
    private Set<Role> roleSet = new HashSet<>(0);

    @ManyToMany()
    @JoinTable(
            name = "sys_users_jobs",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "job_id", referencedColumnName = "job_id")}
    )
    private Set<Job> jobSet = new HashSet<>(0);

    public User() {
    }

    public User(Long id, String username, String nickName, String gender, String phone, String email, String avatarName, String avatarPath, String password, Boolean isAdmin, Boolean enabled, String createBy, String updateBy, LocalDateTime pwdResetTime, LocalDateTime createTime, LocalDateTime updateTime, Dept dept, Set<Role> roleSet, Set<Job> jobSet) {
        this.id = id;
        this.username = username;
        this.nickName = nickName;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.avatarName = avatarName;
        this.avatarPath = avatarPath;
        this.password = password;
        this.isAdmin = isAdmin;
        this.enabled = enabled;
        this.createBy = createBy;
        this.updateBy = updateBy;
        PwdResetTime = pwdResetTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.dept = dept;
        this.roleSet = roleSet;
        this.jobSet = jobSet;
    }


    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Set<Job> getJobSet() {
        return jobSet;
    }

    public void setJobSet(Set<Job> jobSet) {
        this.jobSet = jobSet;
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

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getPwdResetTime() {
        return PwdResetTime;
    }

    public void setPwdResetTime(LocalDateTime pwdResetTime) {
        PwdResetTime = pwdResetTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(nickName, user.nickName) && Objects.equals(gender, user.gender) && Objects.equals(phone, user.phone) && Objects.equals(email, user.email) && Objects.equals(avatarName, user.avatarName) && Objects.equals(avatarPath, user.avatarPath) && Objects.equals(password, user.password) && Objects.equals(isAdmin, user.isAdmin) && Objects.equals(enabled, user.enabled) && Objects.equals(createBy, user.createBy) && Objects.equals(updateBy, user.updateBy) && Objects.equals(PwdResetTime, user.PwdResetTime) && Objects.equals(createTime, user.createTime) && Objects.equals(updateTime, user.updateTime) && Objects.equals(dept, user.dept) && Objects.equals(roleSet, user.roleSet) && Objects.equals(jobSet, user.jobSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, nickName, gender, phone, email, avatarName, avatarPath, password, isAdmin, enabled, createBy, updateBy, PwdResetTime, createTime, updateTime, dept, roleSet, jobSet);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nickName='" + nickName + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", avatarName='" + avatarName + '\'' +
                ", avatarPath='" + avatarPath + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                ", enabled=" + enabled +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", PwdResetTime=" + PwdResetTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", dept=" + dept +
                ", roleSet=" + roleSet +
                ", jobSet=" + jobSet +
                '}';
    }
}
