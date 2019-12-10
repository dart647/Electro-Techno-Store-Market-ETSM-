package com.etsm.ETSM.Repositories;

import com.etsm.ETSM.Models.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sales, Long> {
}
