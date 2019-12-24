package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.*;
import com.etsm.ETSM.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface AdminService {
    boolean addNewProduct(Product product, String subCategoryName);

    List<User> findUsers();

    List<Attribute_Group> findAllAtrubutesGroups();

    Optional<User> findUserById(Long id);

    boolean addNewCategory(Category category);

    boolean addNewSubCategory(String CategoryName, SubCategory subCategory);

    boolean addNewMinorCategory(String subCategoryName, MinorCategory minorCategory);

    boolean addNewAttribute(Attribute attribute, String attribute_group);

    boolean addNewAttributeToProduct(String product, String attribute, ProductAttrValue oldProductAttrValue);
}

@Service
class AdminServiceImpl implements AdminService {

    private UserRepository userRepository;

    SubCategoryRepository subCategoryRepository;

    ProductRepository productRepository;

    CategoryRepository categoryRepository;

    AttributeRepository attributeRepository;

    MinorCategoryRepository minorCategoryRepository;

    AttributeGroupRepository attributeGroupRepository;

    ProductAttrValueRepository productAttrValueRepository;

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
        newProduct.setAttribute_groups(new ArrayList<>());
        newProduct.setMinorCategory_id(minorCategory);
        productRepository.saveAndFlush(newProduct);
        return true;
    }

    @Override
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Attribute_Group> findAllAtrubutesGroups() {
        return attributeGroupRepository.findAll();
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
    public boolean addNewAttribute(Attribute attribute, String attribute_group) {
        if (attributeRepository.findByName(attribute.getName()).isPresent()) {
            return false;
        }
        Attribute newAttribute = new Attribute();
        newAttribute.setName(attribute.getName());
        newAttribute.setAttribute_groups(attributeGroupRepository.findByName(attribute_group).get());
        attributeRepository.saveAndFlush(newAttribute);

        return false;
    }

    @Override
    public boolean addNewAttributeToProduct(String product, String attribute, ProductAttrValue oldProductAttrValue) {
        if(productAttrValueRepository.findByValue(oldProductAttrValue.getValue())!=null){
            Product UpProduct = productRepository.findByName(product).get();
            Attribute attributeForProduct = attributeRepository.findByName(attribute).get();

            if(!UpProduct.getAttribute_groups().equals(attributeForProduct.getAttribute_groups())){
                UpProduct.getAttribute_groups().add(attributeForProduct.getAttribute_groups());
            }
            ProductAttrValue newProductAttrValue = new ProductAttrValue();
            newProductAttrValue.setAttribute(attributeForProduct);
            newProductAttrValue.setProduct(UpProduct);
            newProductAttrValue.setValue(oldProductAttrValue.getValue());
            UpProduct.getProductAttrValue().add(newProductAttrValue);

            productRepository.save(UpProduct);
        }

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

    @Autowired
    public void setAttributeGroupRepository(AttributeGroupRepository attributeGroupRepository) {
        this.attributeGroupRepository = attributeGroupRepository;
    }

    @Autowired
    public void setProductAttrValueRepository(ProductAttrValueRepository productAttrValueRepository) {
        this.productAttrValueRepository = productAttrValueRepository;
    }
}
