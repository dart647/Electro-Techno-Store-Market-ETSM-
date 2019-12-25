package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.Product;
import com.etsm.ETSM.Services.HeaderService;
import com.etsm.ETSM.Services.MainService;
import com.etsm.ETSM.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Контроллер отвечающий за вывод страницы списка товаров

@Controller
@RequestMapping("/catalog")
public class ProductsController {

    private ProductService productService;

    private MainService mainService;

    private HeaderService headerService;

    public ProductsController(ProductService productService, MainService mainService, HeaderService headerService) {
        this.productService = productService;
        this.mainService = mainService;
        this.headerService = headerService;
    }

    //Products List Page
//    @GetMapping("/list")
//    ModelAndView GetAllProducts(Principal principal) {
//        User userForRole = new User();
//        userForRole.setRoles(new HashSet<Role>(Collections.singleton(Role.USER)));
//        if (principal != null) {
//            userForRole = (User) userService.loadUserByUsername(principal.getName());
//        }
//        return new ModelAndView("catalog/list",
//                Map.of("products", productService.findAllProducts(),
//                        "categories", mainService.GetAllCategories(),
//                        "role", userForRole.getRoles().toArray()[0].toString()),
//                HttpStatus.OK);
//    }

    @GetMapping("{categoryName}")
    public ModelAndView GetCategory(@PathVariable String categoryName, Principal principal) {
        headerService.setHeader(principal);
        return productService.findCategoryByName(categoryName)
                .map(product -> new ModelAndView("catalog/category",
                        Map.of("subCategories", productService.findSubCategoriesFromCategory(categoryName),
                                "categories", mainService.GetAllCategories(),
                                "currCategory", categoryName,
                                "role", headerService.getHeaderRole()),
                        HttpStatus.OK))
                .orElseGet(() -> new ModelAndView("errors/404",
                        Map.of("error", "Couldn't find a product"), HttpStatus.NOT_FOUND));
    }

    @GetMapping("category/{subCategoryName}")
    public ModelAndView GetSubCategory(@PathVariable String subCategoryName, Principal principal) {
        headerService.setHeader(principal);
        return productService.findSubCategoryByName(subCategoryName)
                .map(product -> new ModelAndView("/catalog/category/productsInSubCategory",
                        Map.of("minorCategories", productService.findMinorCategoriesFromSubCategory(subCategoryName),
                                "categories", mainService.GetAllCategories(),
                                "currSubCategory", productService.findSubCategoryByName(subCategoryName).get(),
                                "role", headerService.getHeaderRole()),
                        HttpStatus.OK))
                .orElseGet(() -> new ModelAndView("errors/404",
                        Map.of("error", "Couldn't find a sub Category"), HttpStatus.NOT_FOUND));
    }

    @GetMapping("category/subCategory/{minorCategoryName}")
    public ModelAndView GetMinorCategory(@PathVariable String minorCategoryName,
                                         @RequestParam(name = "page", defaultValue = "0") String page,
                                         Principal principal) {
        headerService.setHeader(principal);
        List<Integer> pages = new ArrayList<>();
        int maxProductsInPage = 20;

        List<Product> products = productService.findProductsFromMinorCategory(minorCategoryName, page, maxProductsInPage);
        if(products.size()!=0){
            for (int i = 0; i < Math.ceil((float)productService.GetAllProductsCountInMinorCategory(minorCategoryName) / products.size()); i++) {
                pages.add(i);
            }
        }
        return productService.findMinorCategoryByName(minorCategoryName)
                .map(product -> new ModelAndView("/catalog/category/subCategory/productsinMinCategory",
                        Map.of("products", products,
                                "currMinorCategory", productService.findMinorCategoryByName(minorCategoryName).get(),
                                "categories", mainService.GetAllCategories(),
                                "role", headerService.getHeaderRole(),
                                "pages", pages),
                        HttpStatus.OK))
                .orElseGet(() -> new ModelAndView("errors/404",
                        Map.of("error", "Couldn't find third layer category"), HttpStatus.NOT_FOUND));
    }

    //Product Page
    @GetMapping("details/{productName}")
    public ModelAndView GetProduct(@PathVariable String productName, Principal principal) {
        headerService.setHeader(principal);
        return productService.findProductByName(productName)
                .map(product -> new ModelAndView("catalog/category/subCategory/minorCategory/product",
                        Map.of("product", product,
                                "categories", mainService.GetAllCategories(),
                                "role", headerService.getHeaderRole()),
                        HttpStatus.OK))
                .orElseGet(() -> new ModelAndView("errors/404",
                        Map.of("error", "Couldn't find a product"), HttpStatus.NOT_FOUND));
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setHeaderService(HeaderService headerService) {
        this.headerService = headerService;
    }
}
