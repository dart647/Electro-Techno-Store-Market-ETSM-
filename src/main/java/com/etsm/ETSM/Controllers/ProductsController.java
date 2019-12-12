package com.etsm.ETSM.Controllers;

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
    ProductService service;

    //Products List Page
    @GetMapping("/list")
    ModelAndView GetAllProducts(){
        return new ModelAndView("catalog/list",
                Map.of("products", service.findAllProducts()),
                HttpStatus.OK);
    }

    //Product Page
    @GetMapping("category/subCategory/{productId}")
    public ModelAndView GetProduct(@PathVariable Long productId) {
        return service.findProductById(productId)
                .map(product -> new ModelAndView("catalog/category/subCategory/product",
                        Map.of("product", product), HttpStatus.OK))
                .orElseGet(() -> new ModelAndView("errors/404",
                        Map.of("error", "Couldn't find a product"), HttpStatus.NOT_FOUND));
    }

    @GetMapping("category")
    public ModelAndView GetCategory() {
        return new ModelAndView("catalog/category",
                Map.of("subCategories", service.findSubCategories()), HttpStatus.OK);
    }

    @GetMapping("category/{subCategoryId}")
    public ModelAndView GetSubCategory(@PathVariable Long subCategoryId) {
        return service.findSubCategoryById(subCategoryId)
                .map(product -> new ModelAndView("catalog/category/subCategory",
                        Map.of("products", service.findSubCategoryById(subCategoryId).get().getProductList()),
                        HttpStatus.OK))
                .orElseGet(() -> new ModelAndView("errors/404",
                        Map.of("error", "Couldn't find a sub Category"), HttpStatus.NOT_FOUND));
    }

}
