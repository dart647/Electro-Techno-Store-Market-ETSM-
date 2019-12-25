package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.Product;
import com.etsm.ETSM.Services.HeaderService;
import com.etsm.ETSM.Services.MainService;
import com.etsm.ETSM.Services.ShoppingCartService;
import com.etsm.ETSM.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Это главный контроллер для авторизации и главной страницы
// можно задавать @RequestParam, тогда при разных обращениях к странице получаем параметр (?name=имя), с которым можем работать
// в return указывает view и model (можно без нее)

@Controller
@RequestMapping("/")
public class MainController {

    private MainService service;
    private ShoppingCartService shoppingCartService;
    private HeaderService headerService;

    @Autowired
    public void setHeaderService(HeaderService headerService) {
        this.headerService = headerService;
    }

    @Autowired
    public void setService(MainService service) {
        this.service = service;
    }

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    public MainController(MainService service, UserService userService) {
        this.service = service;

    }

    //Main Page
    @GetMapping("/")
    public ModelAndView MainPage(@RequestParam(name = "page", defaultValue = "0") String page, Principal principal) {
        headerService.setHeader(principal);
        String search = "";
        List<Integer> pages = new ArrayList<>();
        int maxProductsInPage = 20;

        List<Product> products = service.GetSearchProducts("", page, maxProductsInPage);
        for (int i = 0; i < Math.ceil((float)service.GetAllProductsCount() / maxProductsInPage); i++) {
            pages.add(i);
        }
        return new ModelAndView("/main",
                Map.of("categories", service.GetAllCategories(),
                        "searchProducts", products,
                        "search", search,
                        "role", headerService.getHeaderRole(),
                        "recommendations", service.SetRecommendations(),
                        "pages", pages),
                HttpStatus.OK);
    }

    @PostMapping("/")
    public ModelAndView MainPageWithSearch(@RequestParam(name = "page", defaultValue = "0") String page,
                                           @ModelAttribute("searching") String searching, Principal principal) {
        headerService.setHeader(principal);
        List<Integer> pages = new ArrayList<>();
        int maxProductsInPage = 20;
        List<Product> products = service.GetSearchProducts(searching, page, maxProductsInPage);
        for (int i = 0; i < Math.ceil((float)service.GetAllProductsCount() / maxProductsInPage); i++) {
            pages.add(i);
        }
        return new ModelAndView("/main",
                Map.of("categories", service.GetAllCategories(),
                        "searchProducts", products,
                        "search", searching,
                        "role", headerService.getHeaderRole(),
                        "recommendations", service.SetRecommendations(),
                        "pages", new ArrayList<>()),
                HttpStatus.OK);
    }

    //User Cabinet Page
    @GetMapping("/user")
    public ModelAndView UserCabinet(Principal principal) {
        headerService.setHeader(principal);
        return new ModelAndView("/auth/userCabinet",
                Map.of("user", headerService.getUser(),
                        "role", headerService.getHeaderRole(),
                        "categories", headerService.getHeaderCategories()),
                HttpStatus.OK);
    }

    //Admin panel page
    @GetMapping("/admin")
    public ModelAndView Admin(Principal principal) {
        headerService.setHeader(principal);
        return new ModelAndView("/auth/admin",
                Map.of("role", headerService.getHeaderRole(),
                        "categories", headerService.getHeaderCategories()),
                HttpStatus.OK);
    }

    @GetMapping("/about")
    public ModelAndView About(Principal principal) {
        headerService.setHeader(principal);
        return new ModelAndView("/about",
                Map.of("role", headerService.getHeaderRole(),
                        "categories", headerService.getHeaderCategories()),
                HttpStatus.OK);
    }

    @GetMapping("/feedback")
    public ModelAndView Feedback(Principal principal) {
        headerService.setHeader(principal);
        return new ModelAndView("/feedback",
                Map.of("role", headerService.getHeaderRole(),
                        "categories", headerService.getHeaderCategories()),
                HttpStatus.OK);
    }

    @GetMapping("/ourAddresses")
    public ModelAndView Addresses(Principal principal) {
        headerService.setHeader(principal);
        return new ModelAndView("/ourAddresses",
                Map.of("role", headerService.getHeaderRole(),
                        "categories", headerService.getHeaderCategories()),
                HttpStatus.OK);
    }

    @GetMapping("/login")
    public String loginPage(Model model, Principal principal) {
        headerService.setHeader(principal);
        model.addAttribute("view", "login");
        model.addAttribute("title", "Вход");
        model.addAttribute("role", headerService.getHeaderRole());
        model.addAttribute("categories", headerService.getHeaderCategories());
        return "login";
    }

    @GetMapping("/buyProduct")
    public String addToCart(@RequestParam(value = "code") String code, HttpSession session) {
        shoppingCartService.addItemToCart(code, session);
        return "redirect:/orderSuggestion";
    }

    @GetMapping("/orderSuggestion")
    public ModelAndView getCartPage(Principal principal) {
        headerService.setHeader(principal);
        return new ModelAndView("/catalog/orderSuggestion",
                Map.of("role", headerService.getHeaderRole()),
                HttpStatus.OK);
    }
}
