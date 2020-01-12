package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class FilterAndSortingTest {

    @Test
    public void FilterByAttributeTest(){

        FilterAndSorting filterAndSorting = new FilterAndSorting();
        //

        Product product = new Product();
        product.setId(1L);

        List<Product> productList = List.of(product);
        ProductAttrValue productAttrValue = new ProductAttrValue();
        productAttrValue.setId(1L);
        productAttrValue.setValue("10");
       // productAttrValue.setProduct(product);
        List<ProductAttrValue> productAttrValueList = List.of(productAttrValue);
        List<ProductAttrValue> productAttrValueLinkedList = new LinkedList<>(productAttrValueList);

        product.setProductAttrValue(productAttrValueLinkedList);
        AttributeWrapper attributeWrapper = new AttributeWrapper() {
            @Override
            public List<ProductAttrValue> getAttrValues() {
                return productAttrValueLinkedList;
            }

            @Override
            public void setAttrValues(List<ProductAttrValue> attrValues) {

            }
        };
        FilterAndSorting.FilterByAttribute(productList,attributeWrapper);

    }

    @Test
    public void FilterByPriceTest(){

        int maxPrice = 100;
        Product product = new Product();
        product.setId(1L);
        product.setPrice(10);
        List<Product> productList = List.of(product);

        FilterAndSorting.FilterByPrice(productList,maxPrice);

    }

    @Test
    public void FilterByCategoryTest(){
        Category category = new Category();

        category.setId(1L);
        category.setName("name");
        SubCategory subCategory = new SubCategory();
        subCategory.setId(1L);
        subCategory.setCategory_id(category);

        MinorCategory minorCategory = new MinorCategory();
        minorCategory.setId(1L);
        List<MinorCategory> minorCategoryList = List.of(minorCategory);
        List<MinorCategory> minorCategoryLinkedList = new LinkedList(minorCategoryList);

        subCategory.setMinorCategoryList(minorCategoryLinkedList);
        minorCategory.setSubcategory_id(subCategory);
        Product product = new Product();
        product.setId(1L);
        product.setPrice(10);

        List<Product> productList = List.of(product);
        List<Product> productLinkedList = new LinkedList<>(productList);
        minorCategory.setProductList(productLinkedList);
        product.setMinorCategory_id(minorCategory);

        FilterAndSorting.FilterByCategory(productList,"name");

    }

    @Test
    public void SortByTest(){

        Product product = new Product();
        product.setId(1L);
        product.setName("name");
        product.setPrice(10);
        product.setCount(1);
        List<Product> productList = List.of(product);
        List<Product> productLinkedList = new LinkedList<>(productList);

        FilterAndSorting.SortBy(productLinkedList,"name");
        FilterAndSorting.SortBy(productLinkedList,"price");
        FilterAndSorting.SortBy(productLinkedList,"count");
    }

    @Test
    public void GetProductsForPageTest(){
        Product product = new Product();
        product.setId(1L);
        product.setName("name");
        product.setPrice(10);
        product.setCount(1);
        List<Product> productList = List.of(product);
        List<Product> productLinkedList = new LinkedList<>(productList);

        int maxProductsInPage = 1;
        int pageNumber = 1;

        FilterAndSorting.GetProductsForPage(productLinkedList,maxProductsInPage,pageNumber);

    }

}