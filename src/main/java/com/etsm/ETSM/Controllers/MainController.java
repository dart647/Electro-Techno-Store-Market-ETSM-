package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.Role;
import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Services.MainService;
import com.etsm.ETSM.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Map;

// Это главный контроллер для авторизации и главной страницы
// можно задавать @RequestParam, тогда при разных обращениях к странице получаем параметр (?name=имя), с которым можем работать
// в return указывает view и model (можно без нее)

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private MainService service;
    @Autowired
    private UserService userService;

    //Main Page
    @GetMapping("/")
    public ModelAndView MainPage(Principal principal) {
        User user = new User();
        if (principal != null) {
            user = (User) userService.loadUserByUsername(principal.getName());
        }
        String search = "";
        return new ModelAndView("/main",
                Map.of("products", service.SetRecommendations(),
                        "categories", service.GetAllCategories(),
                        "searchProducts", service.GetSearchProducts(""),
                        "search", search,
                        "user", user,
                        "Role", Role.values()),
                HttpStatus.OK);
    }

    @PostMapping("/")
    public ModelAndView MainPageWithSearch(@ModelAttribute("searching") String searching, Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        return new ModelAndView("/main",
                Map.of("products", service.SetRecommendations(),
                        "categories", service.GetAllCategories(),
                        "searchProducts", service.GetSearchProducts(searching),
                        "search", searching,
                        "user", user),
                HttpStatus.OK);
    }

    //User Cabinet Page
    @GetMapping("/user")
    public ModelAndView UserCabinet(Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        return new ModelAndView("/auth/userCabinet",
                Map.of("user", user),
                HttpStatus.OK);
    }

    //Basket Page
    @GetMapping("/basket")
    public ModelAndView Basket(Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        return new ModelAndView("/auth/basket",
                Map.of("categories", service.GetAllCategories(),
                        "user", user),
                HttpStatus.OK);
    }

    //Admin panel page
    @GetMapping("/admin")
    public ModelAndView Admin(Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        return new ModelAndView("/auth/admin",
                Map.of("user", user),
                HttpStatus.OK);
    }

//    @GetMapping("/uLogin")
//    public ModelAndView Login(){
//        return new ModelAndView("/auth/login", HttpStatus.OK);
//    }

    @GetMapping("/about")
    public ModelAndView About(Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        return new ModelAndView("/about",
                Map.of("user", user),
                HttpStatus.OK);
    }
}
