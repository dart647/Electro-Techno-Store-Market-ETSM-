/*
 * Copyright (c) 2019. Nikita Smalkov
 */

package com.etsm.ETSM.Repositories;

import com.etsm.ETSM.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String email);
    User findByLogin(String name);
    User findByGoogleUsername(String googleUsername);
    User findByGoogleName(String googleName);
}