/*
 * Copyright (c) 2020. Smalkov Nikita.
 */

package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.CartItem;
import com.etsm.ETSM.Models.Category;
import com.etsm.ETSM.Models.CategoryIncome;
import com.etsm.ETSM.Models.Product;
import com.etsm.ETSM.Repositories.CategoryIncomeRepository;
import com.etsm.ETSM.Repositories.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

public interface ERPService {
    void addIncomeToCategory(CartItem cartItem);
    List<List<Map<Object,Object>>> getIncomeChartData();
    Map<String,CategoryIncome> getIncomeList();
    int getSalesCount();
}

@Service
class ERPServiceImpl implements ERPService {
    private CategoryIncomeRepository categoryIncomeRepository;
    private SalesRepository salesRepository;

    @Override
    public void addIncomeToCategory(CartItem cartItem) {
        Product product = cartItem.getProduct();
        Category category = product.getMinorcategoryid().getSubcategory_id().getCategory_id();
        long categoryId = category.getId();
        CategoryIncome categoryIncome = null;
        Optional<CategoryIncome> categoryEntry = categoryIncomeRepository.findById(categoryId);
        if (categoryEntry.isPresent()) {
            categoryIncome = categoryEntry.get();
        } else {
            categoryIncome = new CategoryIncome();
            categoryIncome.setId(categoryId);
            categoryIncome.setCategory_id(category);
        }
        int tempPrice = categoryIncome.getTotal() + cartItem.getTotalPrice();
        int tempQuantity = categoryIncome.getQuantity() + cartItem.getQuantity();
        categoryIncome.setTotal(tempPrice);
        categoryIncome.setQuantity(tempQuantity);
        categoryIncomeRepository.saveAndFlush(categoryIncome);
    }

    @Override
    public List<List<Map<Object, Object>>> getIncomeChartData() {
        Map<Object,Object> map = null;
        List<List<Map<Object,Object>>> list = new ArrayList<List<Map<Object,Object>>>();
        List<Map<Object,Object>> dataPoints1 = new ArrayList<Map<Object,Object>>();
        List<CategoryIncome> categoryIncomeList = categoryIncomeRepository.findAll();
        int overallIncome = getTotalIncome(categoryIncomeList);
        for (CategoryIncome income : categoryIncomeList) {
            String name = income.getCategory_id().getName();
            float totalIncome = income.getTotal() / (overallIncome * 1f);
            map = new HashMap<Object, Object>();
            map.put("label", name);
            map.put("y", totalIncome * 100);
            dataPoints1.add(map);
        }
        list.add(dataPoints1);
        return list;
    }

    public int getTotalIncome(List<CategoryIncome> categoryIncomes) {
        int total = 0;
        for (CategoryIncome income : categoryIncomes) {
            total += income.getTotal();
        }
        return total;
    }

    public Map<String,CategoryIncome> getIncomeList() {
        Map<String,CategoryIncome> incomeMap = new HashMap<>();
        List<CategoryIncome> incomes = categoryIncomeRepository.findAll();
        for (CategoryIncome income : incomes) {
            String temp = income.getCategory_id().getName();
            incomeMap.put(temp,income);
        }
        return incomeMap;
    }

    public int getSalesCount() {
        return salesRepository.findAll().size();
    }

    @Autowired
    public void setCategoryIncomeRepository(CategoryIncomeRepository categoryIncomeRepository) {
        this.categoryIncomeRepository = categoryIncomeRepository;
    }
    @Autowired
    public void setSalesRepository(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }
}
