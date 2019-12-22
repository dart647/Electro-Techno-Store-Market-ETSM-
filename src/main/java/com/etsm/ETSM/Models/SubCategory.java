package com.etsm.ETSM.Models;

import org.hibernate.annotations.GenericGenerator;

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

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category_id;

    @OneToMany(targetEntity = MinorCategory.class, mappedBy = "subcategory_id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MinorCategory> minorCategoryList;

    public List<MinorCategory> getMinorCategoryList() {
        return minorCategoryList;
    }

    public void setMinorCategoryList(List<MinorCategory> minorCategoryList) {
        this.minorCategoryList = minorCategoryList;
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

    public Category getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Category category_id) {
        category_id.getSubCategories().add(this);
        this.category_id = category_id;
    }
}

