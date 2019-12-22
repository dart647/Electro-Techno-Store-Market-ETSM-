package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.*;
import com.etsm.ETSM.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    boolean addNewProduct(Product product, String subCategoryName);

    List<User> findUsers();

    Optional<User> findUserById(Long id);

    boolean addNewCategory(Category category);

    boolean addNewSubCategory(String CategoryName, SubCategory subCategory);

    boolean addNewMinorCategory(String subCategoryName, MinorCategory minorCategory);

    boolean addNewAttribute(Attribute attribute);
}

@Service
class AdminServiceImpl implements AdminService {

    private UserRepository userRepository;

    SubCategoryRepository subCategoryRepository;

    ProductRepository productRepository;

    CategoryRepository categoryRepository;

    AttributeRepository attributeRepository;

    MinorCategoryRepository minorCategoryRepository;

    public boolean addNewProduct(Product product, String minorCategoryName) {
        if (productRepository.findByName(product.getName()).isPresent()) {
            return false;
        }
        if (product.getPrice() < 0) {
            return false;
        }
        MinorCategory minorCategory = minorCategoryRepository.findByName(minorCategoryName).get();
        Product newProduct = new Product();
        newProduct.setDescription(product.getDescription());
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setMinorCategory_id(minorCategory);
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
    public boolean addNewMinorCategory(String subCategoryName, MinorCategory minorCategory) {
        if (minorCategoryRepository.findByName(minorCategory.getName()).isPresent()) {
            return false;
        }
        SubCategory subCategory = subCategoryRepository.findByName(subCategoryName).get();
        MinorCategory newMinorCategory = new MinorCategory();
        newMinorCategory.setSubcategory_id(subCategory);
        newMinorCategory.setName(minorCategory.getName());
        minorCategoryRepository.saveAndFlush(newMinorCategory);
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

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setSubCategoryRepository(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
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
    public void setAttributeRepository(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }

    @Autowired
    public void setMinorCategoryRepository(MinorCategoryRepository minorCategoryRepository) {
        this.minorCategoryRepository = minorCategoryRepository;
    }
}
