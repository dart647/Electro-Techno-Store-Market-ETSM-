package com.etsm.ETSM.Models;

import javax.persistence.*;
import java.util.List;

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
    @OneToMany(targetEntity = Sales_has_product.class, mappedBy = "product_id", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private transient List<Sales_has_product> salesHasProducts;

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
        subCategory_id.getProductList().add(this);
        this.subCategory_id = subCategory_id;
    }
}
