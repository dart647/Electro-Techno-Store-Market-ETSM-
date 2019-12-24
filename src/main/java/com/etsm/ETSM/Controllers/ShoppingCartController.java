/*
 * Copyright (c) 2019. Smalkov Nikita.
 */

package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.Product;
import com.etsm.ETSM.Models.Role;
import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Models.UserInfo;
import com.etsm.ETSM.Repositories.UserInfoRepository;
import com.etsm.ETSM.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

@Controller
@RequestMapping("/basket")
public class ShoppingCartController {

    private ProductService productService;

    private ShoppingCartService shoppingCartService;

    private HeaderService headerService;

    private MainService mainService;

    private UserInformationService userInformationService;

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
    public ModelAndView createOrder(@RequestParam(value = "stage") String stage,
                                    @ModelAttribute UserInfo userInfo) {
        return new ModelAndView("/auth/createOrder",
                Map.of("categories", mainService.GetAllCategories(),
                        "role", headerService.getHeaderRole(),
                        "stage", stage,
                        "userInfo",headerService.getUser().getUserInfo()),
                        HttpStatus.OK);
    }

    @PostMapping("/createOrder")
    public String createOrder(@ModelAttribute UserInfo userInfo,
                              Principal principal) {
        User user = headerService.getUser();
        userInformationService.addUserInfo(user, userInfo);
        return "/auth/createOrder?stage=payment";
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

    @Autowired
    public void setUserInformationService(UserInformationService userInformationService) {
        this.userInformationService = userInformationService;
    }
}
