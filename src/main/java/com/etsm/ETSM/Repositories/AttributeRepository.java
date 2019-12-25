package com.etsm.ETSM.Repositories;

import com.etsm.ETSM.Models.Attribute;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {
    Optional<Attribute> findByName(String name);
    List<Attribute> findAllByNameLike(String s, Pageable pageable);
}
