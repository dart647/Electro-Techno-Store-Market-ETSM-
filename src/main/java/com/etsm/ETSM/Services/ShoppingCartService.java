/*
 * Copyright (c) 2019. Smalkov Nikita.
 */

package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.Product;
import com.etsm.ETSM.Models.UserInfo;
import com.etsm.ETSM.Repositories.SalesRepository;
import com.etsm.ETSM.Repositories.Sales_has_productRepository;
import com.etsm.ETSM.Repositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

public interface ShoppingCartService {
    Map<Product,Integer> productMap = new HashMap<>();
    Map<Product,Integer> getItems();
    boolean addItemToCart(String code);
    void deleteItemFromCart(String code);
    void changeQuantity(String code, String type);
    Map<Product,Integer> setOrder(UserInfo user);
    void clearCart();
}

@Service
class ShoppingCartServiceImpl implements ShoppingCartService {

    private UserInfoRepository userInfoRepository;
    private Sales_has_productRepository sales_has_productRepository;
    private SalesRepository salesRepository;
    private ProductService productService;

    @Override
    public Map<Product, Integer> getItems() {
        return productMap;
    }

    @Override
    public boolean addItemToCart(String code) {
        Long id = Long.parseLong(code);
        Product product = productService.findProductById(id).get();
        Object[] pair = findEntry(product);
        Product target = (Product) pair[0];
        if(target == null){
            productMap.put(product,1);
            return true;
        }
        else {
            changeQuantity(code,"plus");
            return true;
        }
    }

    @Override
    public void deleteItemFromCart(String code) {
        Long id = Long.parseLong(code);
        Product product = productService.findProductById(id).get();
        Object[] pair = findEntry(product);
        Product target = (Product) pair[0];
        productMap.remove(target);
    }

    @Override
    public void changeQuantity(String code, String type) {
        Long id = Long.parseLong(code);
        Product product = productService.findProductById(id).get();
        Object[] pair = findEntry(product);
        Product target = (Product) pair[0];
        int targetVal = (Integer) pair[1];
        if (type.equals("plus")) {
            targetVal++;
        } else {
            targetVal--;
        }
        if (targetVal == 0)
            productMap.remove(target);
        if (target != null) {
            productMap.replace(target, targetVal);
        }
    }

    public Object[] findEntry(Product product) {
        Product target = null;
        Integer value = 0;
        for (Map.Entry<Product,Integer> pair: productMap.entrySet()) {
            Product product1 = pair.getKey();
            if (product1.getName().equals(product.getName())) {
                target = product1;
                value = pair.getValue();
            }
        }
        return new Object[] {target,value};
    }

    @Override
    public Map<Product, Integer> setOrder(UserInfo user) {
        return null;
    }

    @Override
    public void clearCart() {
        productMap.clear();
    }

    @Autowired
    public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }
    @Autowired
    public void setSales_has_productRepository(Sales_has_productRepository sales_has_productRepository) {
        this.sales_has_productRepository = sales_has_productRepository;
    }
    @Autowired
    public void setSalesRepository(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }
    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}