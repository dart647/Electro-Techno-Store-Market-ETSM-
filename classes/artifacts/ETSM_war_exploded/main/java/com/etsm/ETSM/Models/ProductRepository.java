package com.etsm.ETSM.Models;

import org.springframework.stereotype.Repository;

import java.util.*;

// Репозиторий хранящий в себе весь список товаров (также занимается выборкой)
@Repository
public class ProductRepository {
    private List<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();
        for (int i = 0; i < new Random().nextInt(20); i++) {
            products.add(new Product(UUID.randomUUID(), "Product №" + i, "Desc"));
        }
    }

    public void AddNewProduct(Product product){
        this.products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Product> findAll(){
        return this.products;
    }

    public Optional<Product> findOneById(UUID id) {
        return this.products.stream()
                .filter(product -> Objects.equals(id, product.getId()))
                .findFirst();
    }
}
