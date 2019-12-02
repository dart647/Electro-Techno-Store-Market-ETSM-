package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.UUID;

// Контроллер отвечающий за вывод страницы списка товаров
@Controller
@RequestMapping("catalog/products")
public class ProductsController {
    public ProductRepository productRepository = new ProductRepository();

    @GetMapping("list")
    ModelAndView list(){
        return new ModelAndView("catalog/products/list",
                Map.of("products", this.productRepository.findAll()),
                HttpStatus.OK);
    }

    @GetMapping("{productId}")
    public ModelAndView product(@PathVariable UUID productId) {
        return this.productRepository.findOneById(productId)
                .map(product -> new ModelAndView("catalog/products/product",
                        Map.of("product", product), HttpStatus.OK))
                .orElseGet(() -> new ModelAndView("errors/404",
                        Map.of("error", "Couldn't find a product"), HttpStatus.NOT_FOUND));
    }
}
