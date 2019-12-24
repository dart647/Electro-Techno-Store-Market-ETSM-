package com.etsm.ETSM.Repositories;

import com.etsm.ETSM.Models.ProductAttrValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductAttrValueRepository extends JpaRepository<ProductAttrValue, Long> {
    Optional<ProductAttrValue> findByValue(String value);
}
