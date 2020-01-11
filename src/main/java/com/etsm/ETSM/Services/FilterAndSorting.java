package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.Attribute;
import com.etsm.ETSM.Models.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FilterAndSorting {
    public static List<Product> FilterByAttribute(List<Product> productList, AttributeWrapper attributeWrapper){
        List<Product> newProductList = new ArrayList<>();
        for (Product product:productList) {
            if(product.getProductAttrValue().containsAll(attributeWrapper.getAttrValues())){
                newProductList.add(product);
            }
        }
        return newProductList;
    }

    public static List<Product> FilterByPrice(List<Product> productList, int maxPrice){
        List<Product> newProductList = new ArrayList<>();
        for (Product product:productList) {
            if(product.getPrice() < maxPrice){
                newProductList.add(product);
            }
        }
        return newProductList;
    }

    public static List<Product> FilterByCategory(List<Product> productList, String categoryName){
        if(!categoryName.equals("all")){
            List<Product> newProductList = new ArrayList<>();
            for (Product product:productList) {
                if(product.getMinorcategoryid().getSubcategory_id().getCategory_id().getName().equals(categoryName)){
                    newProductList.add(product);
                }
            }
            return newProductList;
        }else {
            return productList;
        }
    }

    public static List<Product> SortBy(List<Product> productList, String sortParam){
        switch (sortParam){
            case "name":{
                productList.sort(Comparator.comparing(Product::getName));
                break;
            }
            case "price":{
                productList.sort(Comparator.comparing(Product::getPrice));
                break;
            }
            case "count":{
                productList.sort(Comparator.comparing(Product::getCount));
                break;
            }
        }
        return productList;
    }

    public static List<Product> GetProductsForPage(List<Product> productList, int maxProductsInPage, int pageNumber){
        List<Product> newProductList = new ArrayList<>();
        for (int i = ((pageNumber) * maxProductsInPage); (i < ((pageNumber + 1) * maxProductsInPage) && i < productList.size()); i++) {
            newProductList.add(productList.get(i));
        }
        return newProductList;
    }
}
