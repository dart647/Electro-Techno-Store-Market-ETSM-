package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.Product;
import com.etsm.ETSM.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public interface MainService{
    List<Product> SetRecommendations();
}

@Service
class MainServiceImpl implements MainService{
    @Autowired
    private ProductRepository productRepository;

    public List<Product> SetRecommendations(){
        List<Product> products = new ArrayList<>();
        if(productRepository.count()!=0) {
            for (int i = 0; i < 5; i++) {
                Product product = productRepository.findById((long) new Random().nextInt((int) productRepository.count())).get();
                products.add(product);
            }
        }
        return products;
    }
}
