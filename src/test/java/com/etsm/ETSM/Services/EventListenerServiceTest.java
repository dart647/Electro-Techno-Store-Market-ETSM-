package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.CartItem;
import com.etsm.ETSM.Models.Product;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationEvent;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class EventListenerServiceTest {
/*
    @Test
    public void onApplicationEventTest(){

        EventListenerService eventListenerService = new EventListenerService();
        HttpSession httpSessionMock = mock(HttpSession.class);
        ProductService productServiceMock = mock(ProductService.class);
        eventListenerService.setHttpSession(httpSessionMock);
        eventListenerService.setProductService(productServiceMock);

        HttpSession httpSession = new MockHttpSession();

       // Object sourse = mock(Object.class);
//        ApplicationEvent applicationEvent = (ApplicationEvent) new EventObject(sourse);
        ApplicationEvent applicationEvent = mock(ApplicationEvent.class);
        httpSession.setAttribute("cart", null);
        Product product = new Product();
        product.setId(1L);
        product.setName("cart");
        int quantity = 1;
        boolean isReserve = false;

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        List<CartItem> cartItemList = List.of(cartItem);
        List<CartItem> cart = new ArrayList<>();
        cart.add(new CartItem(product,1));
        httpSession.setAttribute("cart", cart);

        Object test = cart.toArray();

     //   Object test = httpSessionMock.getAttribute("cart");
      //  List<CartItem> cartItemListTEST = (List<CartItem>) httpSessionMock.getAttribute("cart");

        Mockito.when(httpSessionMock.getAttribute("cart")).thenReturn(test);
        Mockito.when(productServiceMock.reserveItem(product,1,false)).thenReturn(false);

        eventListenerService.onApplicationEvent(applicationEvent);



    }
*/
}