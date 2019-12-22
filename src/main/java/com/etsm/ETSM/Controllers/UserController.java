/*
 * Copyright (c) 2019. Smalkov Nikita.
 */

package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Models.UserInfo;
import com.etsm.ETSM.Services.HeaderService;
import com.etsm.ETSM.Services.UserInformationService;
import com.etsm.ETSM.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Map;

/*
Контроллер, отвечающий за действия с юзером. Изменение, удаление юзера итд
 */
@Controller
public class UserController {
    private UserService userService;
    private UserInformationService userInformationService;
    private HeaderService headerService;

    public UserController(UserService userService, UserInformationService userInformationService, HeaderService headerService) {
        this.userService = userService;
        this.userInformationService = userInformationService;
        this.headerService = headerService;
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
    public String editAuth(@ModelAttribute User user) {
        if (userInformationService.editUserAuth(user))
            return "redirect:/user";
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
    public String addUserInfo(@ModelAttribute UserInfo userInfo, Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        if (userInformationService.addUserInfo(user, userInfo))
            return "redirect:/user";
        else
            return "/auth/addUserInfo";
    }

    @GetMapping("/auth/deleteUser")
    public String deleteUser(Principal principal, HttpSession session) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        userInformationService.deleteUser(user);
        session.invalidate();
        return "redirect:/";
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
}
