/*
 * Copyright (c) 2019. Smalkov Nikita.
 */

package com.etsm.ETSM.Repositories;

import com.etsm.ETSM.Models.MinorCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MinorCategoryRepository extends JpaRepository<MinorCategory, Long> {
    Optional<MinorCategory> findByName(String name);
}
