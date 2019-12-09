package com.etsm.ETSM.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

// Это главный контроллер для авторизации и главной страницы
// можно задавать @RequestParam, тогда при разных обращениях к странице получаем параметр (?name=имя), с которым можем работать
// в return указывает view и model (можно без нее)

@Controller
@RequestMapping("/")
public class MainController {
    //Main Page
    @GetMapping
    ModelAndView MainPage(@RequestParam(name="name", required = false, defaultValue = "World") String name)
    {
        return new ModelAndView("main",
                Map.of("name", name),
                HttpStatus.OK);
    }

    //Login Page
    @GetMapping("/login")
    public ModelAndView Login(){
        return new ModelAndView("/auth/login", HttpStatus.OK);
    }

    //User Cabinet Page
    @GetMapping("/user")
    public ModelAndView UserCabinet(){
        return new ModelAndView("/auth/userCabinet", HttpStatus.OK);
    }

    //Basket Page
    @GetMapping("/basket")
    public ModelAndView Basket(){
        return new ModelAndView("/auth/basket", HttpStatus.OK);
    }

    //Admin panel page
    @GetMapping("/admin")
    public ModelAndView Admin() {
        return new ModelAndView("/auth/admin", HttpStatus.OK);
    }
}
