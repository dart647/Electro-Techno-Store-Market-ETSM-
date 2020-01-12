package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.*;
import com.etsm.ETSM.Repositories.*;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Min;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class AdminServiceImplTest {

    @Test
    public void addNewProductTest(){

        AdminServiceImpl adminService = new AdminServiceImpl();
        ProductRepository productRepositoryMock = mock(ProductRepository.class);
        MinorCategoryRepository minorCategoryRepositoryMock = mock(MinorCategoryRepository.class);
        adminService.setMinorCategoryRepository(minorCategoryRepositoryMock);
        adminService.setProductRepository(productRepositoryMock);

        Product productExist = new Product();
        productExist.setId(1L);
        productExist.setName("name");
        Optional<Product> optionalProduct = Optional.of(productExist);
        Mockito.when(productRepositoryMock.findByName(productExist.getName())).thenReturn(optionalProduct);
        Assert.assertFalse(adminService.addNewProduct(productExist,"name"));


        MinorCategory minorCategory = new MinorCategory();
        minorCategory.setId(2L);
        minorCategory.setName("1234");
        Optional<MinorCategory> optionalMinorCategory = Optional.of(minorCategory);

        Product productneg = new Product();
        productneg.setId(1L);
        productneg.setPrice(-10);
        productneg.setName("123");
        productneg.setDescription("desc");
        List<Product> productListNeg = List.of(productneg);
        List<Product> listNeg = new LinkedList(productListNeg);

        Mockito.when(minorCategoryRepositoryMock.findByName(minorCategory.getName())).thenReturn(optionalMinorCategory);
        String minorCategoryName = "1234";
        Assert.assertFalse(adminService.addNewProduct(productneg,minorCategoryName));


        Product product = new Product();
        product.setId(3L);
        product.setPrice(10);
        product.setName("123");
        product.setDescription("desc");

        List<Product> productList = List.of(product);
        List<Product> list = new LinkedList(productList);
        minorCategory.setProductList(list);


        Mockito.when(minorCategoryRepositoryMock.findByName(minorCategory.getName())).thenReturn(optionalMinorCategory);
        Mockito.when(productRepositoryMock.findById(product.getId())).thenReturn(optionalProduct);
        Assert.assertTrue(adminService.addNewProduct(product,minorCategoryName));

product.setId(0L);
        Assert.assertTrue(adminService.addNewProduct(product,minorCategoryName));



    }

    @Test
    public void findUsersTest(){
        AdminServiceImpl adminService = new AdminServiceImpl();
        UserRepository userRepositoryMock = mock(UserRepository.class);
        adminService.setUserRepository(userRepositoryMock);

        User user = new User();
        user.setId(1L);
        List<User> userList = List.of(user);

        Mockito.when(userRepositoryMock.findAll()).thenReturn(userList);
        adminService.findUsers();
    }

    @Test
    public void findAllAtrubutesGroupsTest(){
        AdminServiceImpl adminService = new AdminServiceImpl();
        AttributeGroupRepository attributeGroupRepositoryMock = mock(AttributeGroupRepository.class);
        adminService.setAttributeGroupRepository(attributeGroupRepositoryMock);
        Attribute_Group attribute_group = new Attribute_Group();
        attribute_group.setId(1L);
        //Optional<Attribute_Group> optionalAttribute_group = Optional.of(attribute_group);
        List<Attribute_Group> attributeGroupList = List.of(attribute_group);

        Mockito.when(attributeGroupRepositoryMock.findAll()).thenReturn(attributeGroupList);
        adminService.findAllAtrubutesGroups();


    }

    @Test
    public void findUserByIdTest(){
        AdminServiceImpl adminService = new AdminServiceImpl();
        UserRepository userRepositoryMock = mock(UserRepository.class);
        adminService.setUserRepository(userRepositoryMock);


        User user = new User();
        user.setId(1L);
        Optional<User> optionalUser = Optional.of(user);

        Mockito.when(userRepositoryMock.findById(user.getId())).thenReturn(optionalUser);
        Assert.assertEquals(adminService.findUserById(user.getId()),optionalUser);

    }

    @Test
    public void addNewCategoryTest() {
        AdminServiceImpl adminService = new AdminServiceImpl();
        CategoryRepository categoryRepositoryMock = mock(CategoryRepository.class);
        adminService.setCategoryRepository(categoryRepositoryMock);

        CategoryIncome categoryIncome = new CategoryIncome();
        categoryIncome.setId(1L);
        Category category = new Category();
        category.setId(1L);
        category.setName("name");
        category.setCategoryIncome(categoryIncome);
        SubCategory subCategory = new SubCategory();
        subCategory.setId(1L);
        List<SubCategory> subCategoryList = List.of(subCategory);
        List<SubCategory> subCategoryLinkedList = new LinkedList<>(subCategoryList);
        category.setSubCategories(subCategoryLinkedList);
        Optional<Category> optionalCategory = Optional.of(category);

        Mockito.when(categoryRepositoryMock.findByName((category.getName()))).thenReturn(optionalCategory);
        Assert.assertFalse(adminService.addNewCategory(category));

        Category categoryNew = new Category();
        categoryNew.setId(2L);
        Optional<Category> optionalCategoryNew = Optional.of(categoryNew);

        Mockito.when(categoryRepositoryMock.findByName((category.getName()))).thenReturn(optionalCategory);
        Mockito.when(categoryRepositoryMock.findById(categoryNew.getId())).thenReturn(optionalCategory);
        Assert.assertTrue(adminService.addNewCategory(categoryNew));

        categoryNew.setId(0L);
        Assert.assertTrue(adminService.addNewCategory(categoryNew));

    }

    @Test
    public void addNewSubCategorytest(){

        AdminServiceImpl adminService = new AdminServiceImpl();
        SubCategoryRepository subCategoryRepositoryMock = mock(SubCategoryRepository.class);
        CategoryRepository categoryRepositoryMock = mock(CategoryRepository.class);
        adminService.setSubCategoryRepository(subCategoryRepositoryMock);
        adminService.setCategoryRepository(categoryRepositoryMock);


        SubCategory subCategoryExist = new SubCategory();
        subCategoryExist.setId(1L);
        subCategoryExist.setName("name");
        Optional<SubCategory> optionalSubCategory = Optional.of(subCategoryExist);
        Mockito.when(subCategoryRepositoryMock.findByName(subCategoryExist.getName())).thenReturn(optionalSubCategory);
        Assert.assertFalse(adminService.addNewSubCategory("name",subCategoryExist));

        Category category = new Category();
        category.setId(1L);
        category.setName("1234");

        SubCategory subCategory = new SubCategory();
        subCategory.setId(2L);
        subCategory.setName("123");
        List<SubCategory> subCategoryList = List.of(subCategory);
        List<SubCategory> list = new LinkedList(subCategoryList);

        category.setSubCategories(list);
        Optional<Category> optionalCategory = Optional.of(category);
        String CategoryName = "1234";
        Mockito.when(categoryRepositoryMock.findByName(CategoryName)).thenReturn(optionalCategory);
        Mockito.when(subCategoryRepositoryMock.findById(subCategory.getId())).thenReturn(optionalSubCategory);
        Assert.assertTrue(adminService.addNewSubCategory(CategoryName,subCategory));

    }



    @Test
    public void addNewMinorCategoryTest(){
        AdminServiceImpl adminService = new AdminServiceImpl();
        SubCategoryRepository subCategoryRepositoryMock = mock(SubCategoryRepository.class);
        CategoryRepository categoryRepositoryMock = mock(CategoryRepository.class);
        MinorCategoryRepository minorCategoryRepositoryMock = mock(MinorCategoryRepository.class);
        AttributeRepository attributeRepositoryMock = mock(AttributeRepository.class);
        adminService.setSubCategoryRepository(subCategoryRepositoryMock);
        adminService.setCategoryRepository(categoryRepositoryMock);
        adminService.setMinorCategoryRepository(minorCategoryRepositoryMock);
        adminService.setAttributeRepository(attributeRepositoryMock);


        MinorCategory minorCategoryExist = new MinorCategory();
        minorCategoryExist.setId(1L);
        minorCategoryExist.setName("name");
        Optional<MinorCategory> optionalMinorCategory = Optional.of(minorCategoryExist);
        Mockito.when(minorCategoryRepositoryMock.findByName(minorCategoryExist.getName())).thenReturn(optionalMinorCategory);
        Assert.assertFalse(adminService.addNewMinorCategory("name",minorCategoryExist));


        SubCategory subCategory = new SubCategory();
        subCategory.setId(1L);
        subCategory.setName("1234");

        Product product = new Product();
        product.setId(1L);
        List<Product>productList = List.of(product);
        List<Product>productLinkedList = new LinkedList<>(productList);


        MinorCategory minorCategory = new MinorCategory();
        minorCategory.setId(2L);

        minorCategory.setName("123");
        minorCategory.setProductList(productLinkedList);
        List<MinorCategory> minorCategoryList = List.of(minorCategory);
        List<MinorCategory> list = new LinkedList<MinorCategory>(minorCategoryList);
       // minorCategory.setSubcategory_id(subCategory);
        subCategory.setMinorCategoryList(list);
        Optional<SubCategory> optionalSubCategory = Optional.of(subCategory);


        String subCategoryName = "1234";
        Mockito.when(subCategoryRepositoryMock.findByName(subCategoryName)).thenReturn(optionalSubCategory);
        Mockito.when(minorCategoryRepositoryMock.findById(minorCategory.getId())).thenReturn(optionalMinorCategory);
        Assert.assertTrue(adminService.addNewMinorCategory(subCategoryName,minorCategory));

    }


    @Test
    public void addNewAttributeTest(){
        AdminServiceImpl adminService = new AdminServiceImpl();
        AttributeRepository attributeRepositoryMock = mock(AttributeRepository.class);
        AttributeGroupRepository attributeGroupRepositoryMock = mock (AttributeGroupRepository.class);

        adminService.setAttributeGroupRepository(attributeGroupRepositoryMock);
        adminService.setAttributeRepository(attributeRepositoryMock);
        //

        Attribute attributeExist = new Attribute();
        attributeExist.setId(1L);
        attributeExist.setName("name");
        Optional<Attribute>optionalAttributeExist=Optional.of(attributeExist);
        Mockito.when(attributeRepositoryMock.findByName(attributeExist.getName())).thenReturn(optionalAttributeExist);
        Assert.assertFalse(adminService.addNewAttribute(attributeExist,"name"));

        Attribute attribute = new Attribute();
        attribute.setId(2L);
        attribute.setName("1234");
        List<Attribute> attributeList = List.of(attribute);
        List<Attribute> list = new LinkedList(attributeList);
        Attribute_Group attribute_group = new Attribute_Group();
        attribute_group.setId(1L);
        attribute_group.setAttribute_id(list);
        attribute_group.setName("123");

        Product product = new Product();
        product.setId(1L);
        List<Product>productList = List.of(product);
        List<Product>productLinkedList = new LinkedList<>(productList);
        attribute_group.setProduct_id(productLinkedList);
        Optional<Attribute_Group> optionalAttribute_group = Optional.of(attribute_group);
        Mockito.when(attributeGroupRepositoryMock.findByName(attribute_group.getName())).thenReturn(optionalAttribute_group);
        attribute.setAttribute_groups(attribute_group);
        ProductAttrValue productAttrValue = new ProductAttrValue();
        productAttrValue.setId(1L);
        List<ProductAttrValue>productAttrValueList = List.of(productAttrValue);
        List<ProductAttrValue>productAttrValueLinkedList = new LinkedList<>(productAttrValueList);

        attribute.setProductAttrValue(productAttrValueLinkedList);
        Optional<Attribute>optionalAttribute = Optional.of(attribute);
       Mockito.when(attributeRepositoryMock.findById(attribute.getId())).thenReturn(optionalAttribute);
        // Mockito.when(attributeGroupRepositoryMock.findById(attribute.getId())).thenReturn(optionalAttribute_group);

        Assert.assertFalse(adminService.addNewAttribute(attribute,"123"));

        attribute.setId(0L);
        Assert.assertFalse(adminService.addNewAttribute(attribute,"123"));


    }

    @Test//(expected=NullPointerException.class)
    public void addNewAttributeToProductTest() {
        AdminServiceImpl adminService = new AdminServiceImpl();
        AttributeRepository attributeRepositoryMock = mock(AttributeRepository.class);
        ProductRepository productRepositoryMock = mock(ProductRepository.class);
        ProductAttrValueRepository productAttrValueRepositoryMock = mock (ProductAttrValueRepository.class);

        adminService.setAttributeRepository(attributeRepositoryMock);
        adminService.setProductRepository(productRepositoryMock);
        adminService.setProductAttrValueRepository(productAttrValueRepositoryMock);

        //

        ProductAttrValue productAttrValueExist = new ProductAttrValue();
        productAttrValueExist.setId(1L);
        productAttrValueExist.setValue("value");
        Optional<ProductAttrValue> optionalProductAttrValue = Optional.of(productAttrValueExist);
        List<ProductAttrValue> productAttrValueList = List.of(productAttrValueExist);
        List<ProductAttrValue> productAttrValueLinkedList = new LinkedList(productAttrValueList);
        Mockito.when(productAttrValueRepositoryMock.findByValue(productAttrValueExist.getValue())).thenReturn(optionalProductAttrValue);

        Product product = new Product();
        product.setId(1L);
        product.setName("name");


        Attribute attribute = new Attribute();
        attribute.setId(1L);
        attribute.setName("name");
        attribute.setProductAttrValue(productAttrValueLinkedList);

        List<Attribute> attributeList = List.of(attribute);
        List<Attribute> attributeLinkedList = new LinkedList(attributeList);
        Optional<Attribute>optionalAttribute = Optional.of(attribute);
        Mockito.when(attributeRepositoryMock.findByName(attribute.getName())).thenReturn(optionalAttribute);

        Attribute_Group attribute_group = new Attribute_Group();
        attribute_group.setId(1L);
        attribute_group.setAttribute_id(attributeLinkedList);
        List<Attribute_Group> attribute_groupList = List.of(attribute_group);
        List<Attribute_Group> attribute_groupLinkedList = new LinkedList(attribute_groupList);


        product.setAttribute_groups(attribute_groupLinkedList);
        product.setProductAttrValue(productAttrValueLinkedList);
        Optional<Product> optionalProduct = Optional.of(product);
        Mockito.when(productRepositoryMock.findByName(product.getName())).thenReturn(optionalProduct);
        Assert.assertTrue(adminService.addNewAttributeToProduct(product.getName(),attribute.getName(),productAttrValueExist));

    }

    @Test
    public void findUsersPagesTest(){
        AdminServiceImpl adminService = new AdminServiceImpl();
        UserRepository userRepositoryMock = mock(UserRepository.class);
        adminService.setUserRepository(userRepositoryMock);
        //

        String page = "1";
        int maxInPage = 5;

        User user = new User();
        user.setId(1L);
        user.setLogin("Login");
        List<User> userList = List.of(user);
        Pageable pageable = PageRequest.of(Integer.parseInt(page), maxInPage, Sort.by("login"));
        Mockito.when(userRepositoryMock.findAllByLoginLike("%%",pageable)).thenReturn(userList);
        Assert.assertEquals(adminService.findUsersPages(page,maxInPage),userList);
    }
    @Test
    public void findUsersCountTest(){
        AdminServiceImpl adminService = new AdminServiceImpl();
        UserRepository userRepositoryMock = mock(UserRepository.class);
        adminService.setUserRepository(userRepositoryMock);
        //
        User user = new User();
        user.setId(1L);
        Mockito.when(userRepositoryMock.count()).thenReturn(1L);
        Assert.assertEquals(adminService.findUsersCount(),1L);

    }

    @Test
    public void addNewAttributeGroupTest(){

        AdminServiceImpl adminService = new AdminServiceImpl();
        AttributeGroupRepository attributeGroupRepositoryMock = mock(AttributeGroupRepository.class);
        adminService.setAttributeGroupRepository(attributeGroupRepositoryMock);
        //

        Product product = new Product();
        product.setId(1L);
        List<Product> productList = List.of(product);
        List<Product> productLinkedList = new LinkedList<>(productList);

        Attribute attribute = new Attribute();
        attribute.setId(1L);
        List<Attribute> attributeList = List.of(attribute);
        List<Attribute> attributeLinkedList = new LinkedList<>(attributeList);

        Attribute_Group attributeGroup = new Attribute_Group();
        attributeGroup.setName("Name");
        adminService.addNewAttributeGroup(attributeGroup);

        attributeGroup.setId(1L);
        attributeGroup.setProduct_id(productLinkedList);
        attributeGroup.setAttribute_id(attributeLinkedList);
        Optional<Attribute_Group> optionalAttribute_group = Optional.of(attributeGroup);
        Mockito.when(attributeGroupRepositoryMock.findById(attributeGroup.getId())).thenReturn(optionalAttribute_group);
        adminService.addNewAttributeGroup(attributeGroup);
    }


}