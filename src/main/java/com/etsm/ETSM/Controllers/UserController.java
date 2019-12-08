/*
 * Copyright (c) 2019. Nikita Smalkov
 */

package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.UserEntity;
import com.etsm.ETSM.Models.UserRepository;
import com.etsm.ETSM.Models.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
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
    private UserRepository userRepository = new UserRepositoryImpl();

    @PostMapping(path="/add/user")
    public @ResponseBody String addNewUser (@RequestParam String email,
                                            @RequestParam String password) {
        UserEntity newUser = new UserEntity();
        newUser.setEmail(email);
        newUser.setPassword(password);
        userRepository.save(newUser);
        return "Saved";
    }
/*
    @GetMapping(path="/all")
    public @ResponseBody Iterable<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

 */

    @GetMapping("/all")
    public ModelAndView getAllUsers() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users/all");
        modelAndView.addObject(Map.of("users",this.userRepository.findAll()));
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
        /*return new ModelAndView("users/all",
                Map.of("users",this.userRepository.findAll()),
                HttpStatus.OK); */
    }

    @GetMapping("{userId}")
    public ModelAndView user(@PathVariable int userId) {
        return this.userRepository.findById((long)userId)
                .map(user -> new ModelAndView("users/user",
                        Map.of("user",user), HttpStatus.OK))
                .orElseGet(() -> new ModelAndView("errors/404",
                        Map.of("error","Couldn't find a user"), HttpStatus.NOT_FOUND));

    }
}
