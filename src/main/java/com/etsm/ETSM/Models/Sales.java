package com.etsm.ETSM.Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "sales")
public class Sales {
    @Id
    @Column(name = "`id`", nullable = false, unique = true)
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Column(name = "`sum`")
    private int sum;

    @ManyToOne()
    @JoinColumn(name = "userInfo_id", referencedColumnName = "id")
    private UserInfo userInfoId;

    @OneToMany(targetEntity = Sales_has_product.class, mappedBy = "sales_id", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Sales_has_product> salesHasProducts;

    public List<Sales_has_product> getSalesHasProducts() {
        return salesHasProducts;
    }

    public void setSalesHasProducts(List<Sales_has_product> salesHasProducts) {
        this.salesHasProducts = salesHasProducts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public UserInfo getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(UserInfo userInfo_id) {
        userInfo_id.getSales().add(this);
        this.userInfoId = userInfo_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sales sales = (Sales) o;
        return id == sales.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sum, userInfoId, salesHasProducts);
    }
}
