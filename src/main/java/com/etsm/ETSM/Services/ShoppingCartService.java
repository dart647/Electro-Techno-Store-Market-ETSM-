/*
 * Copyright (c) 2019. Smalkov Nikita.
 */

package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.CartItem;
import com.etsm.ETSM.Models.Product;
import com.etsm.ETSM.Models.UserInfo;
import com.etsm.ETSM.Repositories.SalesRepository;
import com.etsm.ETSM.Repositories.Sales_has_productRepository;
import com.etsm.ETSM.Repositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

public interface ShoppingCartService {
    public boolean addItemToCart(String code, HttpSession session);
    public boolean deleteItemFromCart(String code, HttpSession session);
    public boolean changeQuantity(String code, String type,HttpSession session);
    public Map<Product,Integer> setOrder(UserInfo user);
    public void clearCart(HttpSession session);
    public boolean getTotalOrderPrice(HttpSession session);
}

@Service
class ShoppingCartServiceImpl implements ShoppingCartService {

    private UserInfoRepository userInfoRepository;
    private Sales_has_productRepository sales_has_productRepository;
    private SalesRepository salesRepository;
    private ProductService productService;

    @Override
    public boolean addItemToCart(String code, HttpSession session) {
        Long id = Long.parseLong(code);
        Product product = productService.findProductById(id).orElse(null);
        if (product == null)
            return false;
        if (session.getAttribute("cart") == null) {
            List<CartItem> cart = new ArrayList<>();
            cart.add(new CartItem(product,1));
            session.setAttribute("cart",cart);
        } else {
            List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
            int index = this.exists(id, cart);
            if (index == -1) {
                cart.add(new CartItem(product,1));
            } else {
                changeQuantity(code,"plus",session);
            }
            session.setAttribute("cart",cart);
        }
        return true;
    }

    @Override
    public boolean deleteItemFromCart(String code, HttpSession session) {
        Long id = Long.parseLong(code);
        Product product = productService.findProductById(id).orElse(null);
        if (product == null)
            return false;
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        int index = this.exists(id,cart);
        if (index == -1)
            return false;
        cart.remove(index);
        session.setAttribute("cart",cart);
        return true;
    }

    @Override
    public boolean changeQuantity(String code, String type,HttpSession session) {
        Long id = Long.parseLong(code);
        Product product = productService.findProductById(id).orElse(null);
        if (product == null)
            return false;
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        int index = this.exists(id,cart);
        if (index == -1)
            return false;
        int quantity = cart.get(index).getQuantity();
        if (type.equals("plus")) {
            quantity++;
            cart.get(index).setQuantity(quantity);
            cart.get(index).setTotalPrice();
        } else if (type.equals("minus")) {
            quantity--;
            cart.get(index).setQuantity(quantity);
            cart.get(index).setTotalPrice();
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean getTotalOrderPrice(HttpSession session) {
        if (session.getAttribute("cart") == null) {
            return false;
        } else {
            List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
            int totalOrderPrice = 0;
            for (CartItem cartItem : cart) {
                totalOrderPrice+=cartItem.getTotalPrice();
            }
            session.setAttribute("totalOrderPrice",totalOrderPrice);
            return true;
        }
    }

    @Override
    public Map<Product, Integer> setOrder(UserInfo user) {
        return null;
    }

    @Override
    public void clearCart(HttpSession session) {
        if (session.getAttribute("cart") != null) {
            session.removeAttribute("cart");
        }
    }

    private int exists(Long id, List<CartItem> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProduct().getId() == id) {
                return i;
            }
        }
        return -1;
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