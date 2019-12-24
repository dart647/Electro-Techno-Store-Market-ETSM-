/*
 * Copyright (c) 2019. Smalkov Nikita.
 */

package com.etsm.ETSM.Models;
/*
Класс для хранения сущности продукта с указанием количества и общей цены.
Список объектов хранится в Http-сессии и используется для обращения к корзине.
 */
public class CartItem {
    private Product product;
    private int quantity;
    private int totalPrice;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice() {
        this.totalPrice = this.product.getPrice() * this.quantity;
    }

    public CartItem() {

    }

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.setTotalPrice();
    }
}
