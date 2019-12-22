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

public interface MainService {
    List<Product> SetRecommendations();

    List<Category> GetAllCategories();

    User GetUser(String login);

    List<Product> GetSearchProducts(String searchingProduct);

}

@Service
class MainServiceImpl implements MainService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Product> SetRecommendations() {
        List<Product> products = productRepository.findAll();
        List<Product> recommendations = new ArrayList<>();
        if(productRepository.count()!=0) {
            for (int i = 0; i < 3; i++) {
                int rand = new Random().nextInt(products.size());
                Product product = products.get(rand);
                recommendations.add(product);
            }
        }
        return recommendations;
    }

    @Override
    public List<Category> GetAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public User GetUser(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public List<Product> GetSearchProducts(String searchingProduct) {
        return productRepository.findByNameLike("%" + searchingProduct + "%");
    }

}
