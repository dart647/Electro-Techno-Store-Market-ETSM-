package com.etsm.ETSM.Repositories;

import com.etsm.ETSM.Models.Sales;
import com.etsm.ETSM.Models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SalesRepository extends JpaRepository<Sales, Long> {
    List<Sales> findSalesByUserInfoId(UserInfo userInfo);
    Optional<Sales> findSalesById(Long id);
}
