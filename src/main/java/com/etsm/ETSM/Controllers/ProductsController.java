package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Services.MainService;
import com.etsm.ETSM.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

// Контроллер отвечающий за вывод страницы списка товаров

@Controller
@RequestMapping("/catalog")
public class ProductsController {
    @Autowired
    ProductService productService;
    @Autowired
    MainService mainService;

    //Products List Page
    @GetMapping("/list")
    ModelAndView GetAllProducts() {
        return new ModelAndView("catalog/list",
                Map.of("products", productService.findAllProducts(),
                        "categories", mainService.GetAllCategories()),
                HttpStatus.OK);
    }

    @GetMapping("{categoryName}")
    public ModelAndView GetCategory(@PathVariable String categoryName) {
        return productService.findCategoryByName(categoryName)
                .map(product -> new ModelAndView("catalog/category",
                        Map.of("subCategories", productService.findSubCategoriesFromCategory(categoryName),
                                "categories", mainService.GetAllCategories()), HttpStatus.OK))
                .orElseGet(() -> new ModelAndView("errors/404",
                        Map.of("error", "Couldn't find a product"), HttpStatus.NOT_FOUND));
    }

    @GetMapping("category/{subCategoryName}")
    public ModelAndView GetSubCategory(@PathVariable String subCategoryName) {
        return productService.findSubCategoryByName(subCategoryName)
                .map(product -> new ModelAndView("/catalog/category/productsInSubCategory",
                        Map.of("minorCategories", productService.findMinorCategoriesFromSubCategory(subCategoryName),
                                "categories", mainService.GetAllCategories()),
                        HttpStatus.OK))
                .orElseGet(() -> new ModelAndView("errors/404",
                        Map.of("error", "Couldn't find a sub Category"), HttpStatus.NOT_FOUND));
    }

    @GetMapping("category/subCategory/{minorCategoryName}")
    public ModelAndView GetMinorCategory(@PathVariable String minorCategoryName) {
        return productService.findMinorCategoryByName(minorCategoryName)
                .map(product -> new ModelAndView("/catalog/category/subCategory/productsinMinCategory",
                        Map.of("products", productService.findProductsFromMinorCategory(minorCategoryName),
                                "categories", mainService.GetAllCategories()),
                        HttpStatus.OK))
                .orElseGet(() -> new ModelAndView("errors/404",
                        Map.of("error", "Couldn't find third layer category"), HttpStatus.NOT_FOUND));
    }

    //Product Page
    @GetMapping("category/subCategory/minorCategory/{productName}")
    public ModelAndView GetProduct(@PathVariable String productName) {
        return productService.findProductByName(productName)
                .map(product -> new ModelAndView("catalog/category/subCategory/product",
                        Map.of("product", product,
                                "categories", mainService.GetAllCategories()), HttpStatus.OK))
                .orElseGet(() -> new ModelAndView("errors/404",
                        Map.of("error", "Couldn't find a product"), HttpStatus.NOT_FOUND));
    }
}
