package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.Product;
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

    //Products List Page
    @GetMapping("/list")
    ModelAndView list(){
        return new ModelAndView("catalog/list",
                Map.of("products", this.productRepository.findAll()),
                HttpStatus.OK);
    }

    //Product Page
    @GetMapping("{productId}")
    public ModelAndView product(@PathVariable Long productId) {
        return this.productRepository.findById(productId)
                .map(product -> new ModelAndView("catalog/product",
                        Map.of("product", product), HttpStatus.OK))
                .orElseGet(() -> new ModelAndView("errors/404",
                        Map.of("error", "Couldn't find a product"), HttpStatus.NOT_FOUND));
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
