package com.realdolmen.hbo5.wasdapp.wasdappcore.domain;


import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name = "gebruiker")
public class UserWassdapp implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "achternaam")
    private String achterNaam;
    @Column(name = "role")
    private String role;


    public Long getId() {
        return id;
    }

    public UserWassdapp setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserWassdapp setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserWassdapp setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserWassdapp setName(String name) {
        this.name = name;
        return this;
    }

    public String getAchterNaam() {
        return achterNaam;
    }

    public UserWassdapp setAchterNaam(String achterNaam) {
        this.achterNaam = achterNaam;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserWassdapp setRole(String role) {
        this.role = role;
        return this;
    }


}
