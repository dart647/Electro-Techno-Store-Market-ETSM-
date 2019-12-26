package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.*;
import com.etsm.ETSM.Services.HeaderService;
import com.etsm.ETSM.Services.MainService;
import com.etsm.ETSM.Services.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class ProductsControllerTest {


    @Test
    public void GetCategoryTest(){
        ProductService productServiceMock = mock(ProductService.class);
        MainService mainServiceMock = mock(MainService.class);
        HeaderService headerServiceMock = mock(HeaderService.class);

        ProductsController productsController =
                new ProductsController(productServiceMock,mainServiceMock,headerServiceMock);
        productsController.setProductService(productServiceMock);
        productsController.setMainService(mainServiceMock);
        productsController.setHeaderService(headerServiceMock);


        String categoryname="name";
        User user = new User();
        user.setId(1L);
        user.setRoles(Collections.singleton(Role.ADMIN));
        Optional<User> userOptional = Optional.of(user);


        User finalUserForRole = user;
        Principal principal = () -> finalUserForRole.getUsername();
        headerServiceMock.setHeader(principal);

        Category category = new Category();
        category.setId(1L);
        Optional<Category> optionalCategory = Optional.of(category);
        List<Category> categoryList = List.of(category);

        Mockito.when(productServiceMock.findCategoryByName(categoryname)).thenReturn(optionalCategory);
        SubCategory subCategory = new SubCategory();
        subCategory.setId(1L);
        List<SubCategory> subCategoryList = List.of(subCategory);
        Mockito.when(productServiceMock.findSubCategoriesFromCategory(categoryname)).thenReturn(subCategoryList);
        Mockito.when(mainServiceMock.GetAllCategories()).thenReturn(categoryList);
        Mockito.when(headerServiceMock.getHeaderRole()).thenReturn("ADMIN");
        ModelAndView m1 = productsController.GetCategory(categoryname,principal);

        Assert.assertEquals(m1.getViewName(),"catalog/category");

        ModelAndView m2 = productsController.GetCategory("test",principal);
        Assert.assertEquals(m2.getViewName(),"errors/404");


    }

    @Test
    public void GetSubCategoryTest(){
        ProductService productServiceMock = mock(ProductService.class);
        MainService mainServiceMock = mock(MainService.class);
        HeaderService headerServiceMock = mock(HeaderService.class);

        ProductsController productsController =
                new ProductsController(productServiceMock,mainServiceMock,headerServiceMock);
        productsController.setProductService(productServiceMock);
        productsController.setMainService(mainServiceMock);
        productsController.setHeaderService(headerServiceMock);


        String subcategoryname="name";
        User user = new User();
        user.setId(1L);
        user.setRoles(Collections.singleton(Role.ADMIN));
        Optional<User> userOptional = Optional.of(user);


        User finalUserForRole = user;
        Principal principal = () -> finalUserForRole.getUsername();
        headerServiceMock.setHeader(principal);

        Category category = new Category();
        category.setId(1L);
        Optional<Category> optionalCategory = Optional.of(category);
        List<Category> categoryList = List.of(category);
        SubCategory subCategory = new SubCategory();
        subCategory.setId(1L);
        SubCategory subcategory = new SubCategory();
        subCategory.setId(1L);
        Optional<SubCategory> optionalSubCategory = Optional.of(subcategory);
        Mockito.when(productServiceMock.findSubCategoryByName(subcategoryname)).thenReturn(optionalSubCategory);
        MinorCategory minorCategory = new MinorCategory();
        minorCategory.setId(1L);
        List<MinorCategory> minorCategoryList = List.of(minorCategory);
        List<SubCategory> subCategoryList = List.of(subCategory);

        Mockito.when(productServiceMock.findMinorCategoriesFromSubCategory(subcategoryname)).thenReturn(minorCategoryList);
        Mockito.when(mainServiceMock.GetAllCategories()).thenReturn(categoryList);
        Mockito.when(headerServiceMock.getHeaderRole()).thenReturn("ADMIN");
        ModelAndView m1 = productsController.GetSubCategory(subcategoryname,principal);

        Assert.assertEquals(m1.getViewName(),"/catalog/category/productsInSubCategory");

        ModelAndView m2 = productsController.GetSubCategory("test",principal);
        Assert.assertEquals(m2.getViewName(),"errors/404");
    }

    @Test
    public void GetMinorCategoryTest(){
        ProductService productServiceMock = mock(ProductService.class);
        MainService mainServiceMock = mock(MainService.class);
        HeaderService headerServiceMock = mock(HeaderService.class);

        ProductsController productsController =
                new ProductsController(productServiceMock,mainServiceMock,headerServiceMock);
        productsController.setProductService(productServiceMock);
        productsController.setMainService(mainServiceMock);
        productsController.setHeaderService(headerServiceMock);


        String categoryname="name";
        User user = new User();
        user.setId(1L);
        user.setRoles(Collections.singleton(Role.ADMIN));
        Optional<User> userOptional = Optional.of(user);


        User finalUserForRole = user;
        Principal principal = () -> finalUserForRole.getUsername();
        headerServiceMock.setHeader(principal);

        String minorCategoryName = "name";
        String page = "page";

        List<Integer> pages = new ArrayList<>();
        int maxProductsInPage = 1;

        Product product = new Product();
        product.setId(1L);
        List<Product> productList = List.of(product);

       Mockito.when(productServiceMock.findProductsFromMinorCategory(minorCategoryName,page,maxProductsInPage)).thenReturn(productList);

       Mockito.when(productServiceMock.GetAllProductsCountInMinorCategory(minorCategoryName)).thenReturn(3L);

       for (int i =0;i<3;i++){
           pages.add(i);
       }
    MinorCategory minorCategory = new MinorCategory();
       minorCategory.setId(1L);
       Optional<MinorCategory> optionalMinorCategory = Optional.of(minorCategory);
        Mockito.when(productServiceMock.findMinorCategoryByName(minorCategoryName)).thenReturn(optionalMinorCategory);
       Category category = new Category();
       category.setId(1L);
       List<Category> categoryList = List.of(category);
        Mockito.when(mainServiceMock.GetAllCategories()).thenReturn(categoryList);
        Mockito.when(headerServiceMock.getHeaderRole()).thenReturn("ADMIN");

        ModelAndView m1 = productsController.GetMinorCategory(minorCategoryName,page,principal);

        Assert.assertEquals(m1.getViewName(),"/catalog/category/subCategory/productsinMinCategory");

        ModelAndView m2 = productsController.GetMinorCategory("kavo","dd",principal);
        Assert.assertEquals(m2.getViewName(),"errors/404");

    }

    @Test
    public void GetProductTest(){
        ProductService productServiceMock = mock(ProductService.class);
        MainService mainServiceMock = mock(MainService.class);
        HeaderService headerServiceMock = mock(HeaderService.class);

        ProductsController productsController =
                new ProductsController(productServiceMock,mainServiceMock,headerServiceMock);
        productsController.setProductService(productServiceMock);
        productsController.setMainService(mainServiceMock);
        productsController.setHeaderService(headerServiceMock);


        String productName="name";
        User user = new User();
        user.setId(1L);
        user.setRoles(Collections.singleton(Role.ADMIN));
        Optional<User> userOptional = Optional.of(user);


        User finalUserForRole = user;
        Principal principal = () -> finalUserForRole.getUsername();
        headerServiceMock.setHeader(principal);

        Product product = new Product();
        product.setId(1L);
        Optional<Product> optionalProduct = Optional.of(product);

        Mockito.when(productServiceMock.findProductByName(productName)).thenReturn(optionalProduct);
        Category category = new Category();
        category.setId(1L);
        List<Category> categoryList = List.of(category);
        Mockito.when(mainServiceMock.GetAllCategories()).thenReturn(categoryList);
        Mockito.when(headerServiceMock.getHeaderRole()).thenReturn("ADMIN");

        ModelAndView m1 = productsController.GetProduct(productName,principal);
        Assert.assertEquals(m1.getViewName(),"catalog/category/subCategory/minorCategory/product");

        ModelAndView m2 = productsController.GetProduct("ddd",principal);
        Assert.assertEquals(m2.getViewName(),"errors/404");



    }

}