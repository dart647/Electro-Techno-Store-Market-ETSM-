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
    private long total;

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

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Category getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Category category_id) {
        this.category_id = category_id;
    }
}
