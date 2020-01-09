package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.Category;
import com.etsm.ETSM.Models.Product;
import com.etsm.ETSM.Models.Role;
import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Services.HeaderService;
import com.etsm.ETSM.Services.MainService;
import com.etsm.ETSM.Services.ShoppingCartService;
import com.etsm.ETSM.Services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;

public class MainControllerTest {

@Test
    public void MainPageTest(){

    MainService mainServiceMock = mock(MainService.class);
    UserService userServiceMock = mock(UserService.class);
    MainController mainController = new MainController(mainServiceMock,userServiceMock);
    mainController.setService(mainServiceMock);
    HeaderService headerServiceMock = mock(HeaderService.class);
    mainController.setHeaderService(headerServiceMock);

    User user = new User();
    user.setId(1L);
    user.setRoles(Collections.singleton(Role.ADMIN));
    Optional<User> userOptional = Optional.of(user);


    User finalUserForRole = user;
    Principal principal = () -> finalUserForRole.getUsername();
    headerServiceMock.setHeader(principal);
    Category category = new Category();
    category.setId(1L);
    List<Category> categoryList = List.of(category);
    Product product = new Product();
    product.setId(1L);
    List<Product> productList = List.of(product);

    Mockito.when(mainServiceMock.GetAllCategories()).thenReturn(categoryList);
    Mockito.when(mainServiceMock.SetRecommendations()).thenReturn(productList);
    Mockito.when(headerServiceMock.getHeaderRole()).thenReturn("ADMIN");
    ModelAndView m1 = mainController.MainPage(principal);

    Assert.assertEquals(m1.getViewName(),"/main");

}

@Test
    public void SearchPageTest(){

    MainService mainServiceMock = mock(MainService.class);
    UserService userServiceMock = mock(UserService.class);
    MainController mainController = new MainController(mainServiceMock,userServiceMock);
    mainController.setService(mainServiceMock);
    HeaderService headerServiceMock = mock(HeaderService.class);
    mainController.setHeaderService(headerServiceMock);

    User user = new User();
    user.setId(1L);
    user.setRoles(Collections.singleton(Role.ADMIN));
    Optional<User> userOptional = Optional.of(user);


    User finalUserForRole = user;
    Principal principal = () -> finalUserForRole.getUsername();
    headerServiceMock.setHeader(principal);

    List<Integer> pages = new ArrayList<>();
    int maxProductsInPage = 1;
    String search ="search";
    String page = "page";


    Product product = new Product();
    product.setId(1L);
    List<Product> productList = List.of(product);

    Mockito.when(mainServiceMock.GetSearchProducts(search,page,maxProductsInPage, "name")).thenReturn(productList);
    Mockito.when(mainServiceMock.GetSearchProductsCount(search)).thenReturn(2L);

    for (int i =0; i<2;i++){
        pages.add(i);
    }

    Category category = new Category();
    category.setId(1L);
    List<Category> categoryList = List.of(category);
    Mockito.when(mainServiceMock.GetAllCategories()).thenReturn(categoryList);
    Mockito.when(headerServiceMock.getHeaderRole()).thenReturn("ADMIN");

    ModelAndView m1 = mainController.SearchPage(page,search,principal);

    Assert.assertEquals(m1.getViewName(),"/search");

}
/*
@Test
    public void MainPageWithSearchTest(){

    MainService mainServiceMock = mock(MainService.class);
    UserService userServiceMock = mock(UserService.class);
    MainController mainController = new MainController(mainServiceMock,userServiceMock);
    mainController.setService(mainServiceMock);
    HeaderService headerServiceMock = mock(HeaderService.class);
    mainController.setHeaderService(headerServiceMock);

    User user = new User();
    user.setId(1L);
    user.setRoles(Collections.singleton(Role.ADMIN));
    Optional<User> userOptional = Optional.of(user);


    User finalUserForRole = user;
    Principal principal = () -> finalUserForRole.getUsername();
    headerServiceMock.setHeader(principal);

    List<Integer> pages = new ArrayList<>();
    int maxProductsInPage = 1;
    String searching ="searching";
    String page = "page";


    Product product = new Product();
    product.setId(1L);
    List<Product> productList = List.of(product);

    Mockito.when(mainServiceMock.GetSearchProducts(searching,page,maxProductsInPage)).thenReturn(productList);
    Mockito.when(mainServiceMock.GetSearchProductsCount(searching)).thenReturn(2L);

    for (int i =0; i<2;i++){
        pages.add(i);
    }

    Category category = new Category();
    category.setId(1L);
    List<Category> categoryList = List.of(category);

    Mockito.when(mainServiceMock.SetRecommendations()).thenReturn(productList);
    Mockito.when(mainServiceMock.GetAllCategories()).thenReturn(categoryList);
    Mockito.when(headerServiceMock.getHeaderRole()).thenReturn("ADMIN");

    ModelAndView m1 = mainController.MainPageWithSearch(page,searching,principal);

    Assert.assertEquals(m1.getViewName(),"/main");

}
*/
@Test
    public void UserCabinetTest(){
    MainService mainServiceMock = mock(MainService.class);
    UserService userServiceMock = mock(UserService.class);
    MainController mainController = new MainController(mainServiceMock,userServiceMock);
    mainController.setService(mainServiceMock);
    HeaderService headerServiceMock = mock(HeaderService.class);
    mainController.setHeaderService(headerServiceMock);

    User user = new User();
    user.setId(1L);
    user.setRoles(Collections.singleton(Role.ADMIN));
    Optional<User> userOptional = Optional.of(user);


    User finalUserForRole = user;
    Principal principal = () -> finalUserForRole.getUsername();
    headerServiceMock.setHeader(principal);

    List<Integer> pages = new ArrayList<>();
    int maxProductsInPage = 1;
    String searching ="searching";
    String page = "page";


    Product product = new Product();
    product.setId(1L);
    List<Product> productList = List.of(product);

    Mockito.when(mainServiceMock.GetSearchProducts(searching,page,maxProductsInPage, "name")).thenReturn(productList);
    Mockito.when(mainServiceMock.GetSearchProductsCount(searching)).thenReturn(2L);

    for (int i =0; i<2;i++){
        pages.add(i);
    }

    Category category = new Category();
    category.setId(1L);
    List<Category> categoryList = List.of(category);

    Mockito.when(mainServiceMock.SetRecommendations()).thenReturn(productList);
    Mockito.when(mainServiceMock.GetAllCategories()).thenReturn(categoryList);
    Mockito.when(headerServiceMock.getHeaderRole()).thenReturn("ADMIN");
    Mockito.when(headerServiceMock.getUser()).thenReturn(user);

    ModelAndView m1 = mainController.UserCabinet(principal);

    Assert.assertEquals(m1.getViewName(),"/auth/userCabinet");
}

@Test
    public void AdminTest(){

    MainService mainServiceMock = mock(MainService.class);
    UserService userServiceMock = mock(UserService.class);
    MainController mainController = new MainController(mainServiceMock,userServiceMock);
    mainController.setService(mainServiceMock);
    HeaderService headerServiceMock = mock(HeaderService.class);
    mainController.setHeaderService(headerServiceMock);

    User user = new User();
    user.setId(1L);
    user.setRoles(Collections.singleton(Role.ADMIN));
    Optional<User> userOptional = Optional.of(user);


    User finalUserForRole = user;
    Principal principal = () -> finalUserForRole.getUsername();
    headerServiceMock.setHeader(principal);

    List<Integer> pages = new ArrayList<>();
    int maxProductsInPage = 1;
    String searching ="searching";
    String page = "page";


    Product product = new Product();
    product.setId(1L);
    List<Product> productList = List.of(product);

    Mockito.when(mainServiceMock.GetSearchProducts(searching,page,maxProductsInPage, "name")).thenReturn(productList);
    Mockito.when(mainServiceMock.GetSearchProductsCount(searching)).thenReturn(2L);

    for (int i =0; i<2;i++){
        pages.add(i);
    }

    Category category = new Category();
    category.setId(1L);
    List<Category> categoryList = List.of(category);

    Mockito.when(mainServiceMock.SetRecommendations()).thenReturn(productList);
    Mockito.when(mainServiceMock.GetAllCategories()).thenReturn(categoryList);
    Mockito.when(headerServiceMock.getHeaderRole()).thenReturn("ADMIN");
    Mockito.when(headerServiceMock.getUser()).thenReturn(user);

    ModelAndView m1 = mainController.Admin(principal);

    Assert.assertEquals(m1.getViewName(),"/auth/admin");
}

    @Test
    public void AboutTest(){

        MainService mainServiceMock = mock(MainService.class);
        UserService userServiceMock = mock(UserService.class);
        MainController mainController = new MainController(mainServiceMock,userServiceMock);
        mainController.setService(mainServiceMock);
        HeaderService headerServiceMock = mock(HeaderService.class);
        mainController.setHeaderService(headerServiceMock);

        User user = new User();
        user.setId(1L);
        user.setRoles(Collections.singleton(Role.ADMIN));
        Optional<User> userOptional = Optional.of(user);


        User finalUserForRole = user;
        Principal principal = () -> finalUserForRole.getUsername();
        headerServiceMock.setHeader(principal);

        List<Integer> pages = new ArrayList<>();
        int maxProductsInPage = 1;
        String searching ="searching";
        String page = "page";


        Product product = new Product();
        product.setId(1L);
        List<Product> productList = List.of(product);

        Mockito.when(mainServiceMock.GetSearchProducts(searching,page,maxProductsInPage, "name")).thenReturn(productList);
        Mockito.when(mainServiceMock.GetSearchProductsCount(searching)).thenReturn(2L);

        for (int i =0; i<2;i++){
            pages.add(i);
        }

        Category category = new Category();
        category.setId(1L);
        List<Category> categoryList = List.of(category);

        Mockito.when(mainServiceMock.SetRecommendations()).thenReturn(productList);
        Mockito.when(mainServiceMock.GetAllCategories()).thenReturn(categoryList);
        Mockito.when(headerServiceMock.getHeaderRole()).thenReturn("ADMIN");
        Mockito.when(headerServiceMock.getUser()).thenReturn(user);

        ModelAndView m1 = mainController.About(principal);

        Assert.assertEquals(m1.getViewName(),"/about");
    }

    @Test
    public void FeedbackTest(){

        MainService mainServiceMock = mock(MainService.class);
        UserService userServiceMock = mock(UserService.class);
        MainController mainController = new MainController(mainServiceMock,userServiceMock);
        mainController.setService(mainServiceMock);
        HeaderService headerServiceMock = mock(HeaderService.class);
        mainController.setHeaderService(headerServiceMock);

        User user = new User();
        user.setId(1L);
        user.setRoles(Collections.singleton(Role.ADMIN));
        Optional<User> userOptional = Optional.of(user);


        User finalUserForRole = user;
        Principal principal = () -> finalUserForRole.getUsername();
        headerServiceMock.setHeader(principal);

        List<Integer> pages = new ArrayList<>();
        int maxProductsInPage = 1;
        String searching ="searching";
        String page = "page";


        Product product = new Product();
        product.setId(1L);
        List<Product> productList = List.of(product);

        Mockito.when(mainServiceMock.GetSearchProducts(searching,page,maxProductsInPage, "name")).thenReturn(productList);
        Mockito.when(mainServiceMock.GetSearchProductsCount(searching)).thenReturn(2L);

        for (int i =0; i<2;i++){
            pages.add(i);
        }

        Category category = new Category();
        category.setId(1L);
        List<Category> categoryList = List.of(category);

        Mockito.when(mainServiceMock.SetRecommendations()).thenReturn(productList);
        Mockito.when(mainServiceMock.GetAllCategories()).thenReturn(categoryList);
        Mockito.when(headerServiceMock.getHeaderRole()).thenReturn("ADMIN");
        Mockito.when(headerServiceMock.getUser()).thenReturn(user);
        Mockito.when(headerServiceMock.getHeaderCategories()).thenReturn(categoryList);

        ModelAndView m1 = mainController.Feedback(principal);

        Assert.assertEquals(m1.getViewName(),"/feedback");
    }

    @Test
    public void AddressesTest(){

        MainService mainServiceMock = mock(MainService.class);
        UserService userServiceMock = mock(UserService.class);
        MainController mainController = new MainController(mainServiceMock,userServiceMock);
        mainController.setService(mainServiceMock);
        HeaderService headerServiceMock = mock(HeaderService.class);
        mainController.setHeaderService(headerServiceMock);

        User user = new User();
        user.setId(1L);
        user.setRoles(Collections.singleton(Role.ADMIN));
        Optional<User> userOptional = Optional.of(user);


        User finalUserForRole = user;
        Principal principal = () -> finalUserForRole.getUsername();
        headerServiceMock.setHeader(principal);

        List<Integer> pages = new ArrayList<>();
        int maxProductsInPage = 1;
        String searching ="searching";
        String page = "page";


        Product product = new Product();
        product.setId(1L);
        List<Product> productList = List.of(product);

        Mockito.when(mainServiceMock.GetSearchProducts(searching,page,maxProductsInPage, "name")).thenReturn(productList);
        Mockito.when(mainServiceMock.GetSearchProductsCount(searching)).thenReturn(2L);

        for (int i =0; i<2;i++){
            pages.add(i);
        }

        Category category = new Category();
        category.setId(1L);
        List<Category> categoryList = List.of(category);

        Mockito.when(mainServiceMock.SetRecommendations()).thenReturn(productList);
        Mockito.when(mainServiceMock.GetAllCategories()).thenReturn(categoryList);
        Mockito.when(headerServiceMock.getHeaderRole()).thenReturn("ADMIN");
        Mockito.when(headerServiceMock.getUser()).thenReturn(user);
        Mockito.when(headerServiceMock.getHeaderCategories()).thenReturn(categoryList);

        ModelAndView m1 = mainController.Addresses(principal);

        Assert.assertEquals(m1.getViewName(),"/ourAddresses");
    }

    @Test(expected=NullPointerException.class)
    public void LoginTest(){

        MainService mainServiceMock = mock(MainService.class);
        UserService userServiceMock = mock(UserService.class);
        MainController mainController = new MainController(mainServiceMock,userServiceMock);
        mainController.setService(mainServiceMock);
        HeaderService headerServiceMock = mock(HeaderService.class);
        mainController.setHeaderService(headerServiceMock);

        User user = new User();
        user.setId(1L);
        user.setRoles(Collections.singleton(Role.ADMIN));
        Optional<User> userOptional = Optional.of(user);


        User finalUserForRole = user;
        Principal principal = () -> finalUserForRole.getUsername();
        headerServiceMock.setHeader(principal);

        List<Integer> pages = new ArrayList<>();
        int maxProductsInPage = 1;
        String searching ="searching";
        String page = "page";


        Product product = new Product();
        product.setId(1L);
        List<Product> productList = List.of(product);

        Mockito.when(mainServiceMock.GetSearchProducts(searching,page,maxProductsInPage, "name")).thenReturn(productList);
        Mockito.when(mainServiceMock.GetSearchProductsCount(searching)).thenReturn(2L);

        for (int i =0; i<2;i++){
            pages.add(i);
        }

        Category category = new Category();
        category.setId(1L);
        List<Category> categoryList = List.of(category);

        Mockito.when(mainServiceMock.SetRecommendations()).thenReturn(productList);
        Mockito.when(mainServiceMock.GetAllCategories()).thenReturn(categoryList);
        Mockito.when(headerServiceMock.getHeaderRole()).thenReturn("ADMIN");
        Mockito.when(headerServiceMock.getUser()).thenReturn(user);
        Mockito.when(headerServiceMock.getHeaderCategories()).thenReturn(categoryList);

        Model model = null;
//        model.addAttribute("view","login");
        //model.addAttribute();



        String s1 = mainController.loginPage(model,principal);

        Assert.assertEquals(s1,"login");
    }

    @Test
    public void addToCartTest(){

        MainService mainServiceMock = mock(MainService.class);
        UserService userServiceMock = mock(UserService.class);
        MainController mainController = new MainController(mainServiceMock,userServiceMock);
        mainController.setService(mainServiceMock);
        HeaderService headerServiceMock = mock(HeaderService.class);
        mainController.setHeaderService(headerServiceMock);

        ShoppingCartService shoppingCartServiceMock = mock(ShoppingCartService.class);
        mainController.setShoppingCartService(shoppingCartServiceMock);

        String code = "code";
        HttpSession httpSession = new MockHttpSession();
        Mockito.when(shoppingCartServiceMock.addItemToCart(code,httpSession)).thenReturn(true);

        String s = mainController.addToCart(code, httpSession);

        Assert.assertEquals(s,"redirect:/orderSuggestion");
    }

    @Test
    public void getCardPage(){
        MainService mainServiceMock = mock(MainService.class);
        UserService userServiceMock = mock(UserService.class);
        MainController mainController = new MainController(mainServiceMock,userServiceMock);
        mainController.setService(mainServiceMock);
        HeaderService headerServiceMock = mock(HeaderService.class);
        mainController.setHeaderService(headerServiceMock);

        User user = new User();
        user.setId(1L);
        user.setRoles(Collections.singleton(Role.ADMIN));
        Optional<User> userOptional = Optional.of(user);


        User finalUserForRole = user;
        Principal principal = () -> finalUserForRole.getUsername();
        headerServiceMock.setHeader(principal);

        List<Integer> pages = new ArrayList<>();
        int maxProductsInPage = 1;
        String searching ="searching";
        String page = "page";


        Product product = new Product();
        product.setId(1L);
        List<Product> productList = List.of(product);

        Mockito.when(mainServiceMock.GetSearchProducts(searching,page,maxProductsInPage, "name")).thenReturn(productList);
        Mockito.when(mainServiceMock.GetSearchProductsCount(searching)).thenReturn(2L);

        for (int i =0; i<2;i++){
            pages.add(i);
        }

        Category category = new Category();
        category.setId(1L);
        List<Category> categoryList = List.of(category);

        Mockito.when(mainServiceMock.SetRecommendations()).thenReturn(productList);
        Mockito.when(mainServiceMock.GetAllCategories()).thenReturn(categoryList);
        Mockito.when(headerServiceMock.getHeaderRole()).thenReturn("ADMIN");
        Mockito.when(headerServiceMock.getUser()).thenReturn(user);
        Mockito.when(headerServiceMock.getHeaderCategories()).thenReturn(categoryList);

        ModelAndView m1 = mainController.getCartPage(principal);

        Assert.assertEquals(m1.getViewName(),"/catalog/orderSuggestion");

    }


}