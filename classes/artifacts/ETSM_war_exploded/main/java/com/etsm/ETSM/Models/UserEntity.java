/*
 * Copyright (c) 2019. Nikita Smalkov
 */

package com.etsm.ETSM.Models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "user")
public class UserEntity {
    private long id;
    private String email;
    private String password;
    private String roles;
    private Boolean enabled;
    private String firstname;
    private String lastname;
    private Date birthdate;
    private String adress;
    private String loyaltycode;

    @Id
    @Column(name = "id")
<<<<<<< HEAD:src/main/java/com/etsm/ETSM/Models/UserEntity.java
    public long getId() {
=======
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
>>>>>>> cb8550294f8432889f12d1c023c42ea2852ca12e:classes/artifacts/ETSM_war_exploded/main/java/com/etsm/ETSM/Models/UserEntity.java
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "roles")
    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Basic
    @Column(name = "enabled")
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "birthdate")
    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Basic
    @Column(name = "adress")
    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Basic
    @Column(name = "loyaltycode")
    public String getLoyaltycode() {
        return loyaltycode;
    }

    public void setLoyaltycode(String loyaltycode) {
        this.loyaltycode = loyaltycode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (roles != null ? !roles.equals(that.roles) : that.roles != null) return false;
        if (enabled != null ? !enabled.equals(that.enabled) : that.enabled != null) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (birthdate != null ? !birthdate.equals(that.birthdate) : that.birthdate != null) return false;
        if (adress != null ? !adress.equals(that.adress) : that.adress != null) return false;
        if (loyaltycode != null ? !loyaltycode.equals(that.loyaltycode) : that.loyaltycode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int)id;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        result = 31 * result + (adress != null ? adress.hashCode() : 0);
        result = 31 * result + (loyaltycode != null ? loyaltycode.hashCode() : 0);
        return result;
    }
}
