/*
 * Copyright (c) 2019. Nikita Smalkov
 */

package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Repositories.UserInfoRepository;
import com.etsm.ETSM.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/*
* Контроллер, отвечающий за работу с пользователями.
* Осуществляет операции вывода всех пользователей на экран.
* Осуществляет действия с таблицей пользователей.
*/

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @PostMapping(path="/add/user")
    public @ResponseBody String addNewUser (@RequestParam String email,
                                            @RequestParam String password) {
        User newUser = new User();
        newUser.setLogin(email);
        newUser.setPassword(password);
        userRepository.save(newUser);
        return "Saved";
    }

    @GetMapping("/all")
    public ModelAndView getAllUsers() {
        return new ModelAndView("users/all",
                Map.of("usersInfo",this.userInfoRepository.findAll()),
                HttpStatus.OK);
    }

    @GetMapping("{userId}")
    public ModelAndView user(@PathVariable int userId) {
        return this.userInfoRepository.findById((long)userId)
                .map(user -> new ModelAndView("users/user",
                        Map.of("userInfo",user), HttpStatus.OK))
                .orElseGet(() -> new ModelAndView("errors/404",
                        Map.of("error","Couldn't find a user"), HttpStatus.NOT_FOUND));

    }
}
