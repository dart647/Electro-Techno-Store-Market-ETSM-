/*
 * Copyright (c) 2020. Smalkov Nikita.
 */

package com.etsm.ETSM.Repositories;

import com.etsm.ETSM.Models.CategoryIncome;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CategoryIncomeRepository extends JpaRepository<CategoryIncome, Long> {
    List<CategoryIncome> findAll();
    Optional<CategoryIncome> findById(long id);
}
