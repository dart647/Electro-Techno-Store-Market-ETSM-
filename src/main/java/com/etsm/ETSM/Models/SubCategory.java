package com.etsm.ETSM.Models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.mapping.Set;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subCategory")
public class SubCategory {
    @Id
    @Column(name = "`id`", nullable = false, unique = true)
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    @Column(name = "`name`")
    private String name;
    @Column(name = "`attributes`")
    private Set attributes;
    @ManyToOne()
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category_id;
    @OneToMany(targetEntity = Product.class, mappedBy = "subCategory_id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
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

    public Set getAttributes() {
        return attributes;
    }

    public void setAttributes(Set attributes) {
        this.attributes = attributes;
    }

    public Category getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Category category_id) {
        category_id.getSubCategories().add(this);
        this.category_id = category_id;
    }
}

//enum ProductAttributes{
//    RED,
//    GREEN,
//    BLUE;
//
//    ProductAttributes() {
//    }
//}
