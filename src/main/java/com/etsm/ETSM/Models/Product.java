package com.etsm.ETSM.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    @Column(name = "`subCategory_id`", nullable = false)
    private int subCategory_id; //Подкатегория
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

    public int getSubCategory_id() {
        return subCategory_id;
    }

    public void setSubCategory_id(int subCategory_id) {
        this.subCategory_id = subCategory_id;
    }
}
