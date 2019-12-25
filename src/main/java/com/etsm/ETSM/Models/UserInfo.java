package com.etsm.ETSM.Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Table(name = "userInfo")
public class UserInfo {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    @Column(name = "`fio`")
    private String fio;
    @Column(name = "`birthDate`")
    private String birthDate;
    @Column(name = "`address`")
    private String address;
    @Column(name = "`wallet`")
    private int wallet;
    @Column(name = "`phonenumber`")
    private String phoneNumber;
    @OneToOne(mappedBy = "userInfo", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private User user_id;
    @OneToMany(targetEntity = Sales.class, mappedBy = "userInfoId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private transient List<Sales> sales;
    @OneToOne(targetEntity = Loyalty.class, mappedBy = "userInfo_id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Loyalty loyaltyCode_id;

    public Loyalty getLoyaltyCode_id() {
        return loyaltyCode_id;
    }

    public void setLoyaltyCode_id(Loyalty loyaltyCode_id) {
        this.loyaltyCode_id = loyaltyCode_id;
    }

    public List<Sales> getSales() {
        return sales;
    }

    public void setSales(List<Sales> sales) {
        this.sales = sales;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }
}
