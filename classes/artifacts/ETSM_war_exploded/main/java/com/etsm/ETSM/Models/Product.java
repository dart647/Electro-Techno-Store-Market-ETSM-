package com.etsm.ETSM.Models;

import java.util.UUID;

// Описание продукта
public class Product {
    private UUID id; //ID продукта
    private String title; //Название
    private String description; //Описание

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product(UUID id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
