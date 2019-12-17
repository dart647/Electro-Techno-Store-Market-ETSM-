package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.Product;
import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Repositories.UserRepository;
import com.etsm.ETSM.Services.AdminService;
import com.etsm.ETSM.Services.MainService;
import com.etsm.ETSM.Services.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;
    MainService mainService;
    UserInformationService userInformationService;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @Autowired
    public void setUserInformationService(UserInformationService userInformationService) {
        this.userInformationService = userInformationService;
    }

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

    @GetMapping("{userId}/deleteUser")
    public String deleteOneUser(@PathVariable long userId) {
        Optional<User> optionalUser = adminService.findUserById(userId);
        optionalUser.ifPresent(user -> userInformationService.deleteUser(user));
        return "redirect:/admin";
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
