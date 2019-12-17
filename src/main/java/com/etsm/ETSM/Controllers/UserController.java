/*
 * Copyright (c) 2019. Smalkov Nikita.
 */

package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Models.UserInfo;
import com.etsm.ETSM.Services.UserInformationService;
import com.etsm.ETSM.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Map;

/*
Контроллер, отвечающий за действия с юзером. Изменение, удаление юзера итд
 */
@Controller
public class UserController {
    private UserService userService;
    private UserInformationService userInformationService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setUserInformationService(UserInformationService userInformationService) {
        this.userInformationService = userInformationService;
    }

    @GetMapping("/auth/editAuth")
    public ModelAndView editAuth(Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        return new ModelAndView("/auth/editAuth",
                Map.of("user",user),
                HttpStatus.OK);
    }

    //Edit auth information
    @PostMapping("/auth/editAuth")
    public String editAuth(@ModelAttribute User user) {
        if (userInformationService.editUserAuth(user))
            return "redirect:/";
        else
            return "/auth/editAuth";
    }

    @GetMapping("/auth/addUserInfo")
    public ModelAndView addUserInfo(Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        UserInfo userInfo = new UserInfo();
        return new ModelAndView("/auth/addUserInfo",
                Map.of("user",user,
                        "userInfo",userInfo),
                HttpStatus.OK);
    }
    //Add additional user information
    @PostMapping("/auth/addUserInfo")
    public String addUserInfo(@ModelAttribute User user, @ModelAttribute UserInfo userInfo) {
        if (userInformationService.addUserInfo(user,userInfo))
            return "redirect:/";
        else
            return "/auth/addUserInfo";
    }
}
