/*
 * Copyright (c) 2019. Smalkov Nikita.
 */

package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.Role;
import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Map;

/*
Контроллер, отвечающий за регистрацию пользователей.
 */
@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public ModelAndView registration() {
        User user = new User();
        return new ModelAndView("/registration",
                Map.of("user",user),
                HttpStatus.OK);
    }

    @PostMapping("/registration")
    public ModelAndView addUser (@ModelAttribute User user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setLogin(user.getLogin());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setActive(true);
        newUser.setRoles(Collections.singleton(Role.USER));
        userRepository.saveAndFlush(newUser);
        return new ModelAndView("/login",
                HttpStatus.OK);
    }
}
