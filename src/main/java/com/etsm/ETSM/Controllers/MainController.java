package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    //Main Page
    @GetMapping
    public ModelAndView MainPage(Principal principal)
    {
        return new ModelAndView("/main",
                Map.of("products", service.SetRecommendations(),
                        "categories", service.GetAllCategories()),
                HttpStatus.OK);
    }

    //User Cabinet Page
    @GetMapping("/user")
    public ModelAndView UserCabinet(){
        return new ModelAndView("/auth/userCabinet",
                Map.of("categories", service.GetAllCategories()),
                HttpStatus.OK);
    }

    //Basket Page
    @GetMapping("/basket")
    public ModelAndView Basket(){
        return new ModelAndView("/auth/basket",
                Map.of("categories", service.GetAllCategories()),
                HttpStatus.OK);
    }

    //Admin panel page
    @GetMapping("/admin")
    public ModelAndView Admin() {
        return new ModelAndView("/auth/admin",
                Map.of("categories", service.GetAllCategories()),
                HttpStatus.OK);
    }
}
