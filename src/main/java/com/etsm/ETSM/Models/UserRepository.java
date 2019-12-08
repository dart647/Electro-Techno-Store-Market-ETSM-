/*
 * Copyright (c) 2019. Nikita Smalkov
 */

package com.etsm.ETSM.Models;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long> {

}