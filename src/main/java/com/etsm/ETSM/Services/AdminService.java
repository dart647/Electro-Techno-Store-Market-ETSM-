package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.*;
import com.etsm.ETSM.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface AdminService{
    boolean addNewProduct(Product product, String subCategoryName);
    List<User> findUsers();
    Optional<User> findUserById(Long id);
    boolean addNewCategory(Category category);
    boolean addNewSubCategory(String CategoryName, SubCategory subCategory);
    boolean addNewAttribute(Attribute attribute);
}

@Service
class AdminServiceImpl implements AdminService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AttributeRepository attributeRepository;

    public boolean addNewProduct(Product product, String subCategoryName){
        if (productRepository.findByName(product.getName()).isPresent()) {
            return false;
        }
        SubCategory subCategory = subCategoryRepository.findByName(subCategoryName).get();
        Product newProduct = new Product();
        newProduct.setDescription(product.getDescription());
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setSubCategory_id(subCategory);
        productRepository.saveAndFlush(newProduct);
        return true;
    }

    @Override
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean addNewCategory(Category category) {
        if (categoryRepository.findByName(category.getName()).isPresent()) {
            return false;
        }
        Category newCategory = new Category();
        newCategory.setName(category.getName());
        categoryRepository.saveAndFlush(newCategory);
        return true;
    }

    @Override
    public boolean addNewSubCategory(String categoryName, SubCategory subCategory) {
        if (subCategoryRepository.findByName(subCategory.getName()).isPresent()) {
            return false;
        }
        Category category = categoryRepository.findByName(categoryName).get();
        SubCategory newSubCategory = new SubCategory();
        newSubCategory.setCategory_id(category);
        newSubCategory.setName(subCategory.getName());
        subCategoryRepository.saveAndFlush(newSubCategory);
        return true;
    }

    @Override
    public boolean addNewAttribute(Attribute attribute) {
        if (attributeRepository.findByName(attribute.getName()).isPresent()) {
            return false;
        }
        Attribute newAttribute = new Attribute();
        newAttribute.setName(attribute.getName());
        attributeRepository.saveAndFlush(newAttribute);
        return false;
    }
}
