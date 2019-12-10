package com.etsm.ETSM.Models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.mapping.Set;

import javax.persistence.*;
import java.util.ArrayList;
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
    @JoinColumn(name = "Category_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Category category_id;
    @OneToMany(targetEntity = Product.class, mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Product> productList = new ArrayList<>();

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
