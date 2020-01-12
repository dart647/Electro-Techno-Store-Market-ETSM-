package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.*;
import com.etsm.ETSM.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    List<User> findUsersPages(String page, int maxInPage);

    long findUsersCount();

    void addNewAttributeGroup(Attribute_Group attributeGroup);
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
        newProduct.setMinorCategory_id(minorCategory);
        if(product.getId()!=0){
            product = productRepository.findById(product.getId()).get();
            newProduct.setId(product.getId());
            newProduct.setAttribute_groups(product.getAttribute_groups());
            newProduct.setImg(product.getImg());
            newProduct.setCount(product.getCount());
        }else{
            newProduct.setAttribute_groups(new ArrayList<>());
        }
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
        if(category.getId()!=0){
            category = categoryRepository.findById(category.getId()).get();
            newCategory.setId(category.getId());
            newCategory.setSubCategories(category.getSubCategories());
            newCategory.setCategoryIncome(category.getCategoryIncome());
        }else {
            CategoryIncome categoryIncome = new CategoryIncome();
            categoryIncome.setTotal(0);
            categoryIncome.setQuantity(0);
            newCategory.setCategoryIncome(categoryIncome);
        }
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
        newSubCategory.setName(subCategory.getName());
        newSubCategory.setCategory_id(category);
        if(subCategory.getId()!=0){
            subCategory = subCategoryRepository.findById(subCategory.getId()).get();
            newSubCategory.setId(subCategory.getId());
            newSubCategory.setMinorCategoryList(subCategory.getMinorCategoryList());
        }
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
        newMinorCategory.setName(minorCategory.getName());
        newMinorCategory.setSubcategory_id(subCategory);
        if(minorCategory.getId()!=0){
            minorCategory = minorCategoryRepository.findById(minorCategory.getId()).get();
            newMinorCategory.setId(minorCategory.getId());
            newMinorCategory.setProductList(minorCategory.getProductList());
        }
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
        if(attribute.getId()!=0){
            attribute = attributeRepository.findById(attribute.getId()).get();
            newAttribute.setId(attribute.getId());
            newAttribute.setAttribute_groups(attribute.getAttribute_groups());
        }else {
            newAttribute.setAttribute_groups(attributeGroupRepository.findByName(attribute_group).get());
        }
        attributeRepository.saveAndFlush(newAttribute);
        return false;
    }

    @Override
    public boolean addNewAttributeToProduct(String product, String attribute, ProductAttrValue oldProductAttrValue) {
        if (productAttrValueRepository.findByValue(oldProductAttrValue.getValue()) != null) {
            Product UpProduct = productRepository.findByName(product).get();
            Attribute attributeForProduct = attributeRepository.findByName(attribute).get();
            Boolean isProductHasAttrGr = false;

            for (Attribute_Group attribute_group : UpProduct.getAttribute_groups()) {
                if (attribute_group.equals(attributeForProduct.getAttribute_groups())) {
                    isProductHasAttrGr = true;
                    break;
                }
            }
            if (!isProductHasAttrGr) {
                UpProduct.getAttribute_groups().add(attributeForProduct.getAttribute_groups());
            }
            ProductAttrValue newProductAttrValue = new ProductAttrValue();
            newProductAttrValue.setAttribute(attributeForProduct);
            newProductAttrValue.setProduct(UpProduct);
            newProductAttrValue.setValue(oldProductAttrValue.getValue());
            UpProduct.getProductAttrValue().add(newProductAttrValue);

            productRepository.save(UpProduct);
        }

        return true;
    }

    @Override
    public List<User> findUsersPages(String page, int maxInPage) {
        Pageable pageable = PageRequest.of(Integer.parseInt(page), maxInPage, Sort.by("login"));
        return userRepository.findAllByLoginLike("%%", pageable);
    }

    @Override
    public long findUsersCount() {
        return userRepository.count();
    }

    @Override
    public void addNewAttributeGroup(Attribute_Group attributeGroup) {
        if (attributeGroupRepository.findByName(attributeGroup.getName()).isPresent()) {
            return;
        }
        Attribute_Group newAttributeGroup = new Attribute_Group();
        newAttributeGroup.setName(attributeGroup.getName());
        if(attributeGroup.getId()!=0){
            attributeGroup = attributeGroupRepository.findById(attributeGroup.getId()).get();
            newAttributeGroup.setId(attributeGroup.getId());
            newAttributeGroup.setProduct_id(attributeGroup.getProduct_id());
            newAttributeGroup.setAttribute_id(attributeGroup.getAttribute_id());
        }else {
            newAttributeGroup.setProduct_id(new ArrayList<>());
            newAttributeGroup.setAttribute_id(new ArrayList<>());
        }
        attributeGroupRepository.saveAndFlush(newAttributeGroup);
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
