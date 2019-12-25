package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.*;
import com.etsm.ETSM.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllProducts();

    List<SubCategory> findSubCategories();

    List<SubCategory> findSubCategoriesFromCategory(String categoryName);

    List<MinorCategory> findMinorCategoriesFromSubCategory(String subCategoryName);

    List<Product> findProductsFromMinorCategory(String minorCategoryName, String page, int maxProductsInPage);

    Optional<Product> findProductById(Long id);

    Optional<Product> findProductByName(String name);

    Optional<Category> findCategoryByName(String name);

    Optional<SubCategory> findSubCategoryByName(String name);

    Optional<MinorCategory> findMinorCategoryByName(String name);

    Optional<SubCategory> findSubCategoryById(Long id);

    List<Category> findCategories();

    List<MinorCategory> findMinorCategories();

    List<Attribute> findAttributes();

    List<Attribute> findAttributesPages(String page, int maxInPage);

    List<Sales> findSalesByUser(UserInfo userInfo);

    Optional<Sales> findSalesById(Long id);

    long GetAllProductsCountInMinorCategory(String minorCategoryName);

    long findAttributesCount();

    List<SubCategory> findAllSubCategories();

    List<Product> findAllProductsPages(String page, int maxInPage);

    long findAllProductsCount();

    boolean reserveItem(Product product, int quantity, boolean isReserve);
}

@Service
class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private SubCategoryRepository subCategoryRepository;
    private CategoryRepository categoryRepository;
    private MinorCategoryRepository minorCategoryRepository;
    private AttributeRepository attributeRepository;
    private SalesRepository salesRepository;

    @Autowired
    public void setSalesRepository(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    @Autowired
    public void setAttributeRepository(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }

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

    @Autowired
    public void setMinorCategoryRepository(MinorCategoryRepository minorCategoryRepository) {
        this.minorCategoryRepository = minorCategoryRepository;
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
    public List<MinorCategory> findMinorCategoriesFromSubCategory(String subCategoryName) {
        return findSubCategoryByName(subCategoryName).get().getMinorCategoryList();
    }

    @Override
    public List<Product> findProductsFromMinorCategory(String minorCategoryName, String page, int maxProductsInPage) {
        Pageable productPage = PageRequest.of(Integer.parseInt(page), maxProductsInPage, Sort.by("name"));
        return productRepository.findByMinorcategoryid(findMinorCategoryByName(minorCategoryName), productPage);
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
    public Optional<MinorCategory> findMinorCategoryByName(String name) {
        return minorCategoryRepository.findByName(name);
    }

    @Override
    public Optional<SubCategory> findSubCategoryById(Long id) {
        return subCategoryRepository.findById(id);
    }

    @Override
    public List<Category> findCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<MinorCategory> findMinorCategories() {
        return minorCategoryRepository.findAll();
    }

    @Override
    public List<Attribute> findAttributes() {
        return attributeRepository.findAll();
    }

    @Override
    public List<Attribute> findAttributesPages(String page, int maxInPage) {
        Pageable pageable = PageRequest.of(Integer.parseInt(page), maxInPage, Sort.by("name"));
        return attributeRepository.findAllByNameLike("%%", pageable);
    }

    @Override
    public List<Sales> findSalesByUser(UserInfo userInfo) {
        return salesRepository.findSalesByUserInfoId(userInfo);
    }

    @Override
    public Optional<Sales> findSalesById(Long id) {
        return salesRepository.findSalesById(id);
    }

    @Override
    public long GetAllProductsCountInMinorCategory(String minorCategoryName) {
        return findMinorCategoryByName(minorCategoryName).get().getProductList().size();
    }

    @Override
    public long findAttributesCount() {
        return attributeRepository.count();
    }

    @Override
    public List<SubCategory> findAllSubCategories() {
        return subCategoryRepository.findAll();
    }

    @Override
    public List<Product> findAllProductsPages(String page, int maxInPage) {
        Pageable pageable = PageRequest.of(Integer.parseInt(page), maxInPage, Sort.by("name"));
        return productRepository.findAllByNameLike("%%", pageable);
    }

    @Override
    public long findAllProductsCount() {
        return productRepository.count();
    }
    /*
    Функция резервирования товаров. Если переменная isReserve равна true, происходит резервация товаров.
    Если переменная равна false, отменяется бронирование товаров.
     */
    @Override
    public boolean reserveItem(Product product, int quantity, boolean isReserve) {
        Product targetProduct = productRepository.findById(product.getId()).orElse(null);
        if (targetProduct == null) {
            return false;
        }
        int currentQuantity = targetProduct.getCount();
        if (isReserve) {
            if (currentQuantity < quantity) {
                return false;
            }
            currentQuantity -= quantity;
        } else {
            currentQuantity += quantity;
        }
        targetProduct.setCount(currentQuantity);
        productRepository.saveAndFlush(targetProduct);
        return true;
    }
}