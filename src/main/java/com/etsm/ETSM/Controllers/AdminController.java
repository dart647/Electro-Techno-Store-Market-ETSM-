package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.Product;
import com.etsm.ETSM.Models.Role;
import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Services.AdminService;
import com.etsm.ETSM.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ModelAndView getAllUsers(Principal principal) {
        User userForRole = new User();
        userForRole.setRoles(new HashSet<Role>(Collections.singleton(Role.USER)));
        if (principal != null) {
            userForRole = (User) userService.loadUserByUsername(principal.getName());
        }
        return new ModelAndView("admin/all",
                Map.of("users", adminService.findUsers(),
                        "role", userForRole.getRoles().toArray()[0].toString()),
                HttpStatus.OK);
    }

    @GetMapping("{userId}")
    public ModelAndView user(@PathVariable long userId, Principal principal) {
        User userForRole = new User();
        userForRole.setRoles(new HashSet<Role>(Collections.singleton(Role.USER)));
        if (principal != null) {
            userForRole = (User) userService.loadUserByUsername(principal.getName());
        }

        User finalUserForRole = userForRole;

        return adminService.findUserById(userId)
                .map(user -> new ModelAndView("admin/user",
                        Map.of("user",user,
                                "role", finalUserForRole.getRoles().toArray()[0].toString()), HttpStatus.OK))
                .orElseGet(() -> new ModelAndView("errors/404",
                        Map.of("error","Couldn't find a user"), HttpStatus.NOT_FOUND));

    }

    @GetMapping("/addProduct")
    public ModelAndView AddProduct(Principal principal){
        User userForRole = new User();
        userForRole.setRoles(new HashSet<Role>(Collections.singleton(Role.USER)));
        if (principal != null) {
            userForRole = (User) userService.loadUserByUsername(principal.getName());
        }
        Product product = new Product();
        return new ModelAndView("admin/addProduct",
                Map.of("product", product,
                        "role", userForRole.getRoles().toArray()[0].toString()),
                HttpStatus.OK);
    }

    @PostMapping("/addProduct")
    public String AddProduct(@ModelAttribute Product product){
        adminService.addNewProduct(product);
        return "redirect:/catalog/list";
    }
}
