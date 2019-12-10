package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.Product;
import com.etsm.ETSM.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

// Это главный контроллер для авторизации и главной страницы
// можно задавать @RequestParam, тогда при разных обращениях к странице получаем параметр (?name=имя), с которым можем работать
// в return указывает view и model (можно без нее)

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private ProductRepository productRepository;
    //Main Page
    @GetMapping
    ModelAndView MainPage()
    {
        List<Product> products = new ArrayList<>();
        if(productRepository.count()!=0) {
            for (int i = 0; i < 5; i++) {
                Product product = productRepository.findById((long) new Random().nextInt((int) productRepository.count())).get();
                products.add(product);
            }
        }
        return new ModelAndView("main",
                Map.of("products", products),
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
