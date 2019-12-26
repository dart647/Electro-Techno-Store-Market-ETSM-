package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.*;
import com.etsm.ETSM.Services.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.parameters.P;
import org.springframework.security.web.header.Header;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.Min;
import java.security.Principal;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class AdminControllerTest {


    @Test //(expected = NullPointerException.class)
    public void getAllUsersTest(){
        AdminService adminServiceMock = mock(AdminService.class);
        MainService mainServiceMock = mock(MainService.class);
        UserInformationService userInformationServiceMock = mock(UserInformationService.class);
        ProductService productServiceMock = mock(ProductService.class);
        AdminController adminController = new AdminController
                (adminServiceMock,mainServiceMock,userInformationServiceMock,productServiceMock);



        HeaderService headerServicemock = mock(HeaderService.class);
        adminController.setHeaderService(headerServicemock);

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "name";
            }
        };

        Header header = new Header("name","value");
        Mockito.when(headerServicemock.setHeader(principal)).thenReturn(null);
        List<Integer> pages = new ArrayList<>();

        User user = new User();
        user.setId(1L);
        List<User> userList = List.of(user);
        Mockito.when(adminServiceMock.findUsersPages("page",5)).thenReturn(userList);

        Mockito.when(adminServiceMock.findUsersCount()).thenReturn(10L);

        for (int i=0;i<10/5;i++){
            pages.add(i);
        }
        Category category = new Category();
        category.setId(1L);
        List<Category> categoryList = List.of(category);

        Mockito.when(headerServicemock.getHeaderRole()).thenReturn("ADMIN");
        Mockito.when(headerServicemock.getHeaderCategories()).thenReturn(categoryList);
        ModelAndView m1 = adminController.getAllUsers("page",principal);

        Assert.assertEquals(m1.getViewName(),"admin/all");



    }

    @Test //(expected = NullPointerException.class)
    public void userTest(){
        AdminService adminServiceMock = mock(AdminService.class);
        MainService mainServiceMock = mock(MainService.class);
        UserInformationService userInformationServiceMock = mock(UserInformationService.class);
        ProductService productServiceMock = mock(ProductService.class);
        AdminController adminController = new AdminController
                (adminServiceMock,mainServiceMock,userInformationServiceMock,productServiceMock);

        HeaderService headerServicemock = mock(HeaderService.class);
        adminController.setHeaderService(headerServicemock);
        adminController.setAdminService(adminServiceMock);

        User user = new User();
        user.setId(1L);
        user.setRoles(Collections.singleton(Role.USER));
        Optional<User> userOptional = Optional.of(user);


        User finalUserForRole = user;
        Principal principal = () -> finalUserForRole.getUsername();

        Header header = new Header("name","value");
        Mockito.when(headerServicemock.setHeader(principal)).thenReturn(null);


       //Principal principal1 = (Principal)user.getRoles();
        //Mockito.when(headerServicemock.setHeader(principal1)).thenReturn(null);

        Category category = new Category();
        category.setId(1L);
        List<Category> categoryList = List.of(category);

        Mockito.when(adminServiceMock.findUserById(user.getId())).
                thenReturn(userOptional);

        Mockito.when(headerServicemock.getHeaderRole()).thenReturn("Role");
        Mockito.when(headerServicemock.getHeaderCategories()).thenReturn(categoryList);

        ModelAndView m1 = adminController.user(user.getId(),principal);
        ModelAndView m2 = adminController.user(30L,principal);
        Assert.assertEquals(m1.getViewName(),"admin/user");
        Assert.assertEquals(m2.getViewName(),"errors/404");





    }

    @Test
    public void deleteOneUserTest(){
        AdminService adminServiceMock = mock(AdminService.class);
        MainService mainServiceMock = mock(MainService.class);
        UserInformationService userInformationServiceMock = mock(UserInformationService.class);
        ProductService productServiceMock = mock(ProductService.class);
        AdminController adminController = new AdminController
                (adminServiceMock,mainServiceMock,userInformationServiceMock,productServiceMock);


        adminController.setAdminService(adminServiceMock);
        adminController.setUserInformationService(userInformationServiceMock);

        User user = new User();
        user.setId(1L);
        Optional<User> optionalUser = Optional.of(user);
        Mockito.when(adminServiceMock.findUserById(user.getId())).thenReturn(optionalUser);
        userInformationServiceMock.deleteUser(user);
        Assert.assertEquals(adminController.deleteOneUser(user.getId()),"redirect:/admin/all");
    }

    @Test
    public void AddProductTest (){
        AdminService adminServiceMock = mock(AdminService.class);
        MainService mainServiceMock = mock(MainService.class);
        UserInformationService userInformationServiceMock = mock(UserInformationService.class);
        ProductService productServiceMock = mock(ProductService.class);
        AdminController adminController = new AdminController
                (adminServiceMock,mainServiceMock,userInformationServiceMock,productServiceMock);


        HeaderService headerServiceMock = mock(HeaderService.class);
        adminController.setHeaderService(headerServiceMock);


        User user = new User();
        user.setId(1L);
        user.setRoles(Collections.singleton(Role.ADMIN));
        Optional<User> userOptional = Optional.of(user);


        User finalUserForRole = user;
        Principal principal = () -> finalUserForRole.getUsername();
        headerServiceMock.setHeader(principal);

        String page = "page";
        int maxInPage = 1;

        Product product = new Product();
        product.setId(1L);


        List<Product> productList = List.of(product);

        List<Integer> pages = new ArrayList<>();

        Category category = new Category();
        category.setId(1L);
        List<Category> categoryList = List.of(category);
        MinorCategory minorCategory = new MinorCategory();
        minorCategory.setName("Name");
        List<MinorCategory>  minorCategoryList = List.of(minorCategory);
        SubCategory subCategory = new SubCategory();
        subCategory.setId(1L);
        List<SubCategory> subCategoryList = List.of(subCategory);

        Mockito.when(productServiceMock.findAllProductsPages(page,maxInPage)).
                thenReturn(productList);
        Mockito.when(productServiceMock.findAllProductsCount()).thenReturn(3L);

        pages.add(0);
        pages.add(1);
        pages.add(2);
        Mockito.when(headerServiceMock.getHeaderRole()).thenReturn("str");
        Mockito.when(headerServiceMock.getHeaderCategories()).thenReturn(categoryList);
        Mockito.when(productServiceMock.findCategories()).thenReturn(categoryList);
        Mockito.when(productServiceMock.findSubCategories()).thenReturn(subCategoryList);
        Mockito.when(productServiceMock.findMinorCategories()).thenReturn(minorCategoryList);

        ModelAndView m1 = adminController.AddProduct(page,principal);

        Assert.assertEquals(m1.getViewName(),"admin/addProduct");



    }

    @Test
    public void StringAddProductTest(){
        AdminService adminServiceMock = mock(AdminService.class);
        MainService mainServiceMock = mock(MainService.class);
        UserInformationService userInformationServiceMock = mock(UserInformationService.class);
        ProductService productServiceMock = mock(ProductService.class);
        AdminController adminController = new AdminController
                (adminServiceMock,mainServiceMock,userInformationServiceMock,productServiceMock);

   adminController.setAdminService(adminServiceMock);
   Product product = new Product();
   product.setId(1L);
   Mockito.when(adminServiceMock.addNewProduct(product,"name")).thenReturn(true);

   Assert.assertEquals(adminController.AddProduct(product,"name"),"redirect:/admin/addProduct");

    }

    @Test
    public void addCategoryTest(){
        AdminService adminServiceMock = mock(AdminService.class);
        MainService mainServiceMock = mock(MainService.class);
        UserInformationService userInformationServiceMock = mock(UserInformationService.class);
        ProductService productServiceMock = mock(ProductService.class);
        AdminController adminController = new AdminController
                (adminServiceMock,mainServiceMock,userInformationServiceMock,productServiceMock);

        HeaderService headerServiceMock = mock(HeaderService.class);
        adminController.setHeaderService(headerServiceMock);
        User user = new User();
        user.setId(1L);
        user.setRoles(Collections.singleton(Role.ADMIN));
        Optional<User> userOptional = Optional.of(user);


        User finalUserForRole = user;
        Principal principal = () -> finalUserForRole.getUsername();
        headerServiceMock.setHeader(principal);

       // Mockito.when(headerServiceMock.setHeader(principal)).thenReturn(1);
        Category category = new Category();
        category.setId(1L);
        List<Category> categoryList = List.of(category);
        Mockito.when(productServiceMock.findCategories()).thenReturn(categoryList);
        Product product = new Product();
        product.setId(1L);
        List<Product> productList = List.of(product);
        Mockito.when(productServiceMock.findAllProducts()).thenReturn(productList);
        Mockito.when(headerServiceMock.getHeaderRole()).thenReturn("ADMIN");
        Mockito.when(headerServiceMock.getHeaderCategories()).thenReturn(categoryList);

        ModelAndView m1 = adminController.addCategory(principal);

        Assert.assertEquals(m1.getViewName(),"admin/addCategory");


    }

    @Test
    public void StringAddCategoryTest(){
        AdminService adminServiceMock = mock(AdminService.class);
        MainService mainServiceMock = mock(MainService.class);
        UserInformationService userInformationServiceMock = mock(UserInformationService.class);
        ProductService productServiceMock = mock(ProductService.class);
        AdminController adminController = new AdminController
                (adminServiceMock,mainServiceMock,userInformationServiceMock,productServiceMock);

        adminController.setAdminService(adminServiceMock);

        Category category = new Category();
        category.setId(1L);
        Mockito.when(adminServiceMock.addNewCategory(category)).thenReturn(true);
        String str = adminController.addCategory(category);
        Assert.assertEquals("redirect:/admin/addCategory",str);

    }

    @Test
    public void addSubCategoryTest(){
        AdminService adminServiceMock = mock(AdminService.class);
        MainService mainServiceMock = mock(MainService.class);
        UserInformationService userInformationServiceMock = mock(UserInformationService.class);
        ProductService productServiceMock = mock(ProductService.class);
        AdminController adminController = new AdminController
                (adminServiceMock,mainServiceMock,userInformationServiceMock,productServiceMock);

        HeaderService headerServiceMock = mock(HeaderService.class);
        adminController.setHeaderService(headerServiceMock);

        SubCategory subCategory = new SubCategory();
        subCategory.setId(1L);
        List<SubCategory> subCategoryList = List.of(subCategory);
        Category category = new Category();
        category.setId(1L);
        category.setName("name");
        List<Category> categoryList = List.of(category);
        Mockito.when(productServiceMock.findCategories()).thenReturn(categoryList);
        Mockito.when(productServiceMock.findSubCategories()).thenReturn(subCategoryList);
        Mockito.when(headerServiceMock.getHeaderRole()).thenReturn("ADMIN");
        Mockito.when(headerServiceMock.getHeaderCategories()).thenReturn(categoryList);
        User user = new User();
        user.setId(1L);
        user.setRoles(Collections.singleton(Role.ADMIN));
        Optional<User> userOptional = Optional.of(user);


        User finalUserForRole = user;
        Principal principal = () -> finalUserForRole.getUsername();
        headerServiceMock.setHeader(principal);

        ModelAndView m1 = adminController.addSubCategory(principal);

        Assert.assertEquals(m1.getViewName(),"admin/addSubCategory");




    }

    @Test
    public void StringAddSubCategoryTest(){
        AdminService adminServiceMock = mock(AdminService.class);
        MainService mainServiceMock = mock(MainService.class);
        UserInformationService userInformationServiceMock = mock(UserInformationService.class);
        ProductService productServiceMock = mock(ProductService.class);
        AdminController adminController = new AdminController
                (adminServiceMock,mainServiceMock,userInformationServiceMock,productServiceMock);

        SubCategory subCategory = new SubCategory();
        subCategory.setId(1L);
        adminController.setAdminService(adminServiceMock);

        Mockito.when(adminServiceMock.addNewSubCategory("name",subCategory)).thenReturn(true);

        String str = adminController.addSubCategory(subCategory,"name");
        Assert.assertEquals(str,"redirect:/admin/addSubCategory");

    }

    @Test
    public void addMinorCategoryTest(){
        AdminService adminServiceMock = mock(AdminService.class);
        MainService mainServiceMock = mock(MainService.class);
        UserInformationService userInformationServiceMock = mock(UserInformationService.class);
        ProductService productServiceMock = mock(ProductService.class);
        AdminController adminController = new AdminController
                (adminServiceMock,mainServiceMock,userInformationServiceMock,productServiceMock);

        HeaderService headerServiceMock = mock(HeaderService.class);
        adminController.setHeaderService(headerServiceMock);

        User user = new User();
        user.setId(1L);
        user.setRoles(Collections.singleton(Role.ADMIN));
        Optional<User> userOptional = Optional.of(user);


        User finalUserForRole = user;
        Principal principal = () -> finalUserForRole.getUsername();
        headerServiceMock.setHeader(principal);

        MinorCategory minorCategory = new MinorCategory();
        minorCategory.setId(1L);
        List<MinorCategory> minorCategoryList = List.of(minorCategory);
        SubCategory subCategory = new SubCategory();
        subCategory.setName("name");
        subCategory.setId(1L);
        List<SubCategory> subCategoryList = List.of(subCategory);
        Category category = new Category();
        category.setId(1L);
        List<Category> categoryList = List.of(category);
        Mockito.when(productServiceMock.findAllSubCategories()).thenReturn(subCategoryList);
        Mockito.when(productServiceMock.findMinorCategories()).thenReturn(minorCategoryList);
        Mockito.when(headerServiceMock.getHeaderRole()).thenReturn("ADMIN");
        Mockito.when(headerServiceMock.getHeaderCategories()).thenReturn(categoryList);

        ModelAndView m1 = adminController.addMinorCategory(principal);

        Assert.assertEquals(m1.getViewName(),"admin/addMinorCategory");
    }

    @Test
    public void StringAddMinorCategoryTest(){

        AdminService adminServiceMock = mock(AdminService.class);
        MainService mainServiceMock = mock(MainService.class);
        UserInformationService userInformationServiceMock = mock(UserInformationService.class);
        ProductService productServiceMock = mock(ProductService.class);
        AdminController adminController = new AdminController
                (adminServiceMock,mainServiceMock,userInformationServiceMock,productServiceMock);

        adminController.setAdminService(adminServiceMock);
        MinorCategory minorCategory = new MinorCategory();
        minorCategory.setId(1L);
       List<MinorCategory> minorCategoryList = List.of(minorCategory);

       // Mockito.when(adminController.addMinorCategory(minorCategory,"name")).thenReturn("");

        String string = adminController.addMinorCategory(minorCategory,"name");

        Assert.assertEquals(string,"redirect:/admin/addMinorCategory");
    }

    @Test
    public  void addAttributeTest() {

        AdminService adminServiceMock = mock(AdminService.class);
        MainService mainServiceMock = mock(MainService.class);
        UserInformationService userInformationServiceMock = mock(UserInformationService.class);
        ProductService productServiceMock = mock(ProductService.class);
        AdminController adminController = new AdminController
                (adminServiceMock,mainServiceMock,userInformationServiceMock,productServiceMock);

        HeaderService headerServiceMock = mock(HeaderService.class);
        adminController.setHeaderService(headerServiceMock);

        User user = new User();
        user.setId(1L);
        user.setRoles(Collections.singleton(Role.ADMIN));
        Optional<User> userOptional = Optional.of(user);


        User finalUserForRole = user;
        Principal principal = () -> finalUserForRole.getUsername();
        headerServiceMock.setHeader(principal);

        String page = "page";
        int maxInPage = 1;

        Attribute attribute = new Attribute();
        attribute.setId(1L);
        List<Attribute> attributeList  = List.of(attribute);
        Mockito.when(productServiceMock.findAttributesPages(page,maxInPage)).thenReturn(attributeList);
        Mockito.when(productServiceMock.findAttributesCount()).thenReturn(3L);
        List<Integer> pages = new ArrayList<>();
        for (int i =0; i<3;i++){
            pages.add(i);
        }

        Category category = new Category();
        category.setId(1L);
        List<Category> categoryList = List.of(category);

        Product product = new Product();
        product.setId(1L);
        List<Product> productList = List.of(product);

        Attribute_Group attribute_group = new Attribute_Group();
        attribute_group.setId(1L);
        List<Attribute_Group> attribute_groupList = List.of(attribute_group);
        Mockito.when(headerServiceMock.getHeaderRole()).thenReturn("ADMIN");
        Mockito.when(headerServiceMock.getHeaderCategories()).thenReturn(categoryList);
        Mockito.when(productServiceMock.findAllProducts()).thenReturn(productList);
        Mockito.when(adminServiceMock.findAllAtrubutesGroups()).thenReturn(attribute_groupList);

        ModelAndView m1 = adminController.addAttribute(page,principal);

        Assert.assertEquals(m1.getViewName(),"admin/addAttribute");



    }

    @Test
    public void StringAddAttributeTest(){

        AdminService adminServiceMock = mock(AdminService.class);
        MainService mainServiceMock = mock(MainService.class);
        UserInformationService userInformationServiceMock = mock(UserInformationService.class);
        ProductService productServiceMock = mock(ProductService.class);
        AdminController adminController = new AdminController
                (adminServiceMock,mainServiceMock,userInformationServiceMock,productServiceMock);

        adminController.setAdminService(adminServiceMock);
        Attribute attribute = new Attribute();
        attribute.setId(1L);

        Mockito.when(adminServiceMock.addNewAttribute(attribute,"name")).thenReturn(true);

        String s = adminController.addAttribute(attribute,"name");

        Assert.assertEquals(s,"redirect:/admin/addAttribute");


    }

    @Test
    public  void addAttributeToProductTest(){

        AdminService adminServiceMock = mock(AdminService.class);
        MainService mainServiceMock = mock(MainService.class);
        UserInformationService userInformationServiceMock = mock(UserInformationService.class);
        ProductService productServiceMock = mock(ProductService.class);
        AdminController adminController = new AdminController
                (adminServiceMock,mainServiceMock,userInformationServiceMock,productServiceMock);

        HeaderService headerServiceMock = mock(HeaderService.class);
        adminController.setHeaderService(headerServiceMock);

        User user = new User();
        user.setId(1L);
        user.setRoles(Collections.singleton(Role.ADMIN));
        Optional<User> userOptional = Optional.of(user);


        User finalUserForRole = user;
        Principal principal = () -> finalUserForRole.getUsername();
        headerServiceMock.setHeader(principal);

        String page = "page";
        int maxInPage = 1;

        Attribute attribute = new Attribute();
        attribute.setId(1L);
        List<Attribute> attributeList  = List.of(attribute);
        Mockito.when(productServiceMock.findAttributesPages(page,maxInPage)).thenReturn(attributeList);
        Mockito.when(productServiceMock.findAttributesCount()).thenReturn(3L);
        //List<Integer> pages = new ArrayList<>();
        //for (int i =0; i<3;i++){
        //    pages.add(i);
        //}

        Category category = new Category();
        category.setId(1L);
        List<Category> categoryList = List.of(category);

        Product product = new Product();
        product.setId(1L);
        List<Product> productList = List.of(product);

        Attribute_Group attribute_group = new Attribute_Group();
        attribute_group.setId(1L);
        List<Attribute_Group> attribute_groupList = List.of(attribute_group);
        Mockito.when(headerServiceMock.getHeaderRole()).thenReturn("ADMIN");
        Mockito.when(headerServiceMock.getHeaderCategories()).thenReturn(categoryList);
        Mockito.when(productServiceMock.findAllProducts()).thenReturn(productList);
        //Mockito.when(adminServiceMock.findAllAtrubutesGroups()).thenReturn(attribute_groupList);
        Mockito.when(productServiceMock.findAttributes()).thenReturn(attributeList);
        ModelAndView m1 = adminController.addAttributeToProduct(principal);

        Assert.assertEquals(m1.getViewName(),"admin/addAttributeToProduct");

    }

    @Test
    public void StringAddAttributeToProductTest(){
        AdminService adminServiceMock = mock(AdminService.class);
        MainService mainServiceMock = mock(MainService.class);
        UserInformationService userInformationServiceMock = mock(UserInformationService.class);
        ProductService productServiceMock = mock(ProductService.class);
        AdminController adminController = new AdminController
                (adminServiceMock,mainServiceMock,userInformationServiceMock,productServiceMock);


        adminController.setAdminService(adminServiceMock);
adminController.setMainService(mainServiceMock);
        ProductAttrValue productAttrValue = new ProductAttrValue();
        productAttrValue.setId(1L);

        Mockito.when(adminServiceMock.addNewAttributeToProduct("product","attribute",productAttrValue)).thenReturn(true);
        String s = adminController.addAttributeToProduct(productAttrValue,"product","attribute");
        Assert.assertEquals(s,"redirect:/admin/");

        Mockito.when(adminServiceMock.addNewAttributeToProduct("product","attribute",productAttrValue)).thenReturn(false);

        String s2 = adminController.addAttributeToProduct(productAttrValue,"product","attribute");
        Assert.assertEquals(s2,"redirect:/admin/addAttributeToProduct");


    }


}