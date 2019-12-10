package com.etsm.ETSM.Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @Column(name = "`id`", nullable = false, unique = true)
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    @Column(name = "`name`")
    private String name;
    @Column(name = "`desc`")
    private String desc;
    @OneToMany(targetEntity = SubCategory.class, mappedBy = "category_id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SubCategory> subCategories = new ArrayList<>();

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    public List<SubCategory> getProductList() {
        return subCategories;
    }

    public void setProductList(List<SubCategory> productList) {
        this.subCategories = productList;
    }

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}


