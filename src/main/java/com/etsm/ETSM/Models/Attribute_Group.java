/*
 * Copyright (c) 2019. Smalkov Nikita.
 */

package com.etsm.ETSM.Models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "attribute_group")
public class Attribute_Group {
    @Id
    @Column(name = "`id`", nullable = false, unique = true)
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(targetEntity = Attribute.class,mappedBy = "attribute_groups", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SELECT)
    private List<Attribute> attribute_id;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    @JoinTable(name = "product_has_attribute_group",
            joinColumns = @JoinColumn(name="attribute_group_id"),
            inverseJoinColumns = @JoinColumn(name="product_id"))
    private List<Product> product_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Attribute> getAttribute_id() {
        return attribute_id;
    }

    public void setAttribute_id(List<Attribute> attribute_id) {
        this.attribute_id = attribute_id;
    }

    public List<Product> getProduct_id() {
        return product_id;
    }

    public void setProduct_id(List<Product> product_id) {
        this.product_id = product_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attribute_Group that = (Attribute_Group) o;
        return id == that.id &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, attribute_id, product_id);
    }
}
