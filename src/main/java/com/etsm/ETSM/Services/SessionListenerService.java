/*
 * Copyright (c) 2019. Smalkov Nikita.
 */

package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.CartItem;
import com.etsm.ETSM.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public class SessionListenerService implements ApplicationListener<ApplicationEvent> {

    HttpSession httpSession;

    ShoppingCartService shoppingCartService;

    ProductService productService;

    @Override
    @EventListener
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof HttpSessionDestroyedEvent) {
            List<CartItem> cartItems = (List<CartItem>) httpSession.getAttribute("cart");
            for (CartItem cartItem : cartItems) {
                int quantity = cartItem.getQuantity();
                Product product = cartItem.getProduct();
                productService.reserveItem(product,quantity,false);
            }
        }
    }

    @Autowired
    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
