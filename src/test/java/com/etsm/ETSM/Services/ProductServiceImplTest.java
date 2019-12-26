package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.*;
import com.etsm.ETSM.Repositories.*;
import org.hibernate.mapping.Subclass;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.Attr;

import javax.management.Attribute;
import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class ProductServiceImplTest {

    @Test
    public void findAllProductsTest() {
        ProductServiceImpl productService = new ProductServiceImpl();
        ProductRepository productRepositorymock = mock(ProductRepository.class);

        List <Product> products = new ArrayList<>();

        Mockito.when(productRepositorymock.findAll()).thenReturn(products);
        productService.setProductRepository(productRepositorymock);

        Assert.assertEquals(productService.findAllProducts(),products);
    }

    @Test
    public void findSubCategoriesTest(){
        ProductServiceImpl productService = new ProductServiceImpl();
        SubCategoryRepository subCategoryRepositoryMock = mock(SubCategoryRepository.class);
        List <SubCategory> subCategories = new ArrayList<>();

        Mockito.when(subCategoryRepositoryMock.findAll()).thenReturn(subCategories);
        productService.setSubCategoryRepository(subCategoryRepositoryMock);

        Assert.assertEquals(productService.findSubCategories(),subCategories);
    }

    @Test
    public void findSubCategoriesFromCategoryTest() {
        ProductServiceImpl productServiceImpl = new ProductServiceImpl();
        CategoryRepository categoryRepositoryMock = mock(CategoryRepository.class);
        SubCategoryRepository subCategoryRepositoryMock = mock(SubCategoryRepository.class);
        ProductRepository productRepositoryMock = mock(ProductRepository.class);


        productServiceImpl.setCategoryRepository(categoryRepositoryMock);
        productServiceImpl.setSubCategoryRepository(subCategoryRepositoryMock);
        productServiceImpl.setProductRepository(productRepositoryMock);

        SubCategory subCategory = new SubCategory();
        subCategory.setName("test");
       subCategory.setId(1L);
        List <SubCategory> subCategories = List.of(subCategory);

        Category category = new Category();
        category.setSubCategories(subCategories);
        category.setId(1L);
        category.setName("test");


        Optional<Category> OptionalCategory = Optional.of(category);


       // String name = productServiceImpl.findCategoryByName(OptionalCategory).get().getName();

        Mockito.when(categoryRepositoryMock.findByName(OptionalCategory.get().getName())).thenReturn(OptionalCategory);



        Assert.assertEquals(subCategories,productServiceImpl.findSubCategoriesFromCategory(OptionalCategory.get().getName()));

    }

    @Test
    public void findProductByIdTest(){

        ProductServiceImpl productServiceImpl = new ProductServiceImpl();
        ProductRepository productRepositoryMock = mock(ProductRepository.class);
        productServiceImpl.setProductRepository(productRepositoryMock);

        Product product = new Product();
        product.setId(1L);
        product.setName("test");


        Optional<Product> OptProd = Optional.of(product);
         Mockito.when(productRepositoryMock.findById(product.getId())).thenReturn(OptProd);
         Assert.assertEquals(productServiceImpl.findProductById(product.getId()),OptProd);


    }

    @Test
    public void findProductByNameTest() {

        ProductServiceImpl productServiceImpl = new ProductServiceImpl();
        ProductRepository productRepositoryMock = mock(ProductRepository.class);
        productServiceImpl.setProductRepository(productRepositoryMock);

        Product product = new Product();
        product.setId(1L);
        product.setName("test");

        Optional<Product> OptProd = Optional.of(product);
        Mockito.when(productRepositoryMock.findByName(product.getName())).thenReturn(OptProd);
        Assert.assertEquals(productServiceImpl.findProductByName(product.getName()),OptProd);

    }

    @Test
    public void findCategoryByNameTest(){
        ProductServiceImpl productServiceImpl = new ProductServiceImpl();
        CategoryRepository categoryRepositoryMock = mock(CategoryRepository.class);
        productServiceImpl.setCategoryRepository(categoryRepositoryMock);

        Category category = new Category();
        category.setId(1L);
        category.setName("test");

        Optional OptCat = Optional.of(category);

        Mockito.when(categoryRepositoryMock.findByName(category.getName())).thenReturn(OptCat);
        Assert.assertEquals(productServiceImpl.findCategoryByName(category.getName()),OptCat);


    }

    @Test
    public void findSubCategoryByNameTest() {
        ProductServiceImpl productServiceImpl = new ProductServiceImpl();
        SubCategoryRepository subCategoryRepositoryMock = mock(SubCategoryRepository.class);
        CategoryRepository categoryRepositoryMock = mock(CategoryRepository.class);
        productServiceImpl.setSubCategoryRepository(subCategoryRepositoryMock);
        productServiceImpl.setCategoryRepository(categoryRepositoryMock);

        SubCategory subcategory = new SubCategory();
        subcategory.setId(1L);
        subcategory.setName("test");
        List<SubCategory> subCategories = List.of(subcategory);

        Category category = new Category();
        category.setId(1L);
        category.setName("test");
        category.setSubCategories(subCategories);




        Optional OptSubCat = Optional.of(subcategory);

        Optional OptCat = Optional.of(category);



        Mockito.when(subCategoryRepositoryMock.findByName(subcategory.getName())).thenReturn(OptSubCat);
        Mockito.when(categoryRepositoryMock.findByName(category.getName())).thenReturn(OptCat);
        Assert.assertEquals(productServiceImpl.findSubCategoryByName(subcategory.getName()), OptSubCat);

    }

    @Test
    public void findMinorCategoryByNameTest(){

        ProductServiceImpl productServiceImpl = new ProductServiceImpl();
        SubCategoryRepository subCategoryRepositoryMock = mock(SubCategoryRepository.class);
        CategoryRepository categoryRepositoryMock = mock(CategoryRepository.class);
        MinorCategoryRepository minorCategoryRepositoryMock = mock(MinorCategoryRepository.class);
        productServiceImpl.setSubCategoryRepository(subCategoryRepositoryMock);
        productServiceImpl.setCategoryRepository(categoryRepositoryMock);
        productServiceImpl.setMinorCategoryRepository(minorCategoryRepositoryMock);



        MinorCategory minorCategory = new MinorCategory();
        minorCategory.setId(1L);
        minorCategory.setName("test");
        List<MinorCategory> minorCategories = List.of(minorCategory);


        SubCategory subcategory = new SubCategory();
        subcategory.setId(1L);
        subcategory.setName("test");
        List<SubCategory> subCategories = List.of(subcategory);
        subcategory.setMinorCategoryList(minorCategories);

        Category category = new Category();
        category.setId(1L);
        category.setName("test");
        category.setSubCategories(subCategories);




        Optional OptSubCat = Optional.of(subcategory);

        Optional OptCat = Optional.of(category);

        Optional OptMin = Optional.of(minorCategories);



        Mockito.when(subCategoryRepositoryMock.findByName(subcategory.getName())).thenReturn(OptSubCat);
        Mockito.when(categoryRepositoryMock.findByName(category.getName())).thenReturn(OptCat);
        Mockito.when(minorCategoryRepositoryMock.findByName(minorCategory.getName())).thenReturn(OptMin);
        Assert.assertEquals(productServiceImpl.findMinorCategoryByName(minorCategory.getName()), OptMin);



    }

    @Test
    public void findSubCategoryById() {
        ProductServiceImpl productServiceImpl = new ProductServiceImpl();
        SubCategoryRepository subCategoryRepositoryMock = mock(SubCategoryRepository.class);
        CategoryRepository categoryRepositoryMock = mock(CategoryRepository.class);
        productServiceImpl.setSubCategoryRepository(subCategoryRepositoryMock);
        productServiceImpl.setCategoryRepository(categoryRepositoryMock);

        SubCategory subcategory = new SubCategory();
        subcategory.setId(1L);
        subcategory.setName("test");
        List<SubCategory> subCategories = List.of(subcategory);

        Category category = new Category();
        category.setId(1L);
        category.setName("test");
        category.setSubCategories(subCategories);




        Optional OptSubCat = Optional.of(subcategory);

        Optional OptCat = Optional.of(category);



        Mockito.when(subCategoryRepositoryMock.findById(subcategory.getId())).thenReturn(OptSubCat);
        Mockito.when(categoryRepositoryMock.findById(category.getId())).thenReturn(OptCat);
        Assert.assertEquals(productServiceImpl.findSubCategoryById(subcategory.getId()), OptSubCat);

    }

    @Test
    public void findCategoriesTest(){
        ProductServiceImpl productService = new ProductServiceImpl();
        CategoryRepository categoryRepositorymock = mock(CategoryRepository.class);

        List <Category> categories = new ArrayList<>();

        Mockito.when(categoryRepositorymock.findAll()).thenReturn(categories);
        productService.setCategoryRepository(categoryRepositorymock);

        Assert.assertEquals(productService.findCategories(),categories);


    }

    @Test
    public void findMinorCategoriesTest(){
        ProductServiceImpl productService = new ProductServiceImpl();
        SubCategoryRepository subCategoryRepositoryMock = mock(SubCategoryRepository.class);
        MinorCategoryRepository minorCategoryRepositoryMock = mock(MinorCategoryRepository.class);
        productService.setSubCategoryRepository(subCategoryRepositoryMock);
        productService.setMinorCategoryRepository(minorCategoryRepositoryMock);
        List <MinorCategory> minorCategories = new ArrayList<>();

        Mockito.when(minorCategoryRepositoryMock.findAll()).thenReturn(minorCategories);


        Assert.assertEquals(productService.findMinorCategories(),minorCategories);
    }

    @Test
    public void findAttributesTest() {
        ProductServiceImpl productService = new ProductServiceImpl();
        AttributeRepository attributeRepositoryMock = mock(AttributeRepository.class);
        productService.setAttributeRepository(attributeRepositoryMock);

        Attribute attributes = new Attribute("name",1);
        Optional<Attribute> OptAtt = Optional.of(attributes);
        List<Attribute> attributeList = new ArrayList<>();

        Assert.assertEquals(productService.findAttributes(),attributeList);

    }

    @Test
    public void findAttributesPagesTest(){

        ProductServiceImpl productServiceImpl = new ProductServiceImpl();
        AttributeRepository attributeRepositoryMock = mock(AttributeRepository.class);
        productServiceImpl.setAttributeRepository(attributeRepositoryMock);

        String page = "1";
        Integer maxInPage = 10;
        String name = "test";
        Attribute attribute = new Attribute(name,null);
        List<Attribute> attributeList = List.of(attribute);


        Pageable pageable = PageRequest.of(Integer.parseInt(page), maxInPage, Sort.by("name"));
        Mockito.when(attributeRepositoryMock.findAllByNameLike("%%",pageable)).thenReturn(null);
        Assert.assertEquals(productServiceImpl.findAttributesPages(page,maxInPage),null);
    }

    @Test
    public void findSalesByUserTest() {

        ProductServiceImpl productService = new ProductServiceImpl();
        SalesRepository salesRepositoryMock = mock(SalesRepository.class);
        productService.setSalesRepository(salesRepositoryMock);

        List<Sales> ListSales= new ArrayList<>();
        UserInfo userInfo = new UserInfo();

        Assert.assertEquals(productService.findSalesByUser(userInfo),ListSales);
    }

    @Test
    public void findSalesByIdTest() {
        ProductServiceImpl productService = new ProductServiceImpl();
        SalesRepository salesRepositoryMock = mock(SalesRepository.class);
        productService.setSalesRepository(salesRepositoryMock);

        Sales sales = new Sales();
        sales.setId(1L);
        List<Sales> ListSales= List.of(sales);
        UserInfo userInfo = new UserInfo();

        Optional<Sales> OptSales= Optional.of(sales);

        Mockito.when(productService.findSalesById(sales.getId())).thenReturn(OptSales);

        Assert.assertEquals(productService.findSalesById(sales.getId()),OptSales);

    }

    @Test
    public void findMinorCategoriesFromSubCategoryTest(){
        ProductServiceImpl productServiceImpl = new ProductServiceImpl();

        SubCategoryRepository subCategoryRepositoryMock = mock(SubCategoryRepository.class);
        MinorCategoryRepository minorCategoryRepositoryMock = mock(MinorCategoryRepository.class);
        ProductRepository productRepositoryMock = mock(ProductRepository.class);


        productServiceImpl.setMinorCategoryRepository(minorCategoryRepositoryMock);
        productServiceImpl.setSubCategoryRepository(subCategoryRepositoryMock);
        productServiceImpl.setProductRepository(productRepositoryMock);

        MinorCategory minorCategory = new MinorCategory();
        minorCategory.setName("test");
        minorCategory.setId(1L);
        List<MinorCategory> minorCategories = List.of(minorCategory);

        SubCategory subCategory = new SubCategory();
        subCategory.setName("test");
        subCategory.setId(1L);
        subCategory.setMinorCategoryList(minorCategories);
        List <SubCategory> subCategories = List.of(subCategory);

        Category category = new Category();
        category.setSubCategories(subCategories);
        category.setId(1L);
        category.setName("test");


        Optional<Category> OptionalCategory = Optional.of(category);
        Optional<SubCategory> OptionalSubCategory = Optional.of(subCategory);


        // String name = productServiceImpl.findCategoryByName(OptionalCategory).get().getName();

        Mockito.when(subCategoryRepositoryMock.findByName(OptionalSubCategory.get().getName())).thenReturn(OptionalSubCategory);



        Assert.assertEquals(minorCategories,productServiceImpl.findMinorCategoriesFromSubCategory(OptionalSubCategory.get().getName()));




    }

    @Test
    public void findProductsFromMinorCategoryTest (){

        ProductServiceImpl productServiceImpl = new ProductServiceImpl();
        MinorCategoryRepository minorCategoryRepositoryMock = mock(MinorCategoryRepository.class);
        productServiceImpl.setMinorCategoryRepository(minorCategoryRepositoryMock);
        ProductRepository productRepositoryMock = mock(ProductRepository.class);
        productServiceImpl.setProductRepository(productRepositoryMock);

        Product product = new Product();
        product.setId(1L);
        List<Product> productList = List.of(product);
        Optional<Product> OptProduct = Optional.of(product);

        MinorCategory minorCategory = new MinorCategory();
        minorCategory.setId(1L);
        minorCategory.setName("test");
        minorCategory.setProductList(productList);
        Optional<MinorCategory> OptMin = Optional.of(minorCategory);

        String page = "1";
        int maxproducts = 10;
        Pageable productPage = PageRequest.of(Integer.parseInt(page), maxproducts, Sort.by("name"));

        Mockito.when(minorCategoryRepositoryMock.findByName(minorCategory.getName())).thenReturn(OptMin);
        Mockito.when(productRepositoryMock.findByMinorcategoryid(OptMin,productPage)).thenReturn(productList);

        Assert.assertEquals(productServiceImpl.findProductsFromMinorCategory(minorCategory.getName(),page,maxproducts),productList);




       // Assert.assertEquals(productServiceImpl.findProductsFromMinorCategory(minorCategory.getName(),page,maxproducts));







    }

    @Test
    public void GetAllProductsCountInMinorCategorytest(){

        ProductServiceImpl productServiceImpl = new ProductServiceImpl();
        SubCategoryRepository subCategoryMock = mock(SubCategoryRepository.class);
        productServiceImpl.setSubCategoryRepository(subCategoryMock);
        MinorCategory minorCategory = new MinorCategory();
        minorCategory.setName("test");



        List<MinorCategory> minorCategoryList = List.of(minorCategory);
        SubCategory subCategory = new SubCategory();
        subCategory.setId(1L);
        subCategory.setMinorCategoryList(minorCategoryList);

        Product product = new Product();
        product.setId(1L);
        List<Product> productList = List.of(product);
        minorCategory.setProductList(productList);
       // minorCategory.setSubcategory_id(subCategory);
        Optional<MinorCategory> OptMinCat = Optional.of(minorCategory);

        Optional<Product> OptProd = Optional.of(product);

        MinorCategoryRepository minorCategoryRepositoryMock = mock(MinorCategoryRepository.class);
        productServiceImpl.setMinorCategoryRepository(minorCategoryRepositoryMock);
        Mockito.when(minorCategoryRepositoryMock.findByName(minorCategory.getName())).thenReturn(OptMinCat);
       OptMinCat = productServiceImpl.findMinorCategoryByName(minorCategory.getName());
      MinorCategory minorCategorytest = OptMinCat.get();

        Assert.assertEquals(productServiceImpl.GetAllProductsCountInMinorCategory(minorCategory.getName()),1);
    }

    @Test
    public void findAttributesCountTest(){
        ProductServiceImpl productService = new ProductServiceImpl();
        AttributeRepository attributeRepositoryMock = mock(AttributeRepository.class);
        productService.setAttributeRepository(attributeRepositoryMock);

        Attribute attribute = new Attribute("test",1);
        Optional<Attribute> OptAtt = Optional.of(attribute);
        Mockito.when(attributeRepositoryMock.count()).thenReturn(1L);

        Assert.assertEquals(productService.findAttributesCount(),1L);
    }

    @Test
    public void findAllSubCategoriesTest(){
        ProductServiceImpl productService = new ProductServiceImpl();
        SubCategoryRepository subCategoryRepositoryMock = mock(SubCategoryRepository.class);
        productService.setSubCategoryRepository(subCategoryRepositoryMock);

        SubCategory subCategory = new SubCategory();
        subCategory.setId(1L);
        Optional<SubCategory> OptSub = Optional.of(subCategory);
        List<SubCategory> subCategoryList = List.of(subCategory);
        Mockito.when(subCategoryRepositoryMock.findAll()).thenReturn(subCategoryList);




        Assert.assertEquals(productService.findAllSubCategories(),subCategoryList);
    }

    @Test
    public void findAllProductsPagestest(){
        ProductServiceImpl productService = new ProductServiceImpl();
        ProductRepository productRepositoryMock = mock(ProductRepository.class);
        productService.setProductRepository(productRepositoryMock);

        String page = "1";
        Integer maxInPage = 10;
        Product product = new Product();
        product.setId(1L);
        List<Product> productList = List.of(product);
        Pageable pageable = PageRequest.of(Integer.parseInt(page), maxInPage, Sort.by("name"));
        Mockito.when(productRepositoryMock.findAllByNameLike("%%",pageable)).thenReturn(productList);

        Assert.assertEquals(productService.findAllProductsPages(page,maxInPage),productList);
    }

    @Test
    public void findAllProductsCountTest(){
        ProductServiceImpl productService = new ProductServiceImpl();
        ProductRepository productRepositoryMock = mock(ProductRepository.class);
        productService.setProductRepository(productRepositoryMock);

        Product product = new Product();
        product.setId(1L);

        Mockito.when(productRepositoryMock.count()).thenReturn(1L);
        Assert.assertEquals(productService.findAllProductsCount(),1);
    }

    @Test
    public void reserveItemTest() {
        ProductServiceImpl productService = new ProductServiceImpl();
        ProductRepository productRepositoryMock = mock(ProductRepository.class);
        productService.setProductRepository(productRepositoryMock);

        Product product = new Product();
        product.setId(1L);
        Optional<Product> OptProd = Optional.of(product);

        Integer quantity =1;
        boolean isReserve = false;
        Mockito.when(productRepositoryMock.findById(product.getId())).thenReturn(OptProd);
        Assert.assertTrue(productService.reserveItem(product,quantity,isReserve));

        Optional <Product> Test = productRepositoryMock.findById(product.getId());
        isReserve = true;

        Assert.assertFalse(productService.reserveItem(product,50,isReserve));

      //  Product productNull = new Product();
       // Optional<Product> OptNull = Optional.of(productNull);

//        Mockito.when(productRepositoryMock.findById(30L)).thenReturn(OptNull);
//        Assert.assertFalse(productService.reserveItem(null,0,false));

    }
}