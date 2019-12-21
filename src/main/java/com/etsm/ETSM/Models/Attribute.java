package com.etsm.ETSM.Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "attribute")
public class Attribute {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", nullable = false, unique = true)
    private long id;
    @Column(name = "name")
    private String name;

    @OneToMany(targetEntity = Attribute_has_product.class,mappedBy = "attribute_id", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Attribute_has_product> attributeHasProducts;

    public List<Attribute_has_product> getAttributeHasProducts() {
        return attributeHasProducts;
    }

    public void setAttributeHasProducts(List<Attribute_has_product> attributeHasProducts) {
        this.attributeHasProducts = attributeHasProducts;
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
}
