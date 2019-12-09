package com.etsm.ETSM.Models;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Репозиторий хранящий в себе весь список товаров (также занимается выборкой)
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}
