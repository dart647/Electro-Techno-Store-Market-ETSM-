/*
 * Copyright (c) 2019. Smalkov Nikita.
 */

package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Services.MainService;
import com.etsm.ETSM.Services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/*
Контроллер, отвечающий за регистрацию пользователей.
 */
@Controller
public class RegistrationController {
    @Autowired
    RegistrationService registrationService;
    @Autowired
    MainService mainService;

    @GetMapping("/registration")
    public ModelAndView registration() {
        User user = new User();
        return new ModelAndView("/registration",
                Map.of("user",user,
                        "categories", mainService.GetAllCategories()),
                HttpStatus.OK);
    }

    @PostMapping("/registration")
    public String addUser (@ModelAttribute User user) {
        registrationService.AddNewUser(user);
        return "redirect:/";
    }
}
