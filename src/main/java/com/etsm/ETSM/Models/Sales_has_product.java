package com.etsm.ETSM.Models;

import javax.persistence.*;

@Entity
@Table(name = "sales_has_product")
public class Sales_has_product {
    @Id
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Sales sales_id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product_id;

    @Column(name = "`count`")
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Sales getSales_id() {
        return sales_id;
    }

    public void setSales_id(Sales sales_id) {
        this.sales_id = sales_id;
    }

    public Product getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Product product_id) {
        this.product_id = product_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
