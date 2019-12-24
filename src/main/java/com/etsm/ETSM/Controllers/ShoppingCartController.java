/*
 * Copyright (c) 2019. Smalkov Nikita.
 */

package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Services.HeaderService;
import com.etsm.ETSM.Services.MainService;
import com.etsm.ETSM.Services.ProductService;
import com.etsm.ETSM.Services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/basket")
public class ShoppingCartController {

    private ProductService productService;

    private ShoppingCartService shoppingCartService;

    private HeaderService headerService;

    private MainService mainService;

    //Basket Page
    @GetMapping("")
    public ModelAndView Basket(Principal principal, HttpSession session) {
        headerService.setHeader(principal);
        shoppingCartService.getTotalOrderPrice(session);
        return new ModelAndView("/auth/basket",
                Map.of("categories", mainService.GetAllCategories(),
                        "role", headerService.getHeaderRole()),
                HttpStatus.OK);
    }

    @GetMapping("/delete")
    public String removeProductFromCart(@RequestParam(value = "code") String code,
                                        HttpSession session) {
        shoppingCartService.deleteItemFromCart(code,session);
        return "redirect:/basket";
    }

    @GetMapping("/change")
    public String changeQuantity(@RequestParam(value = "code") String code,
                                 @RequestParam(value = "type") String type,
                                 HttpSession session) {
        shoppingCartService.changeQuantity(code, type,session);
        return "redirect:/basket";
    }

    @GetMapping("/clearCart")
    public String clearCart(HttpSession session) {
        shoppingCartService.clearCart(session);
        return "redirect:/";
    }

    @GetMapping("/createOrder")
    public ModelAndView createOrder() {
        return null;
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
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @Autowired
    public void setHeaderService(HeaderService headerService) {
        this.headerService = headerService;
    }
}
