package com.webapp.share4better.model;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    private Integer id;
    private String home_address;
    private String home_city;
    private String home_state;
    private int home_zip;
    private String home_country;

    private String work_address;
    private String work_city;
    private String work_state;
    private int work_zip;
    private String work_country;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHome_address() {
        return home_address;
    }

    public void setHome_address(String home_address) {
        this.home_address = home_address;
    }

    public String getHome_city() {
        return home_city;
    }

    public void setHome_city(String home_city) {
        this.home_city = home_city;
    }

    public String getHome_state() {
        return home_state;
    }

    public void setHome_state(String home_state) {
        this.home_state = home_state;
    }

    public int getHome_zip() {
        return home_zip;
    }

    public void setHome_zip(int home_zip) {
        this.home_zip = home_zip;
    }

    public String getHome_country() {
        return home_country;
    }

    public void setHome_country(String home_country) {
        this.home_country = home_country;
    }

    public String getWork_address() {
        return work_address;
    }

    public void setWork_address(String work_address) {
        this.work_address = work_address;
    }

    public String getWork_city() {
        return work_city;
    }

    public void setWork_city(String work_city) {
        this.work_city = work_city;
    }

    public int getWork_zip() {
        return work_zip;
    }

    public void setWork_zip(int work_zip) {
        this.work_zip = work_zip;
    }

    public String getWork_state() {
        return work_state;
    }

    public void setWork_state(String work_state) {
        this.work_state = work_state;
    }

    public String getWork_country() {
        return work_country;
    }

    public void setWork_country(String work_country) {
        this.work_country = work_country;
    }

}
