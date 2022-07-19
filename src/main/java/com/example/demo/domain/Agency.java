package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "agencies")
public class Agency {

    //region fields
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String phone;

    private String email;
    //endregion

    //region constructors
    public Agency(long id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Agency() {
    }

    public Agency(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
    //endregion

    //region getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
    //endregion
}
