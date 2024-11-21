package com.jackson.dto;

import java.io.Serializable;
import java.util.Objects;

public class UpdateEmailDTO implements Serializable {
    private String newEmail;
    private String verifyCode;
    private String password;

    public UpdateEmailDTO() {
    }

    public UpdateEmailDTO(String newEmail, String verifyCode, String password) {
        this.newEmail = newEmail;
        this.verifyCode = verifyCode;
        this.password = password;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateEmailDTO that = (UpdateEmailDTO) o;
        return Objects.equals(newEmail, that.newEmail) && Objects.equals(verifyCode, that.verifyCode) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newEmail, verifyCode, password);
    }

    @Override
    public String toString() {
        return "UpdateEmailDTO{" +
                "newEmail='" + newEmail + '\'' +
                ", verifyCode='" + verifyCode + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
