package com.webapp.share4better.model;

import javax.persistence.*;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    public Integer userId;

    @Column(name="user_name")
    public String userName;

    @Column(name="user_email")
    public String userEmail;

    @Column(name="user_password")
    public String userPassword;

    @Column(name="donor_status")
    public boolean donorStatus;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public boolean isDonorStatus() {
        return donorStatus;
    }

    public void setDonorStatus(boolean donorStatus) {
        this.donorStatus = donorStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer id) {
        this.userId = id;
    }


}
