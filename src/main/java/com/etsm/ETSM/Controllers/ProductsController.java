package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.Product;
import com.etsm.ETSM.Repositories.CategoryRepository;
import com.etsm.ETSM.Repositories.ProductRepository;
import com.etsm.ETSM.Repositories.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

// Контроллер отвечающий за вывод страницы списка товаров

@Controller
@RequestMapping("/catalog")
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    //Products List Page
    @GetMapping("/list")
    ModelAndView GetAllProducts(){
        return new ModelAndView("catalog/list",
                Map.of("products", this.productRepository.findAll()),
                HttpStatus.OK);
    }

    //Product Page
    @GetMapping("category/subCategory/{productId}")
    public ModelAndView GetProduct(@PathVariable Long productId) {
        return this.productRepository.findById(productId)
                .map(product -> new ModelAndView("catalog/category/subCategory/product",
                        Map.of("product", product), HttpStatus.OK))
                .orElseGet(() -> new ModelAndView("errors/404",
                        Map.of("error", "Couldn't find a product"), HttpStatus.NOT_FOUND));
    }

    @GetMapping("category")
    public ModelAndView GetCategory() {
        return new ModelAndView("catalog/category",
                Map.of("subCategories", subCategoryRepository.findAll()), HttpStatus.OK);
    }

    @GetMapping("category/{subCategoryId}")
    public ModelAndView GetSubCategory(@PathVariable Long subCategoryId) {
        return this.subCategoryRepository.findById(subCategoryId)
                .map(product -> new ModelAndView("catalog/category/subCategory",
                        Map.of("products", subCategoryRepository.findById(subCategoryId).get().getProductList()),
                        HttpStatus.OK))
                .orElseGet(() -> new ModelAndView("errors/404",
                        Map.of("error", "Couldn't find a sub Category"), HttpStatus.NOT_FOUND));
    }

    @GetMapping("/addProduct")
    public ModelAndView AddProduct(){
        Product product = new Product();
        return new ModelAndView("catalog/addProduct",
                Map.of("product", product),
                HttpStatus.OK);
    }

    @PostMapping("/addProduct")
    public ModelAndView AddProduct(@ModelAttribute Product product){
        Product newProduct = new Product();
        newProduct.setDescription(product.getDescription());
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setSubCategory_id(this.subCategoryRepository.getOne(1l));
        productRepository.saveAndFlush(newProduct);

        return new ModelAndView("catalog/list",
                Map.of("products", this.productRepository.findAll()),
                HttpStatus.OK);
    }
}
