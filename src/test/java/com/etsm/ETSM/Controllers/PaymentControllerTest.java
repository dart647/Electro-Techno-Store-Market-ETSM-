package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.Category;
import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Models.UserInfo;
import com.etsm.ETSM.Services.HeaderService;
import com.etsm.ETSM.Services.MainService;
import com.etsm.ETSM.Services.ShoppingCartService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Categories;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class PaymentControllerTest {

    @Test
    public void paymentTest(){

        PaymentController paymentController = new PaymentController();
        MainService mainServiceMock = mock(MainService.class);
        HeaderService headerServiceMock = mock(HeaderService.class);

        paymentController.setHeaderService(headerServiceMock);
        paymentController.setMainService(mainServiceMock);

        ModelAndView m1 = new ModelAndView();
        m1.setViewName("name");
        HttpSession httpSession = new MockHttpSession();
        httpSession.setAttribute("totalOrderPrice",10);

        Category category = new Category();
        category.setId(1L);
        List<Category> categoryList = List.of(category);
        Mockito.when(mainServiceMock.GetAllCategories()).thenReturn(categoryList);
        Mockito.when(headerServiceMock.getHeaderRole()).thenReturn("ADMIN");

       ModelAndView m2 = paymentController.payment(m1,httpSession);
        Assert.assertEquals(m1,m2);
    }

    @Test(expected=NullPointerException.class)
    public void chargeCardTest() throws Exception {

        PaymentController paymentController = new PaymentController();
        MainService mainServiceMock = mock(MainService.class);
        HeaderService headerServiceMock = mock(HeaderService.class);
        ShoppingCartService shoppingCartServiceMock = mock(ShoppingCartService.class);


        paymentController.setHeaderService(headerServiceMock);
        paymentController.setMainService(mainServiceMock);
        paymentController.setShoppingCartService(shoppingCartServiceMock);

        HttpServletRequest request = new MockHttpServletRequest();
        HttpSession httpSession = new MockHttpSession();
        request.setAttribute("stripeToken",1);
        request.setAttribute("amount",2);

        User user = new User();
        user.setId(1L);
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1L);
        userInfo.setUser_id(user);
        user.setUserInfo(userInfo);
        Mockito.when(headerServiceMock.getUser()).thenReturn(user);
        shoppingCartServiceMock.performOrder(httpSession,userInfo);
        String s = paymentController.chargeCard(request,httpSession);

        Assert.assertEquals(s,"redirect:/orders?orderDone=true");





    }

}