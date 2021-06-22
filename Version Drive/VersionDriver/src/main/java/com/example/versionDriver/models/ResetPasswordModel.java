package com.example.versionDriver.models;

/**
 * this class use to bind the json data coming as post request for reseting the data on "/reset_password"
 */
public class ResetPasswordModel {

    private String email;
    private String currentPassword;
    private String newPassword;

    public ResetPasswordModel(String email, String currentPassword, String newPassord) {
        this.email = email;
        this.currentPassword = currentPassword;
        this.newPassword = newPassord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
