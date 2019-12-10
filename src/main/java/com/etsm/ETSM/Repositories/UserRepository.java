/*
 * Copyright (c) 2019. Nikita Smalkov
 */

package com.etsm.ETSM.Repositories;

import com.etsm.ETSM.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

}