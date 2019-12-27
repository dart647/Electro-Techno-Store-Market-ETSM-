package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.*;
import com.etsm.ETSM.Repositories.*;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.core.parameters.P;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Min;
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

        Product product = new Product();
        product.setId(1L);
        product.setName("name");
        List<Product> productList = List.of(product);
        Optional<Product> optionalProduct = Optional.of(product);
        Mockito.when(productRepositoryMock.findByName(product.getName())).thenReturn(optionalProduct);
        Assert.assertFalse(adminService.addNewProduct(product,"name"));

        MinorCategory minorCategory = new MinorCategory();
        minorCategory.setId(1L);
        minorCategory.setName("name");
        List<MinorCategory> minorCategoryList = List.of(minorCategory);
        minorCategory.setProductList(productList);

        product.setPrice(1);
        product.setDescription("desc");
        product.setName("name");

        SubCategory subCategory = new SubCategory();
        subCategory.setId(1L);
        subCategory.setMinorCategoryList(minorCategoryList);
       // minorCategory.setSubcategory_id(subCategory);
       List<MinorCategory> minorCategoriesTest = subCategory.getMinorCategoryList();

        //SubCategory subCategory_id

        Optional<MinorCategory> optionalMinorCategory = Optional.of(minorCategory);
        Optional<Product> optionalProduct2 = Optional.of(product);
        Mockito.when(productRepositoryMock.findByName(product.getName())).thenReturn(optionalProduct2);
       Mockito.when(minorCategoryRepositoryMock.findByName("name")).thenReturn(optionalMinorCategory);
        Mockito.when(productRepositoryMock.saveAndFlush(product)).thenReturn(product);
        Optional<MinorCategory> optionalMinorCategoryTest = minorCategoryRepositoryMock.findByName("name");

        Assert.assertTrue(adminService.addNewProduct(product,"name"));


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

    @Test(expected=NullPointerException.class)
    public void addNewCategoryTest() {
        AdminServiceImpl adminService = new AdminServiceImpl();
        CategoryRepository categoryRepositoryMock = mock(CategoryRepository.class);
        adminService.setCategoryRepository(categoryRepositoryMock);

        Category category = new Category();
        category.setId(1L);
        category.setName("name");
        Optional<Category> optionalCategory = Optional.of(category);

        Mockito.when(categoryRepositoryMock.findByName((category.getName()))).thenReturn(optionalCategory);
        Assert.assertFalse(adminService.addNewCategory(category));

        Category categoryNew = new Category();
        categoryNew.setId(2L);

        Mockito.when(categoryRepositoryMock.findByName((category.getName()))).thenReturn(optionalCategory);
        Assert.assertTrue(adminService.addNewCategory(categoryNew));

    }

    @Test(expected=UnsupportedOperationException.class)
    public void addNewSubCategorytest(){

        AdminServiceImpl adminService = new AdminServiceImpl();
        SubCategoryRepository subCategoryRepositoryMock = mock(SubCategoryRepository.class);
        CategoryRepository categoryRepositoryMock = mock(CategoryRepository.class);
        adminService.setSubCategoryRepository(subCategoryRepositoryMock);
        adminService.setCategoryRepository(categoryRepositoryMock);


        SubCategory subCategory = new SubCategory();
        subCategory.setId(1L);
        subCategory.setName("name");


        SubCategory subCategoryNew = new SubCategory();
        subCategoryNew.setId(2L);
        subCategoryNew.setName("kavo");
        //Optional<SubCategory> optionalSubCategoryNew = Optional.of(subCategoryNew);

        Category category = new Category();
        category.setId(1L);
        subCategory.setCategory_id(category);
        Optional<SubCategory> optionalSubCategory = Optional.of(subCategory);
        List<SubCategory> subCategoryList = List.of(subCategory);
        category.setSubCategories(subCategoryList);
        category.setName("name");
        Optional<Category> optionalCategory = Optional.of(category);



        Mockito.when(subCategoryRepositoryMock.findByName(subCategory.getName())).thenReturn(optionalSubCategory);
        Assert.assertFalse(adminService.addNewSubCategory("name",subCategory));


        Mockito.when(categoryRepositoryMock.findByName(category.getName())).thenReturn(optionalCategory);
        Assert.assertTrue(adminService.addNewSubCategory("name",subCategoryNew));
    }





}