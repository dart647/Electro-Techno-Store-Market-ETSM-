/*
 * Copyright (c) 2019. Nikita Smalkov
 */

package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/add/user")
    public @ResponseBody String addNewUser (@RequestParam String email,
                                            @RequestParam String password) {
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password);
        userRepository.save(newUser);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}
