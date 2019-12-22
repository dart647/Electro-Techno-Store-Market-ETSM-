/*
 * Copyright (c) 2019. Smalkov Nikita.
 */

package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Services.HeaderService;
import com.etsm.ETSM.Services.RegistrationService;
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
Контроллер, отвечающий за регистрацию пользователей.
 */
@Controller
public class RegistrationController {

    private RegistrationService registrationService;

    private UserService userService;

    private HeaderService headerService;

    public RegistrationController(RegistrationService registrationService, UserService userService) {
        this.registrationService = registrationService;
        this.userService = userService;
    }

    @GetMapping("/registration")
    public ModelAndView registration(Principal principal) {
        headerService.setHeader(principal);
        return new ModelAndView("/registration",
                Map.of("user", headerService.getUser(),
                        "role", headerService.getHeaderRole(),
                        "categories", headerService.getHeaderCategories()),
                HttpStatus.OK);
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute User user) {
        if (registrationService.AddNewUser(user))
            return "redirect:/";
        else
            return "/registration";
    }

    @Autowired
    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setHeaderService(HeaderService headerService) {
        this.headerService = headerService;
    }
}
