package com.etsm.ETSM.Models;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "roles")
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "login")
    private String login;

    @Column(name = "googleUsername")
    private String googleUsername;

    @Column(name = "googleName")
    private String googleName;

    public String getPassword() {
        return password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getGoogleUsername() {
        return googleUsername;
    }

    public void setGoogleUsername(String googleUsername) {
        this.googleUsername = googleUsername;
    }

    public String getGoogleName() {
        return googleName;
    }

    public void setGoogleName(String googleName) {
        this.googleName = googleName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return isEnabled() == user.isEnabled() &&
                Objects.equals(getId(), user.getId()) &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getRoles(), user.getRoles()) &&
                Objects.equals(getLogin(), user.getLogin()) &&
                Objects.equals(getGoogleUsername(), user.getGoogleUsername()) &&
                Objects.equals(getGoogleName(), user.getGoogleName());
    }

    @Override
    public String toString() {
        return "User{" +
                "id = " + id +
                ", username = " + username +
                ", password = " + password +
                ", login = " + login +
                ", roles = " + roles +
                ", active = " + active +
                ", Google Username = " + googleUsername +
                ", Google Name = " + googleName +
                "}";
    }
}