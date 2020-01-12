package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.*;
import com.etsm.ETSM.Services.HeaderService;
import com.etsm.ETSM.Services.ProductService;
import com.etsm.ETSM.Services.UserInformationService;
import com.etsm.ETSM.Services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class UserControllerTest {
    @Test
    public void editAuthTest(){

        UserService userServiceMock = mock(UserService.class);
        UserInformationService userInformationServiceMock = mock(UserInformationService.class);
        HeaderService headerServiceMock = mock(HeaderService.class);
        ProductService productServiceMock = mock(ProductService.class);
        UserController userController =
                new UserController(userServiceMock,userInformationServiceMock,
                        headerServiceMock,productServiceMock);
        userController.setUserInformationService(userInformationServiceMock);
        userController.setUserService(userServiceMock);
        userController.setHeaderService(headerServiceMock);
        userController.setProductService(productServiceMock);

        User user = new User();
        user.setId(1L);
        user.setRoles(Collections.singleton(Role.ADMIN));User finalUserForRole = user;
        Principal principal = () -> finalUserForRole.getUsername();
        headerServiceMock.setHeader(principal);

        Mockito.when(headerServiceMock.getUser()).thenReturn(user);
        Mockito.when(headerServiceMock.getHeaderRole()).thenReturn("ADMIN");

        Category category = new Category();
        category.setId(1L);
        List<Category> categoryList = List.of(category);
        Mockito.when(headerServiceMock.getHeaderCategories()).thenReturn(categoryList);

        ModelAndView m1 = userController.editAuth(principal);
        assertEquals(m1.getViewName(),"/auth/editAuth");

    }

    @Test
    public void StringEditAuthTest(){
        UserService userServiceMock = mock(UserService.class);
        UserInformationService userInformationServiceMock = mock(UserInformationService.class);
        HeaderService headerServiceMock = mock(HeaderService.class);
        ProductService productServiceMock = mock(ProductService.class);
        UserController userController =
                new UserController(userServiceMock,userInformationServiceMock,
                        headerServiceMock,productServiceMock);
        userController.setUserInformationService(userInformationServiceMock);
        userController.setUserService(userServiceMock);
        userController.setHeaderService(headerServiceMock);
        userController.setProductService(productServiceMock);

        HttpSession httpSession = new MockHttpSession();
        User user = new User();
        user.setId(1L);
        user.setUsername("name");
        user.setRoles(Collections.singleton(Role.ADMIN));User finalUserForRole = user;
        Principal principal = () -> finalUserForRole.getUsername();
        headerServiceMock.setHeader(principal);

        Mockito.when((User)userServiceMock.loadUserByUsername(principal.getName())).thenReturn(user);

        String s = userController.editAuth(user,principal,httpSession);

        assertEquals(s,"/auth/editAuth");

        Mockito.when(userInformationServiceMock.editUserAuth(user,user)).thenReturn(true);
        //httpSession.invalidate();
        String s1 = userController.editAuth(user,principal,httpSession);

        assertEquals(s1,"redirect:/login");



    }

    @Test
    public void addUserInfoTest(){
        UserService userServiceMock = mock(UserService.class);
        UserInformationService userInformationServiceMock = mock(UserInformationService.class);
        HeaderService headerServiceMock = mock(HeaderService.class);
        ProductService productServiceMock = mock(ProductService.class);
        UserController userController =
                new UserController(userServiceMock,userInformationServiceMock,
                        headerServiceMock,productServiceMock);
        userController.setUserInformationService(userInformationServiceMock);
        userController.setUserService(userServiceMock);
        userController.setHeaderService(headerServiceMock);
        userController.setProductService(productServiceMock);

        HttpSession httpSession = new MockHttpSession();
        User user = new User();
        user.setId(1L);
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1L);
        user.setUserInfo(userInfo);
        user.setUsername("name");
        user.setRoles(Collections.singleton(Role.ADMIN));User finalUserForRole = user;
        Principal principal = () -> finalUserForRole.getUsername();
        headerServiceMock.setHeader(principal);

        Mockito.when(headerServiceMock.getUser()).thenReturn(user);
        Mockito.when(headerServiceMock.getHeaderRole()).thenReturn("ADMIN");
        Category category = new Category();
        category.setId(1L);
        List<Category> categoryList = List.of(category);
        Mockito.when(headerServiceMock.getHeaderCategories()).thenReturn(categoryList);

        ModelAndView m1 = userController.addUserInfo(principal);

        assertEquals(m1.getViewName(),"/auth/addUserInfo");


    }

    @Test
    public void StringAddUserInfoTest(){
        UserService userServiceMock = mock(UserService.class);
        UserInformationService userInformationServiceMock = mock(UserInformationService.class);
        HeaderService headerServiceMock = mock(HeaderService.class);
        ProductService productServiceMock = mock(ProductService.class);
        UserController userController =
                new UserController(userServiceMock,userInformationServiceMock,
                        headerServiceMock,productServiceMock);
        userController.setUserInformationService(userInformationServiceMock);
        userController.setUserService(userServiceMock);
        userController.setHeaderService(headerServiceMock);
        userController.setProductService(productServiceMock);

        HttpSession httpSession = new MockHttpSession();
        User user = new User();
        user.setId(1L);
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1L);
        user.setUserInfo(userInfo);
        user.setUsername("name");
        user.setRoles(Collections.singleton(Role.ADMIN));
        User finalUserForRole = user;
        Principal principal = () -> finalUserForRole.getUsername();
        headerServiceMock.setHeader(principal);

        Mockito.when(userInformationServiceMock.addUserInfo(user,userInfo)).thenReturn(true);
        String s = userController.addUserInfo(userInfo,principal);
//        Assert.assertEquals(s,"redirect:/user");

        UserInfo userInfo1 = new UserInfo();
        userInfo1.setId(3L);

        Mockito.when(userInformationServiceMock.addUserInfo(user,userInfo)).thenReturn(false);
        String s2 = userController.addUserInfo(null,principal);
        assertEquals(s2,"/auth/addUserInfo");

    }



    @Test
    public void deleteUserTest (){
        UserService userServiceMock = mock(UserService.class);
        UserInformationService userInformationServiceMock = mock(UserInformationService.class);
        HeaderService headerServiceMock = mock(HeaderService.class);
        ProductService productServiceMock = mock(ProductService.class);
        UserController userController =
                new UserController(userServiceMock,userInformationServiceMock,
                        headerServiceMock,productServiceMock);
        userController.setUserInformationService(userInformationServiceMock);
        userController.setUserService(userServiceMock);
        userController.setHeaderService(headerServiceMock);
        userController.setProductService(productServiceMock);

        HttpSession httpSession = new MockHttpSession();
        User user = new User();
        user.setId(1L);
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1L);
        user.setUserInfo(userInfo);
        user.setUsername("name");
        user.setRoles(Collections.singleton(Role.ADMIN));
        User finalUserForRole = user;
        Principal principal = () -> finalUserForRole.getUsername();
        headerServiceMock.setHeader(principal);

        Mockito.when((User)userServiceMock.loadUserByUsername(principal.getName())).thenReturn(user);
        String s = userController.deleteUser(principal,httpSession);
        assertEquals(s,"redirect:/");

    }

    @Test
    public void showOrdersTest(){
        UserService userServiceMock = mock(UserService.class);
        UserInformationService userInformationServiceMock = mock(UserInformationService.class);
        HeaderService headerServiceMock = mock(HeaderService.class);
        ProductService productServiceMock = mock(ProductService.class);
        UserController userController =
                new UserController(userServiceMock,userInformationServiceMock,
                        headerServiceMock,productServiceMock);
        userController.setUserInformationService(userInformationServiceMock);
        userController.setUserService(userServiceMock);
        userController.setHeaderService(headerServiceMock);
        userController.setProductService(productServiceMock);

        HttpSession httpSession = new MockHttpSession();
        User user = new User();
        user.setId(1L);
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1L);
        user.setUserInfo(userInfo);
        user.setUsername("name");
        user.setRoles(Collections.singleton(Role.ADMIN));
        User finalUserForRole = user;
        Principal principal = () -> finalUserForRole.getUsername();
        headerServiceMock.setHeader(principal);

        String orderDone = "orderDone";
//        Mockito.when(headerServiceMock.getUser().getUserInfo()).thenReturn(userInfo);
        Mockito.when(headerServiceMock.getUser()).thenReturn(user);
        Mockito.when(headerServiceMock.getHeaderRole()).thenReturn("ADMIN");
        Category category = new Category();
        category.setId(1L);
        List<Category> categoryList = List.of(category);
        Mockito.when(headerServiceMock.getHeaderCategories()).thenReturn(categoryList);
        Sales sales = new Sales();
        sales.setId(1L);
        List<Sales> salesList = List.of(sales);
        Mockito.when(productServiceMock.findSalesByUser(userInfo)).thenReturn(salesList);

        ModelAndView m1 = userController.showOrders(orderDone,httpSession,principal);
        assertEquals(m1.getViewName(),"/auth/orders");
    }


    @Test//(expected = NullPointerException.class)
    public void ShowOrderTest(){

        UserService userServiceMock = mock(UserService.class);
        UserInformationService userInformationServiceMock = mock(UserInformationService.class);
        HeaderService headerServiceMock = mock(HeaderService.class);
        ProductService productServiceMock = mock(ProductService.class);
        UserController userController =
                new UserController(userServiceMock,userInformationServiceMock,
                        headerServiceMock,productServiceMock);
        userController.setUserInformationService(userInformationServiceMock);
        userController.setUserService(userServiceMock);
        userController.setHeaderService(headerServiceMock);
        userController.setProductService(productServiceMock);

        HttpSession httpSession = new MockHttpSession();
        User user = new User();
        user.setId(1L);
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1L);
        user.setUserInfo(userInfo);
        user.setUsername("name");
        user.setRoles(Collections.singleton(Role.ADMIN));
        User finalUserForRole = user;
        Principal principal = () -> finalUserForRole.getUsername();
        headerServiceMock.setHeader(principal);

        String code = "1";


        Sales sales = new Sales();
        sales.setId(1L);

        List<Sales> salesList = List.of(sales);
        Optional<Sales> optionalSales = Optional.of(sales);
        Optional<Sales> test =productServiceMock.findSalesById(sales.getId());
        //Mockito.when(productServiceMock.findSalesById(Long.parseLong(code)).get()).thenReturn(sales);
//        Mockito.when((headerServiceMock.getUser().getUserInfo().getSales())).thenReturn(salesList);

        Mockito.when(headerServiceMock.getUser()).thenReturn(user);
        Mockito.when(headerServiceMock.getUser()).thenReturn(user);
        Mockito.when(headerServiceMock.getHeaderRole()).thenReturn("ADMIN");
        Category category = new Category();
        category.setId(1L);
        List<Category> categoryList = List.of(category);
        Mockito.when(headerServiceMock.getHeaderCategories()).thenReturn(categoryList);

       // ModelAndView m1 = userController.showOrder("1",principal);
       // assertEquals(m1.getViewName(),"auth/order");


       // ModelAndView m2 = userController.showOrder("12412412",principal);
       // assertEquals(m2.getViewName(),"errors/404");

    }
}