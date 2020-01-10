/*
 * Copyright (c) 2020. Smalkov Nikita.
 */

package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.CartItem;
import com.etsm.ETSM.Models.CategoryIncome;
import com.etsm.ETSM.Models.Product;
import com.etsm.ETSM.Repositories.CategoryIncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public interface ERPService {
    void addIncomeToCategory(CartItem cartItem);
}

class ERPServiceImpl implements ERPService {

    private CategoryIncomeRepository categoryIncomeRepository;

    @Override
    public void addIncomeToCategory(CartItem cartItem) {

    }

    @Autowired
    public void setCategoryIncomeRepository(CategoryIncomeRepository categoryIncomeRepository) {
        this.categoryIncomeRepository = categoryIncomeRepository;
    }
}
