/*
 * Copyright (c) 2019. Nikita Smalkov
 */

package com.etsm.ETSM.Repositories;

import com.etsm.ETSM.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String email);
    User findByLogin(String name);
    User findByGoogleName(String googleName);
    User findByGoogleUsername(String googleUsername);
}