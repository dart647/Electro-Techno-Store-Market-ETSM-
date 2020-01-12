package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.Category;
import com.etsm.ETSM.Models.Role;
import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Models.UserInfo;
import com.etsm.ETSM.Services.HeaderService;
import com.etsm.ETSM.Services.MainService;
import com.etsm.ETSM.Services.ShoppingCartService;
import com.etsm.ETSM.Services.UserInformationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Categories;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.validateMockitoUsage;

public class ShoppingCartControllerTest {

    @Test//(expected=NullPointerException.class)

    public void BasketTest() {

        ShoppingCartController shoppingCartController = new ShoppingCartController();
        HeaderService headerServiceMock = mock(HeaderService.class);
        ShoppingCartService shoppingCartServiceMock = mock(ShoppingCartService.class);
        MainService mainServiceMock = mock(MainService.class);
        shoppingCartController.setHeaderService(headerServiceMock);
        shoppingCartController.setShoppingCartService(shoppingCartServiceMock);
        shoppingCartController.setMainService(mainServiceMock);

        User user = new User();
        user.setId(1L);
        user.setRoles(Collections.singleton(Role.ADMIN));
        Optional<User> userOptional = Optional.of(user);


        User finalUserForRole = user;
        Principal principal = () -> finalUserForRole.getUsername();
        headerServiceMock.setHeader(principal);

        HttpSession httpSession = new MockHttpSession();


        Mockito.when(shoppingCartServiceMock.getTotalOrderPrice(httpSession)).thenReturn(true);
        Category category = new Category();
        category.setId(1L);
        List<Category> categoryList = List.of(category);
        Mockito.when(mainServiceMock.GetAllCategories()).thenReturn(categoryList);
        Mockito.when(headerServiceMock.getHeaderRole()).thenReturn("ADMIN");

        ModelAndView m1 = shoppingCartController.Basket(principal, httpSession);

        Assert.assertEquals(m1.getViewName(), "/auth/basket");
    }

    @Test//(expected=NullPointerException.class)
    public void removeProductFromCartTest() {
        ShoppingCartController shoppingCartController = new ShoppingCartController();
        HeaderService headerServiceMock = mock(HeaderService.class);
        ShoppingCartService shoppingCartServiceMock = mock(ShoppingCartService.class);
        MainService mainServiceMock = mock(MainService.class);
        shoppingCartController.setHeaderService(headerServiceMock);
        shoppingCartController.setMainService(mainServiceMock);
        shoppingCartController.setShoppingCartService(shoppingCartServiceMock);

        String code = "code";
        HttpSession httpSession = new MockHttpSession();
        Mockito.when(shoppingCartServiceMock.deleteItemFromCart(code, httpSession)).thenReturn(true);

        String s = shoppingCartController.removeProductFromCart(code, httpSession);

        Assert.assertEquals(s, "redirect:/basket?deleted=true");

    }



    @Test
    public void changeQuantityTest() {

        ShoppingCartController shoppingCartController = new ShoppingCartController();
        HeaderService headerServiceMock = mock(HeaderService.class);
        ShoppingCartService shoppingCartServiceMock = mock(ShoppingCartService.class);
        MainService mainServiceMock = mock(MainService.class);
        shoppingCartController.setHeaderService(headerServiceMock);
        shoppingCartController.setMainService(mainServiceMock);
        shoppingCartController.setShoppingCartService(shoppingCartServiceMock);

        String code = "code";
        String type = "type";
        HttpSession httpSession = new MockHttpSession();
        Mockito.when(shoppingCartServiceMock.changeQuantity(code, type, httpSession)).thenReturn(true);

        String s = shoppingCartController.changeQuantity(code, type, httpSession);

        Assert.assertEquals(s, "redirect:/basket");

    }

    @Test(expected = NullPointerException.class)
    public void createOrderTest() {

        ShoppingCartController shoppingCartController = new ShoppingCartController();
        HeaderService headerServiceMock = mock(HeaderService.class);
        ShoppingCartService shoppingCartServiceMock = mock(ShoppingCartService.class);
        MainService mainServiceMock = mock(MainService.class);
        shoppingCartController.setHeaderService(headerServiceMock);
        shoppingCartController.setShoppingCartService(shoppingCartServiceMock);
        shoppingCartController.setMainService(mainServiceMock);

        String stage = "stage";
        HttpSession httpSession = new MockHttpSession();
        User user = new User();
        user.setId(1L);
        user.setRoles(Collections.singleton(Role.ADMIN));


        User finalUserForRole = user;
        Principal principal = () -> finalUserForRole.getUsername();
        headerServiceMock.setHeader(principal);

        Category category = new Category();
        category.setId(1L);
        List<Category> categoryList = List.of(category);
        Mockito.when(mainServiceMock.GetAllCategories()).thenReturn(categoryList);
        Mockito.when(headerServiceMock.getHeaderRole()).thenReturn("ADMIN");
        UserInfo userInfo = new UserInfo();
        userInfo.setUser_id(user);

        Mockito.when(headerServiceMock.getUser()).thenReturn(user);

        ModelAndView m1 = shoppingCartController.createOrder(stage, httpSession);
        assertEquals(m1.getViewName(), "/auth/createOrder");

        //shoppingCartController.createOrder("userInfo",httpSession);
    }

    @Test
    public void clearCartTest(){

        ShoppingCartController shoppingCartController = new ShoppingCartController();
        HeaderService headerServiceMock = mock(HeaderService.class);
        ShoppingCartService shoppingCartServiceMock = mock(ShoppingCartService.class);
        MainService mainServiceMock = mock(MainService.class);
        shoppingCartController.setHeaderService(headerServiceMock);
        shoppingCartController.setShoppingCartService(shoppingCartServiceMock);
        shoppingCartController.setMainService(mainServiceMock);


        HttpSession httpSession = new MockHttpSession();
        //Mockito.when(shoppingCartServiceMock.clearCart(httpSession)).thenReturn()
        String s = shoppingCartController.clearCart(httpSession);
        assertEquals(s,"redirect:/basket");
    }

    @Test(expected = NullPointerException.class)
    public void createOrderTest2(){
        ShoppingCartController shoppingCartController = new ShoppingCartController();
        HeaderService headerServiceMock = mock(HeaderService.class);
        ShoppingCartService shoppingCartServiceMock = mock(ShoppingCartService.class);
        MainService mainServiceMock = mock(MainService.class);
        UserInformationService userInformationServiceMock = mock(UserInformationService.class);
        shoppingCartController.setUserInformationService(userInformationServiceMock);
        shoppingCartController.setHeaderService(headerServiceMock);
        shoppingCartController.setShoppingCartService(shoppingCartServiceMock);
        shoppingCartController.setMainService(mainServiceMock);

        User user = new User();
        user.setId(1L);
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1L);
        user.setUserInfo(userInfo);

        HttpSession httpSession = new MockHttpSession();
        Mockito.when(headerServiceMock.getUser()).thenReturn(user);

        ModelAndView m1 = shoppingCartController.createOrder(userInfo,false,httpSession);
        assertEquals(m1.getViewName(),"payment");

    }

    @Test(expected = NullPointerException.class)
    public void cancelOrderTest(){
        ShoppingCartController shoppingCartController = new ShoppingCartController();
        HeaderService headerServiceMock = mock(HeaderService.class);
        ShoppingCartService shoppingCartServiceMock = mock(ShoppingCartService.class);
        MainService mainServiceMock = mock(MainService.class);
        UserInformationService userInformationServiceMock = mock(UserInformationService.class);
        shoppingCartController.setUserInformationService(userInformationServiceMock);
        shoppingCartController.setHeaderService(headerServiceMock);
        shoppingCartController.setShoppingCartService(shoppingCartServiceMock);
        shoppingCartController.setMainService(mainServiceMock);
        User user = new User();
        user.setId(1L);
        user.setRoles(Collections.singleton(Role.ADMIN));


        User finalUserForRole = user;
        Principal principal = () -> finalUserForRole.getUsername();
        headerServiceMock.setHeader(principal);
        HttpSession httpSession = new MockHttpSession();

        ModelAndView m1 = shoppingCartController.cancelOrder(principal,httpSession);


    }

}
