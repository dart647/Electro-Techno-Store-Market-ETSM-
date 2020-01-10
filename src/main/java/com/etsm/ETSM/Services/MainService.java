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
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public interface MainService {
    List<Product> SetRecommendations();

    List<Category> GetAllCategories();

    User GetUser(String login);

    Page<Product> GetSearchProducts(String searchingProduct, String page, int maxProductsInPage, String sortParam, AttributeWrapper attributeWrapper);

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
    public Page<Product> GetSearchProducts(String searchingProduct, String page, int maxProductsInPage, String sortParam, AttributeWrapper attributeWrapper) {
        int pageNumber = Integer.parseInt(page);
        Pageable productPage = PageRequest.of(pageNumber, maxProductsInPage, Sort.by(sortParam));
        List<Product> productList = productRepository.findByNameLike(String.format("%%%s%%", searchingProduct));

        List<Product> newProductList;
        if(attributeWrapper.getAttrValues() != null){
            newProductList = new ArrayList<>();
            for (Product product:productList) {
                if(product.getProductAttrValue().containsAll(attributeWrapper.getAttrValues())){
                    newProductList.add(product);
                }
            }
        }else{
            newProductList = productList;
        }

        switch (sortParam){
            case "name":{
                newProductList.sort(Comparator.comparing(Product::getName));
                break;
            }
            case "price":{
                newProductList.sort(Comparator.comparing(Product::getPrice));
                break;
            }
            case "count":{
                newProductList.sort(Comparator.comparing(Product::getCount));
                break;
            }
        }

        List<Product> resultProductList = new ArrayList<>();
        for (int i = ((pageNumber) * maxProductsInPage); (i < ((pageNumber + 1) * maxProductsInPage) && i < newProductList.size()); i++) {
            resultProductList.add(newProductList.get(i));
        }

        Page<Product> products = new PageImpl<Product>(resultProductList, productPage, newProductList.size());
        return products;
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

