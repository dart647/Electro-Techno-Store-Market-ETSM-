package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.Product;
import com.etsm.ETSM.Models.Role;
import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Services.MainService;
import com.etsm.ETSM.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

// Это главный контроллер для авторизации и главной страницы
// можно задавать @RequestParam, тогда при разных обращениях к странице получаем параметр (?name=имя), с которым можем работать
// в return указывает view и model (можно без нее)

@Controller
@RequestMapping("/")
public class MainController {

    private MainService service;
    private UserService userService;

    @Autowired
    public MainService getService() {
        return service;
    }
    @Autowired
    public void setService(MainService service) {
        this.service = service;
    }

    @Autowired
    public UserService getUserService() {
        return userService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public MainController(MainService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    //Main Page
    @GetMapping("/")
    public ModelAndView MainPage(Principal principal) {
        User user = new User();
        user.setRoles(new HashSet<Role>(Collections.singleton(Role.USER)));
        if (principal != null) {
            user = (User) userService.loadUserByUsername(principal.getName());
        }
        String search = "";
        List<Product> products = service.GetSearchProducts("");
        return new ModelAndView("/main",
                Map.of(
                        "categories", service.GetAllCategories(),
                        "searchProducts", products,
                        "search", search,
                        "role", user.getRoles().toArray()[0].toString()),
                HttpStatus.OK);
    }

    @PostMapping("/")
    public ModelAndView MainPageWithSearch(@ModelAttribute("searching") String searching, Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        List<Product> products = service.GetSearchProducts(searching);
        return new ModelAndView("/main",
                Map.of("products", service.SetRecommendations(),
                        "categories", service.GetAllCategories(),
                        "searchProducts", products,
                        "search", searching,
                        "role", user.getRoles().toArray()[0].toString()),
                HttpStatus.OK);
    }

    //User Cabinet Page
    @GetMapping("/user")
    public ModelAndView UserCabinet(Principal principal) {
        User user = new User();
        user.setRoles(new HashSet<Role>(Collections.singleton(Role.USER)));
        if (principal != null) {
            user = (User) userService.loadUserByUsername(principal.getName());
        }
        return new ModelAndView("/auth/userCabinet",
                Map.of("user", user,
                        "role", user.getRoles().toArray()[0].toString()),
                HttpStatus.OK);
    }

    //Basket Page
    @GetMapping("/basket")
    public ModelAndView Basket(Principal principal) {
        User user = new User();
        user.setRoles(new HashSet<Role>(Collections.singleton(Role.USER)));
        if (principal != null) {
            user = (User) userService.loadUserByUsername(principal.getName());
        }
        return new ModelAndView("/auth/basket",
                Map.of("categories", service.GetAllCategories(),
                        "role", user.getRoles().toArray()[0].toString()),
                HttpStatus.OK);
    }

    //Admin panel page
    @GetMapping("/admin")
    public ModelAndView Admin(Principal principal) {
        User user = new User();
        user.setRoles(new HashSet<Role>(Collections.singleton(Role.USER)));
        if (principal != null) {
            user = (User) userService.loadUserByUsername(principal.getName());
        }
        return new ModelAndView("/auth/admin",
                Map.of("role", user.getRoles().toArray()[0].toString()),
                HttpStatus.OK);
    }

    @GetMapping("/about")
    public ModelAndView About(Principal principal) {
        User user = new User();
        user.setRoles(new HashSet<Role>(Collections.singleton(Role.USER)));
        if (principal != null) {
            user = (User) userService.loadUserByUsername(principal.getName());
        }
        return new ModelAndView("/about",
                Map.of("role", user.getRoles().toArray()[0].toString()),
                HttpStatus.OK);
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("view","login");
        model.addAttribute("title","Вход");
        return "login";
    }
}
