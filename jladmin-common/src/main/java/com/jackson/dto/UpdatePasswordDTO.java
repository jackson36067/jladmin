package com.jackson.dto;

import java.io.Serializable;
import java.util.Objects;

public class UpdatePasswordDTO implements Serializable {
    private String usedPassword;
    private String newPassword;
    private String confirmPassword;

    public UpdatePasswordDTO() {
    }

    public UpdatePasswordDTO(String usedPassword, String newPassword, String confirmPassword) {
        this.usedPassword = usedPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    public String getUsedPassword() {
        return usedPassword;
    }

    public void setUsedPassword(String usedPassword) {
        this.usedPassword = usedPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdatePasswordDTO that = (UpdatePasswordDTO) o;
        return Objects.equals(usedPassword, that.usedPassword) && Objects.equals(newPassword, that.newPassword) && Objects.equals(confirmPassword, that.confirmPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usedPassword, newPassword, confirmPassword);
    }

    @Override
    public String toString() {
        return "UpdatePasswordDTO{" +
                "usedPassword='" + usedPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
