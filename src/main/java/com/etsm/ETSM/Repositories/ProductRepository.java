package com.etsm.ETSM.Repositories;

import com.etsm.ETSM.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// Репозиторий хранящий в себе весь список товаров (также занимается выборкой)

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    List<Product> findByNameLike(String name);
}
