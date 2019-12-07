/*
 * Copyright (c) 2019. Nikita Smalkov
 * Entity Bean with JPA annotations
 */

package com.etsm.ETSM.Models;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //User ID

    private String email; //Username

    private String password; //Password

    private String roles; //Роли данные пользователю

    public void setID(long id) { //ID setter
        this.id = id;
    }

    public long getID() { //ID getter
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.email = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "id = "+id+", name = "+email+", password= "+password;
    }
}
