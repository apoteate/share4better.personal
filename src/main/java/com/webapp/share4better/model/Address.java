package com.webapp.share4better.model;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    private Integer id;
    private String home_address;
    private String work_address;

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

    public String getWork_address() {
        return work_address;
    }

    public void setWork_address(String work_address) {
        this.work_address = work_address;
    }
}
