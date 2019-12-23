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

    @ManyToOne()
    @JoinColumn(name = "attribute_gr_id", referencedColumnName = "id")
    private Attribute_Group attribute_groups;

    @OneToMany(targetEntity = ProductAttrValue.class,mappedBy = "attribute", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ProductAttrValue> productAttrValue;

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

    public Attribute_Group getAttribute_groups() {
        return attribute_groups;
    }

    public void setAttribute_groups(Attribute_Group attribute_groups) {
        attribute_groups.getAttribute_id().add(this);
        this.attribute_groups = attribute_groups;
    }

    public List<ProductAttrValue> getProductAttrValue() {
        return productAttrValue;
    }

    public void setProductAttrValue(List<ProductAttrValue> productAttrValue) {
        this.productAttrValue = productAttrValue;
    }

}
