/*
 * Copyright (c) 2019. Smalkov Nikita.
 */

package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.Role;
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
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

/*
Контроллер, отвечающий за действия с юзером. Изменение, удаление юзера итд
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserInformationService userInformationService;

    @GetMapping("/auth/editAuth")
    public ModelAndView editAuth(Principal principal) {
        User userForRole = new User();
        userForRole.setRoles(new HashSet<Role>(Collections.singleton(Role.USER)));
        if (principal != null) {
            userForRole = (User) userService.loadUserByUsername(principal.getName());
        }
        return new ModelAndView("/auth/editAuth",
                Map.of("user",userForRole,
                        "role", userForRole.getRoles().toArray()[0].toString()),
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
        User userForRole = new User();
        userForRole.setRoles(new HashSet<Role>(Collections.singleton(Role.USER)));
        if (principal != null) {
            userForRole = (User) userService.loadUserByUsername(principal.getName());
        }
        return new ModelAndView("/auth/addUserInfo",
                Map.of("user",userForRole,
                        "userInfo",userForRole.getUserInfo(),
                        "role", userForRole.getRoles().toArray()[0].toString()),
                HttpStatus.OK);
    }
    //Add additional user information
    @PostMapping("/auth/addUserInfo")
    public String addUserInfo(@ModelAttribute UserInfo userInfo, Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        if (userInformationService.addUserInfo(user,userInfo))
            return "redirect:/";
        else
            return "/auth/addUserInfo";
    }
}
