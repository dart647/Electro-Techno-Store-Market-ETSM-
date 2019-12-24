package com.etsm.ETSM.Repositories;

import com.etsm.ETSM.Models.Attribute_Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttributeGroupRepository extends JpaRepository<Attribute_Group, Long> {
    Optional<Attribute_Group> findByName(String name);
}
