package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.Product;
import com.etsm.ETSM.Models.ProductAttrValue;
import com.etsm.ETSM.Services.*;
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
    public ModelAndView MainPage(Principal principal) {
        headerService.setHeader(principal);
        List<Integer> pages = new ArrayList<>();
        String search = "";

        return new ModelAndView("/main",
                Map.of("categories", service.GetAllCategories(),
                        "search", search,
                        "role", headerService.getHeaderRole(),
                        "recommendations", service.SetRecommendations(),
                        "pages", pages),
                HttpStatus.OK);
    }

//    @GetMapping("/search")
//    public ModelAndView SearchPage(@RequestParam(name = "searchProduct", defaultValue = "") String search,
//                                   Principal principal) {
//        String page = "0";
//        String sort = "name";
//        List<ProductAttrValue> attrValues = service.GetAllAttributes();
//        AttributeWrapper attributeWrapper = new AttributeWrapper(new ArrayList<>());
//
//        headerService.setHeader(principal);
//        List<Integer> pages = new ArrayList<>();
//        int maxProductsInPage = 10;
//
//        List<Product> products = service.GetSearchProducts(search, page, maxProductsInPage, "name");
//        for (int i = 0; i < Math.ceil((float) service.GetSearchProductsCount(search) / maxProductsInPage); i++) {
//            pages.add(i);
//        }
//        return new ModelAndView("/search",
//                Map.of("categories", service.GetAllCategories(),
//                        "attributesParams", new AttributeWrapper(new ArrayList<>()),
//                        "attributes", attrValues,
//                        "searchProducts", products,
//                        "sortParam", sort,
//                        "search", search,
//                        "role", headerService.getHeaderRole(),
//                        "pages", pages,
//                        "page", page),
//                HttpStatus.OK);
//    }

    @PostMapping("/search")
    public ModelAndView SearchPage(@ModelAttribute("attributesParams") AttributeWrapper filterParams,
                             @ModelAttribute(name = "page") String page,
                             @ModelAttribute(name = "searchProduct") String search,
                             @ModelAttribute(name = "sortParam") String sort,
                             Principal principal) {
        if(page.equals("")){
            page = "0";
        }
        if(sort.equals("")){
            sort = "name";
        }
        List<ProductAttrValue> attrValues = service.GetAllAttributes();

        headerService.setHeader(principal);
        List<Integer> pages = new ArrayList<>();
        int maxProductsInPage = 10;

        List<Product> products = service.GetSearchProducts(search, page, maxProductsInPage, sort);

        for (int i = 0; i < Math.ceil((float) service.GetSearchProductsCount(search) / maxProductsInPage); i++) {
            pages.add(i);
        }
        return new ModelAndView("/search",
                Map.of("categories", service.GetAllCategories(),
                        "attributesParams", filterParams,
                        "attributes", attrValues,
                        "searchProducts", products,
                        "sortParam", sort,
                        "search", search,
                        "role", headerService.getHeaderRole(),
                        "pages", pages,
                        "page", page),
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
