/*
 * Copyright (c) 2019. Smalkov Nikita.
 */

package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.UserInfo;
import com.etsm.ETSM.Services.HeaderService;
import com.etsm.ETSM.Services.MainService;
import com.etsm.ETSM.Services.ShoppingCartService;
import com.etsm.ETSM.Services.StripeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/payment")
public class PaymentController {
    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    private StripeService stripeService;

    private MainService mainService;

    private HeaderService headerService;

    private ShoppingCartService shoppingCartService;

    @RequestMapping("")
    public ModelAndView payment(ModelAndView modelAndView, HttpSession session) {
        int amount = (int)session.getAttribute("totalOrderPrice") * 100;
        modelAndView.setViewName("/auth/payment");
        modelAndView.addObject("amount", amount); // In cents
        modelAndView.addObject("stripePublicKey", stripePublicKey);
        modelAndView.addObject("categories", mainService.GetAllCategories());
        modelAndView.addObject("role", headerService.getHeaderRole());
        return modelAndView;
    }

    @PostMapping("/charge")
    public String chargeCard(HttpServletRequest request, HttpSession session) throws Exception {
        String token = request.getParameter("stripeToken");
        Double amount = Double.parseDouble(request.getParameter("amount"));
        stripeService.chargeNewCard(token, amount);
        UserInfo userInfo = headerService.getUser().getUserInfo();
        shoppingCartService.performOrder(session, userInfo);
        return "redirect:/orders";
    }

    @Autowired
    public void setStripeService(StripeService stripeService) {
        this.stripeService = stripeService;
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
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }
}
