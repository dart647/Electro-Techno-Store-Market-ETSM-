package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.Category;
import com.etsm.ETSM.Models.Product;
import com.etsm.ETSM.Models.SubCategory;
import com.etsm.ETSM.Repositories.CategoryRepository;
import com.etsm.ETSM.Repositories.ProductRepository;
import com.etsm.ETSM.Repositories.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllProducts();
    List<SubCategory> findSubCategories();
    List<SubCategory> findSubCategoriesFromCategory(String categoryName);
    List<Product> findProductsFromSubCategory(String subCategoryName);
    Optional<Product> findProductById(Long id);
    Optional<Product> findProductByName(String name);
    Optional<Category> findCategoryByName(String name);
    Optional<SubCategory> findSubCategoryByName(String name);
    Optional<SubCategory> findSubCategoryById(Long id);
    List<Category> findCategories();
}

@Service
class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private SubCategoryRepository subCategoryRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Autowired
    public void setSubCategoryRepository(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }
    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<SubCategory> findSubCategories() {
        return subCategoryRepository.findAll();
    }

    @Override
    public List<SubCategory> findSubCategoriesFromCategory(String categoryName) {
        return findCategoryByName(categoryName).get().getSubCategories();
    }

    @Override
    public List<Product> findProductsFromSubCategory(String subCategoryName) {
        return findSubCategoryByName(subCategoryName).get().getProductList();
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> findProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Optional<Category> findCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public Optional<SubCategory> findSubCategoryByName(String name) {
        return subCategoryRepository.findByName(name);
    }

    @Override
    public Optional<SubCategory> findSubCategoryById(Long id) {
        return subCategoryRepository.findById(id);
    }

    @Override
    public List<Category> findCategories() {
        return categoryRepository.findAll();
    }


}