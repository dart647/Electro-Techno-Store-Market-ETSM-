package com.etsm.ETSM.Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sales_has_product")
public class Sales_has_product {
    @Id
    @Column(name = "`id`", nullable = false, unique = true)
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Column(name = "`count`")
    private int count;

    @Column(name = "`summ`")
    private int summ;

    @ManyToOne()
    @JoinColumn(name = "sales_id", referencedColumnName = "id")
    private Sales sales_id;

    @ManyToOne()
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product_id;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSumm() {
        return summ;
    }

    public void setSumm(int summ) {
        this.summ = summ;
    }

    public Sales getSales_id() {
        return sales_id;
    }

    public void setSales_id(Sales sales_id) {
        sales_id.getSalesHasProducts().add(this);
        this.sales_id = sales_id;
    }

    public Product getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Product product_id) {
        product_id.getSalesHasProducts().add(this);
        this.product_id = product_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sales_has_product that = (Sales_has_product) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, count, summ, sales_id, product_id);
    }
}
