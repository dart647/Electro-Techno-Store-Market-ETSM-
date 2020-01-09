package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.Category;
import com.etsm.ETSM.Models.Product;
import com.etsm.ETSM.Models.ProductAttrValue;
import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Repositories.CategoryRepository;
import com.etsm.ETSM.Repositories.ProductAttrValueRepository;
import com.etsm.ETSM.Repositories.ProductRepository;
import com.etsm.ETSM.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public interface MainService {
    List<Product> SetRecommendations();

    List<Category> GetAllCategories();

    User GetUser(String login);

    List<Product> GetSearchProducts(String searchingProduct, String page, int maxProductsInPage, String sortParam);

    long GetAllProductsCount();

    long GetSearchProductsCount(String name);

    List<ProductAttrValue> GetAllAttributes();
}

@Service
class MainServiceImpl implements MainService {
    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;

    private UserRepository userRepository;

    private ProductAttrValueRepository productAttrValueRepository;

    public List<Product> SetRecommendations() {
        List<Product> products = productRepository.findAll();
        List<Product> recommendations = new ArrayList<>();
        if (productRepository.count() != 0) {
            for (int i = 0; i < 10; i++) {
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
    public List<Product> GetSearchProducts(String searchingProduct, String page, int maxProductsInPage, String sortParam) {
        Pageable productPage = PageRequest.of(Integer.parseInt(page), maxProductsInPage, Sort.by(sortParam));
        return productRepository.findByNameLike(String.format("%%%s%%", searchingProduct), productPage);
    }

    @Override
    public long GetAllProductsCount() {
        return productRepository.count();
    }

    @Override
    public long GetSearchProductsCount(String name) {
        return productRepository.findByNameLike(String.format("%%%s%%", name)).size();
    }

    @Override
    public List<ProductAttrValue> GetAllAttributes() {
        List<ProductAttrValue> values = new ArrayList<>();
        for (ProductAttrValue value : productAttrValueRepository.findAll()) {
            if(!values.contains(value)){
                values.add(value);
            }
        }
        return values;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setProductAttrValueRepository(ProductAttrValueRepository productAttrValueRepository) {
        this.productAttrValueRepository = productAttrValueRepository;
    }
}

