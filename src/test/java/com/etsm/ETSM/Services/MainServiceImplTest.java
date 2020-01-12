package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.Category;
import com.etsm.ETSM.Models.Product;
import com.etsm.ETSM.Models.ProductAttrValue;
import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Repositories.CategoryRepository;
import com.etsm.ETSM.Repositories.ProductAttrValueRepository;
import com.etsm.ETSM.Repositories.ProductRepository;
import com.etsm.ETSM.Repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class MainServiceImplTest {

    @Test
    public void SetRecommendationsTest (){
        MainServiceImpl mainService = new MainServiceImpl();
        ProductRepository productRepositoryMock = mock(ProductRepository.class);
        mainService.setProductRepository(productRepositoryMock);
        ProductService productServiceMock = mock(ProductService.class);
        mainService.setProductService(productServiceMock);

        Product product = new Product();
        product.setId(1L);
        List<Product> productList = List.of(product);
        List<Product> recommendations = new ArrayList<>();
        Mockito.when(productRepositoryMock.findAll()).thenReturn(productList);
        Mockito.when(productRepositoryMock.count()).thenReturn(1L);

        for (int i =0;i<12; i++){
            recommendations.add(product);
        }

        Mockito.when(productServiceMock.findAllProducts()).thenReturn(productList);
        Assert.assertEquals(mainService.SetRecommendations(),recommendations);

    }

    @Test
    public void GetAllCategoriesTest () {
        MainServiceImpl mainService = new MainServiceImpl();
        CategoryRepository categoryRepositoryMock = mock(CategoryRepository.class);
        mainService.setCategoryRepository(categoryRepositoryMock);

        Category category = new Category();
        category.setId(1L);
        List<Category> categoryList = List.of(category);

        Mockito.when(categoryRepositoryMock.findAll()).thenReturn(categoryList);

        mainService.GetAllCategories();

    }

    @Test
    public void GetUserTest(){

        MainServiceImpl mainService = new MainServiceImpl();
        UserRepository userRepositoryMock = mock(UserRepository.class);
        mainService.setUserRepository(userRepositoryMock);

        User user = new User();
        user.setId(1L);
        user.setLogin("name");
        List<User> userList = List.of(user);

        Mockito.when(userRepositoryMock.findAll()).thenReturn(userList);

        mainService.GetUser(user.getLogin());

    }

    @Test
    public void GetSearchProductsTest() {

        MainServiceImpl mainService = new MainServiceImpl();
        ProductRepository productRepositoryMock = mock(ProductRepository.class);
        mainService.setProductRepository(productRepositoryMock);

        Product product = new Product();
        product.setId(1L);
        List<Product> productList = List.of(product);

        Pageable productPage =  PageRequest.of(Integer.parseInt("1"), 10, Sort.by("name"));
        Mockito.when(productRepositoryMock.findByNameLike(String.format("%%%s%%","searchingProduct"), productPage)).thenReturn(productList);

//        mainService.GetSearchProducts("product","1",10, "name");
    }

    @Test
    public void GetAllProductsCountTest(){

        MainServiceImpl mainService = new MainServiceImpl();
        ProductRepository productRepositoryMock = mock(ProductRepository.class);
        mainService.setProductRepository(productRepositoryMock);

        Product product = new Product();
        product.setId(1L);
        List<Product> productList = List.of(product);
        Mockito.when(productRepositoryMock.count()).thenReturn(1L);

        Assert.assertEquals(mainService.GetAllProductsCount(),1L);

    }

    @Test
    public void GetSearchProductsCountTest(){

        MainServiceImpl mainService = new MainServiceImpl();
        ProductRepository productRepositoryMock = mock(ProductRepository.class);
        mainService.setProductRepository(productRepositoryMock);

        Product product = new Product();
        product.setId(1L);
        product.setName("name");
        List<Product> productList = List.of(product);
        String name = "name";

        Mockito.when(productRepositoryMock.findByNameLike(String.format("%%%s%%", name))).thenReturn(productList);

        Assert.assertEquals(mainService.GetSearchProductsCount(name),1);
    }

    @Test
    public void GetAllAttributesTEst(){

        MainServiceImpl mainService = new MainServiceImpl();
        ProductAttrValueRepository productAttrValueRepositoryMock = mock(ProductAttrValueRepository.class);
        mainService.setProductAttrValueRepository(productAttrValueRepositoryMock);
        //

        ProductAttrValue productAttrValue = new ProductAttrValue();
        productAttrValue.setId(1L);
        List<ProductAttrValue> productAttrValueList = List.of(productAttrValue);
        List<ProductAttrValue> productAttrValueLinkedList = new LinkedList<>(productAttrValueList);

        Mockito.when(productAttrValueRepositoryMock.findAll()).thenReturn(productAttrValueLinkedList);

        mainService.GetAllAttributes();
    }
}