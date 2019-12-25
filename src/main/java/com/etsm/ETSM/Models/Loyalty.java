/*
 * Copyright (c) 2019. Smalkov Nikita.
 */

package com.etsm.ETSM.Models;
// Программа лояльности

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "loyalty")
public class Loyalty {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Column(name = "balance")
    private int balance;

    @OneToOne()
    @JoinColumn(name = "userinfo_id", referencedColumnName = "id")
    private UserInfo userInfo_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setUserInfo_id(UserInfo userInfo_id) {
        userInfo_id.setLoyaltyCode_id(this);
        this.userInfo_id = userInfo_id;
    }
}
