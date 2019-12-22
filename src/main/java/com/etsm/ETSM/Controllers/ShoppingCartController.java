/*
 * Copyright (c) 2019. Smalkov Nikita.
 */

package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.Product;
import com.etsm.ETSM.Models.Role;
import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Services.MainService;
import com.etsm.ETSM.Services.ProductService;
import com.etsm.ETSM.Services.ShoppingCartService;
import com.etsm.ETSM.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

@Controller
@RequestMapping("/basket")
public class ShoppingCartController {

    private ProductService productService;

    private ShoppingCartService shoppingCartService;

    private UserService userService;

    private MainService mainService;

    //Basket Page
    @GetMapping("")
    public ModelAndView Basket(Principal principal) {
        User user = new User();
        user.setRoles(new HashSet<Role>(Collections.singleton(Role.USER)));
        if (principal != null) {
            user = (User) userService.loadUserByUsername(principal.getName());
        }
        Map<Product,Integer> cartItems = shoppingCartService.getItems();
        return new ModelAndView("/auth/basket",
                Map.of("categories", mainService.GetAllCategories(),
                        "role", user.getRoles().toArray()[0].toString(),
                        "items",cartItems),
                HttpStatus.OK);
    }

    @GetMapping("/delete")
    public String removeProductFromCart(@RequestParam(value = "code") String code) {
        shoppingCartService.deleteItemFromCart(code);
        return "redirect:/basket";
    }

    @GetMapping("/change")
    public String changeQuantity(@RequestParam(value = "code") String code,
                                 @RequestParam(value = "type") String type) {
        shoppingCartService.changeQuantity(code, type);
        return "redirect:/basket";
    }

    @GetMapping("/clearCart")
    public String clearCart() {
        shoppingCartService.clearCart();
        return "redirect:/";
    }



    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }
}
