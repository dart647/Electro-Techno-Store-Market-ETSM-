/*
 * Copyright (c) 2020. Smalkov Nikita.
 */

package com.etsm.ETSM.Repositories;

import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Models.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {
    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}
