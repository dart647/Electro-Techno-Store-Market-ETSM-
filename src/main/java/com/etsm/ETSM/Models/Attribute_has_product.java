/*
 * Copyright (c) 2019. Smalkov Nikita.
 */

package com.etsm.ETSM.Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "attribute_has_product")
public class Attribute_has_product {
    @Id
    @Column(name = "`id`", nullable = false, unique = true)
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @ManyToOne()
    @JoinColumn(name = "attribute_id", referencedColumnName = "id")
    private Attribute attribute_id;

    @ManyToOne()
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product_id;

    public Attribute getAttribute_id() {
        return attribute_id;
    }

    public void setAttribute_id(Attribute attribute_id) {
        attribute_id.getAttributeHasProducts().add(this);
        this.attribute_id = attribute_id;
    }

    public Product getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Product product_id) {
        product_id.getAttributeHasProducts().add(this);
        this.product_id = product_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
