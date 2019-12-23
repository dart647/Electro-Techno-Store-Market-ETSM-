/*
 * Copyright (c) 2019. Smalkov Nikita.
 */

package com.etsm.ETSM.Repositories;

import com.etsm.ETSM.Models.Attribute_Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Attribute_has_productRepository extends JpaRepository<Attribute_Group,Long> {

}
