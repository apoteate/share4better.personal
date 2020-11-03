package com.webapp.share4better.model;

public class User {
    private boolean isValidUser;
    private String userEmail;
    private Integer userId;


    public boolean isValidUser() {
        return isValidUser;
    }

    public void setValidUser(boolean validUser) {
        isValidUser = validUser;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
