package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.Category;
import com.etsm.ETSM.Models.Product;
import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Repositories.CategoryRepository;
import com.etsm.ETSM.Repositories.ProductRepository;
import com.etsm.ETSM.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public interface MainService{
    List<Product> SetRecommendations();
    List<Category> GetAllCategories();
    User GetUser(String login);
}

@Service
class MainServiceImpl implements MainService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Product> SetRecommendations(){
        List<Product> products = new ArrayList<>();
        if(productRepository.count()!=0) {
            for (int i = 0; i < 5; i++) {
                long rand = (long) new Random().nextInt((int) productRepository.count());
                Product product = productRepository.findById(rand + 1).get();
                products.add(product);
            }
        }
        return products;
    }

    @Override
    public List<Category> GetAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public User GetUser(String login) {
        return userRepository.findByLogin(login);
    }
}
