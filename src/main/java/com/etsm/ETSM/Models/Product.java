package com.etsm.ETSM.Models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

// Описание продукта
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "`id`", nullable = false)
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id; //ID продукта

    @Column(name = "`name`", nullable = false)
    private String name; //Название

    @Column(name = "`price`", nullable = false)
    private int price; //Цена

    @Column(name = "`desc`")
    private String description; //Описание

    @Column(name = "`img`")
    private String img;

    @Column(name = "`count`")
    private int count;

    @ManyToOne()
    @JoinColumn(name = "minorcategory_id", referencedColumnName = "id")
    private MinorCategory minorcategoryid; //Подкатегория

    @OneToMany(targetEntity = Sales_has_product.class, mappedBy = "product_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SELECT)
    private transient List<Sales_has_product> salesHasProducts;

    @OneToMany(targetEntity = ProductAttrValue.class,mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SELECT)
    private List<ProductAttrValue> productAttrValue;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    @JoinTable(name = "product_has_attribute_group",
    joinColumns = @JoinColumn(name="product_id"),
    inverseJoinColumns = @JoinColumn(name="attribute_group_id"))
    private List<Attribute_Group> attribute_groups;

    public MinorCategory getMinorcategoryid() {
        return minorcategoryid;
    }

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setMinorCategory_id(MinorCategory minorCategory_id) {
        minorCategory_id.getProductList().add(this);
        this.minorcategoryid = minorCategory_id;
    }

    public List<ProductAttrValue> getProductAttrValue() {
        return productAttrValue;
    }

    public void setProductAttrValue(List<ProductAttrValue> productAttrValue) {
        this.productAttrValue = productAttrValue;
    }

    public List<Attribute_Group> getAttribute_groups() {
        return attribute_groups;
    }

    public void setAttribute_groups(List<Attribute_Group> attribute_groups) {
        this.attribute_groups = attribute_groups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, minorcategoryid, description, img, salesHasProducts, productAttrValue, attribute_groups);
    }
}
