package com.webapp.share4better.model;

import javax.persistence.*;

@Entity
@Table(name = "profile")
public class Profile {
    @Id
    public Integer user_id;

    public String user_name;

    public String user_email;

    public String user_password;

    public boolean donor_status;

    public byte[] getPhotos() {
        return photos;
    }

    public void setPhotos(byte[] photos) {
        this.photos = photos;
    }

    private byte[] photos;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String userPassword) {
        this.user_password = userPassword;
    }

    public boolean isDonor_status() {
        return donor_status;
    }

    public void setDonor_status(boolean donor_status) {
        this.donor_status = donor_status;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer id) {
        this.user_id = id;
    }

}
