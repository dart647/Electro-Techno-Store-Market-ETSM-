package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.*;
import com.etsm.ETSM.Repositories.CategoryIncomeRepository;
import com.etsm.ETSM.Repositories.SalesRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class ERPServiceImplTest {


    @Test//(expected = NullPointerException.class)
    public void addIncomeToCategoryTest(){
    ERPServiceImpl erpService = new ERPServiceImpl();
    CategoryIncomeRepository categoryIncomeRepositoryMock = mock(CategoryIncomeRepository.class);
    erpService.setCategoryIncomeRepository(categoryIncomeRepositoryMock);
    //

        Product product = new Product();
        product.setId(1L);
        product.setPrice(10);
        List<Product> productList = List.of(product);
        List<Product> productLinkedList = new LinkedList<>(productList);
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(4);

        Category category = new Category();
        category.setId(1L);
        SubCategory subCategory = new SubCategory();
        subCategory.setId(1L);
        MinorCategory  minorCategory = new MinorCategory();
        minorCategory.setId(1L);
        minorCategory.setName("name");
        minorCategory.setProductList(productLinkedList);
        List<MinorCategory> minorCategoryList = List.of(minorCategory);
        List<MinorCategory> minorCategoryLinkedList = new LinkedList<>(minorCategoryList);
        subCategory.setMinorCategoryList(minorCategoryLinkedList);

        minorCategory.setSubcategory_id(subCategory);

        subCategory.setCategory_id(category);
        product.setMinorCategory_id(minorCategory);

        CategoryIncome categoryIncome = new CategoryIncome();
        categoryIncome.setId(1L);
        categoryIncome.setCategory_id(category);
        categoryIncome.setTotal(10);
        Optional<CategoryIncome> optionalCategoryIncome = Optional.of(categoryIncome);


        Mockito.when(categoryIncomeRepositoryMock.findById(1L)).thenReturn(optionalCategoryIncome);

        erpService.addIncomeToCategory(cartItem);
    }

    @Test
    public void getIncomeChartDataTest(){
        ERPServiceImpl erpService = new ERPServiceImpl();
        CategoryIncomeRepository categoryIncomeRepositoryMock = mock(CategoryIncomeRepository.class);
        erpService.setCategoryIncomeRepository(categoryIncomeRepositoryMock);
        //

        Category category = new Category();
        category.setId(1L);
        category.setName("name");
        CategoryIncome categoryIncome = new CategoryIncome();
        categoryIncome.setId(1L);
        categoryIncome.setCategory_id(category);
        categoryIncome.setTotal(10);
        List<CategoryIncome> categoryIncomeList = List.of(categoryIncome);
        List<CategoryIncome> categoryIncomeLinkedList = new LinkedList<>(categoryIncomeList);
        Mockito.when(categoryIncomeRepositoryMock.findAll()).thenReturn(categoryIncomeList);
        Assert.assertNotNull(erpService.getIncomeChartData());

    }

    @Test
    public void getIncomeListTest(){
        ERPServiceImpl erpService = new ERPServiceImpl();
        CategoryIncomeRepository categoryIncomeRepositoryMock = mock(CategoryIncomeRepository.class);
        erpService.setCategoryIncomeRepository(categoryIncomeRepositoryMock);
        //
        Category category = new Category();
        category.setId(1L);
        category.setName("name");
        CategoryIncome categoryIncome = new CategoryIncome();
        categoryIncome.setId(1L);
        categoryIncome.setCategory_id(category);
        categoryIncome.setTotal(10);
        List<CategoryIncome> categoryIncomeList = List.of(categoryIncome);
        List<CategoryIncome> categoryIncomeLinkedList = new LinkedList<>(categoryIncomeList);
        Mockito.when(categoryIncomeRepositoryMock.findAll()).thenReturn(categoryIncomeList);
        Assert.assertNotNull(erpService.getIncomeList());

    }

    @Test
    public void getSalesCountTest(){
        ERPServiceImpl erpService = new ERPServiceImpl();
        SalesRepository salesRepositoryMock = mock(SalesRepository.class);
        erpService.setSalesRepository(salesRepositoryMock);
        //

        Sales sales = new Sales();
        sales.setId(1L);
        List<Sales> salesList = List.of(sales);
        Mockito.when(salesRepositoryMock.findAll()).thenReturn(salesList);
        Assert.assertEquals(erpService.getSalesCount(),1);

    }
}