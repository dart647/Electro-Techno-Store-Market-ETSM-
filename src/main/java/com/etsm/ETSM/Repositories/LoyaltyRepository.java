/*
 * Copyright (c) 2019. Smalkov Nikita.
 */

package com.etsm.ETSM.Repositories;

import com.etsm.ETSM.Models.Loyalty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoyaltyRepository extends JpaRepository<Loyalty, Long> {
    Optional<Loyalty> findById(long id);
}
