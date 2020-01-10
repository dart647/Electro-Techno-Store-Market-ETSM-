/*
 * Copyright (c) 2020. Smalkov Nikita.
 */

package com.etsm.ETSM.Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "categoryincome")
public class CategoryIncome {
    @Id
    @Column(name = "`id`", nullable = false, unique = true)
    private long id;

    @Column(name = "total")
    private int total;

    @Column(name = "quantity")
    private int quantity;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Category category_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Category getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Category category_id) {
        this.category_id = category_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
