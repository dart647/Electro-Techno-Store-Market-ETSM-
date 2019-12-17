package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.Product;
import com.etsm.ETSM.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/all")
    public ModelAndView getAllUsers() {
        return new ModelAndView("admin/all",
                Map.of("users", adminService.findUsers()),
                HttpStatus.OK);
    }

    @GetMapping("{userId}")
    public ModelAndView user(@PathVariable long userId) {
        return adminService.findUserById(userId)
                .map(user -> new ModelAndView("admin/user",
                        Map.of("user",user), HttpStatus.OK))
                .orElseGet(() -> new ModelAndView("errors/404",
                        Map.of("error","Couldn't find a user"), HttpStatus.NOT_FOUND));

    }

    @GetMapping("/addProduct")
    public ModelAndView AddProduct(){
        Product product = new Product();
        return new ModelAndView("admin/addProduct",
                Map.of("product", product),
                HttpStatus.OK);
    }

    @PostMapping("/addProduct")
    public String AddProduct(@ModelAttribute Product product){
        adminService.addNewProduct(product);
        return "redirect:/catalog/list";
    }
}
