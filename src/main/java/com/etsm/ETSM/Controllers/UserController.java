/*
 * Copyright (c) 2019. Smalkov Nikita.
 */

package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.Sales;
import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Models.UserInfo;
import com.etsm.ETSM.Services.HeaderService;
import com.etsm.ETSM.Services.ProductService;
import com.etsm.ETSM.Services.UserInformationService;
import com.etsm.ETSM.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Map;

/*
Контроллер, отвечающий за действия с юзером. Изменение, удаление юзера итд
 */
@Controller
public class UserController {
    private UserService userService;
    private UserInformationService userInformationService;
    private HeaderService headerService;
    private ProductService productService;

    public UserController(UserService userService, UserInformationService userInformationService,
                          HeaderService headerService, ProductService productService) {
        this.userService = userService;
        this.userInformationService = userInformationService;
        this.headerService = headerService;
        this.productService = productService;
    }

    @GetMapping("/auth/editAuth")
    public ModelAndView editAuth(Principal principal) {
        headerService.setHeader(principal);
        return new ModelAndView("/auth/editAuth",
                Map.of("user", headerService.getUser(),
                        "role", headerService.getHeaderRole(),
                        "categories", headerService.getHeaderCategories()),
                HttpStatus.OK);
    }

    //Edit auth information
    @PostMapping("/auth/editAuth")
    public String editAuth(@ModelAttribute User user, Principal principal, HttpSession session) {
        User oldUser = (User) userService.loadUserByUsername(principal.getName());
        if (userInformationService.editUserAuth(oldUser,user)) {
            session.invalidate();
            return "redirect:/login";
        }
        else
            return "/auth/editAuth";
    }

    @GetMapping("/auth/addUserInfo")
    public ModelAndView addUserInfo(Principal principal) {
        headerService.setHeader(principal);
        return new ModelAndView("/auth/addUserInfo",
                Map.of("user", headerService.getUser(),
                        "userInfo", headerService.getUser().getUserInfo(),
                        "role", headerService.getHeaderRole(),
                        "categories", headerService.getHeaderCategories()),
                HttpStatus.OK);
    }

    //Add additional user information
    @PostMapping("/auth/addUserInfo")
    public String addUserInfo(@ModelAttribute UserInfo userInfo,
                              Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        if (userInformationService.addUserInfo(user, userInfo)) {
            return "redirect:/user";
        }
        else {
            return "/auth/addUserInfo";
        }
    }

    @GetMapping("/auth/deleteUser")
    public String deleteUser(Principal principal, HttpSession session) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        userInformationService.deleteUser(user);
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/orders")
    public ModelAndView showOrders(HttpSession session, Principal principal) {
        UserInfo userInfo = headerService.getUser().getUserInfo();
        return new ModelAndView("/auth/orders",
                Map.of("user", headerService.getUser(),
                        "userInfo", headerService.getUser().getUserInfo(),
                        "role", headerService.getHeaderRole(),
                        "categories", headerService.getHeaderCategories(),
                        "orders", productService.findSalesByUser(userInfo)),
                HttpStatus.OK);
    }

    @GetMapping("/orders/order")
    public ModelAndView showOrder(@RequestParam(value = "code") String code, Principal principal) {
        Sales sales1 = productService.findSalesById(Long.parseLong(code)).get();
        List<Sales> salesList = headerService.getUser().getUserInfo().getSales();
        return productService.findSalesById(Long.parseLong(code))
                .map(sales -> new ModelAndView("auth/order",
                        Map.of("user", headerService.getUser(),
                                "userInfo", headerService.getUser().getUserInfo(),
                                "role", headerService.getHeaderRole(),
                                "categories", headerService.getHeaderCategories(),
                                "order", sales),
                        HttpStatus.OK))
                .orElseGet(() -> new ModelAndView("errors/404",
                        Map.of("error","Couldn't find an order"), HttpStatus.NOT_FOUND));
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserInformationService(UserInformationService userInformationService) {
        this.userInformationService = userInformationService;
    }

    @Autowired
    public void setHeaderService(HeaderService headerService) {
        this.headerService = headerService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
