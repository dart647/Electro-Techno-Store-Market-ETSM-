package com.etsm.ETSM.Models;

import javax.persistence.*;

// Описание продукта
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "`id`", nullable = false)
    private long id; //ID продукта
    @Column(name = "`name`", nullable = false)
    private String name; //Название
    @Column(name = "`price`", nullable = false)
    private int price; //Цена
    @ManyToOne()
    @JoinColumn(name = "subCategory_id", referencedColumnName = "id")
    private SubCategory subCategory_id; //Подкатегория
    @Column(name = "`desc`")
    private String description; //Описание

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public SubCategory getSubCategory_id() {
        return subCategory_id;
    }

    public void setSubCategory_id(SubCategory subCategory_id) {
        this.subCategory_id = subCategory_id;
    }
}
