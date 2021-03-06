package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.Product;
import com.etsm.ETSM.Models.ProductAttrValue;
import com.etsm.ETSM.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("/search")
    public ModelAndView SearchPage( @ModelAttribute(name = "attributeParams")AttributeWrapper filterParams,
                                    @RequestParam(name = "page",defaultValue = "0") String page,
                                    @ModelAttribute(name = "searchProduct") String search,
                                    @RequestParam(name = "maxPrice", defaultValue = "100000") int maxPrice,
                                    @RequestParam(name = "categoryName", defaultValue = "all") String categoryName,
                                    @RequestParam(name = "sortParam", defaultValue = "name") String sort,
                                    Principal principal) {
        List<ProductAttrValue> attrValues = service.GetAllAttributes();

        headerService.setHeader(principal);
        List<Integer> pages = new ArrayList<>();
        int maxProductsInPage = 20;

        if(maxPrice <= 0){
            maxPrice = 100000;
        }

        Page<Product> products = service.GetSearchProducts(search, page,
                maxProductsInPage, maxPrice,
                sort, categoryName, filterParams);

        for (int i = 0; i < products.getTotalPages(); i++) {
            pages.add(i);
        }
        return new ModelAndView("/search",
                Map.of("categories", service.GetAllCategories(),
                        "attributesParams", filterParams,
                        "attributes", attrValues,
                        "sortParam", sort,
                        "categoryName", categoryName,
                        "maxPrice", maxPrice,
                        "searchProducts", products.getContent(),
                        "search", search,
                        "role", headerService.getHeaderRole(),
                        "pages", pages),
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
