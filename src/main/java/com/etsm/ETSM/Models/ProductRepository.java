package com.etsm.ETSM.Models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Репозиторий хранящий в себе весь список товаров (также занимается выборкой)
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
