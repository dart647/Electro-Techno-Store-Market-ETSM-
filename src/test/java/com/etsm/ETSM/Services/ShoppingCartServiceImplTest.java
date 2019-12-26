package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.*;
import com.etsm.ETSM.Repositories.LoyaltyRepository;
import com.etsm.ETSM.Repositories.SalesRepository;
import com.etsm.ETSM.Repositories.Sales_has_productRepository;
import com.etsm.ETSM.Repositories.UserInfoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.NotNull;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShoppingCartServiceImplTest {


    @Test //(expected=NullPointerException.class)
    public void addItemToCarttest(){
        ShoppingCartServiceImpl shoppingCartServiceImpl = new ShoppingCartServiceImpl();
        ProductService productServiceMock = mock(ProductService.class);
        shoppingCartServiceImpl.setProductService(productServiceMock);

        HttpSession httpSession = new MockHttpSession();
        String code = "1231";

        Product product = new Product();
        product.setId(1L);
        product.setName("name");

        httpSession.setAttribute("cart",null);




        Optional OptProd = Optional.of(product);

        when(productServiceMock.findProductById(product.getId())).thenReturn(null);

        Assert.assertFalse(shoppingCartServiceImpl.addItemToCart(code,httpSession));

        when(productServiceMock.findProductById(product.getId())).thenReturn(OptProd);

        List<CartItem> cart = new ArrayList<>();
        cart.add(new CartItem(product,1));
        httpSession.setAttribute("cart",cart);

        Assert.assertTrue(shoppingCartServiceImpl.addItemToCart("1",httpSession));
        //Assert.assertTrue(shoppingCartServiceImpl.addItemToCart("1",null));

    }

    @Test
    public void deleteItemFromCartTest(){
        ShoppingCartServiceImpl shoppingCartService = new ShoppingCartServiceImpl();
        ProductService productServiceMock = mock(ProductService.class);
        shoppingCartService.setProductService(productServiceMock);

        String code ="12";
        HttpSession httpSession = new MockHttpSession();

        Product product = new Product();
        product.setId(1L);
        product.setName("name");
        Optional OptProd = Optional.of(product);
        httpSession.setAttribute("cart",null);
        when(productServiceMock.findProductById(product.getId())).thenReturn(null);

        Assert.assertFalse(shoppingCartService.deleteItemFromCart(code,httpSession));

        List<CartItem> cart = new ArrayList<>();
        cart.add(new CartItem(product,1));
        httpSession.setAttribute("cart",cart);
        when(productServiceMock.findProductById(product.getId())).thenReturn(OptProd);
        Assert.assertTrue(shoppingCartService.deleteItemFromCart("1",httpSession));
        List<CartItem> cartTest = (List<CartItem>)httpSession.getAttribute("carrt");
        Assert.assertFalse(shoppingCartService.deleteItemFromCart("2",httpSession));

    }

    @Test
    public void changeQuantityTest(){
        ShoppingCartServiceImpl shoppingCartService = new ShoppingCartServiceImpl();
        ProductService productServiceMock = mock(ProductService.class);
        shoppingCartService.setProductService(productServiceMock);

        String code ="12";
        HttpSession httpSession = new MockHttpSession();
        String type = "plus";

        Product product = new Product();
        product.setId(1L);
        product.setName("name");
        Optional OptProd = Optional.of(product);
        httpSession.setAttribute("cart",null);
        when(productServiceMock.findProductById(product.getId())).thenReturn(null);
        Assert.assertFalse(shoppingCartService.changeQuantity(code,type,httpSession));
        List<CartItem> cart = new ArrayList<>();
        cart.add(new CartItem(product,2));
        httpSession.setAttribute("cart",cart);
        when(productServiceMock.findProductById(product.getId())).thenReturn(OptProd);
        Assert.assertTrue(shoppingCartService.changeQuantity("1",type,httpSession));
        Assert.assertFalse(shoppingCartService.changeQuantity("1","kavo",httpSession));

    }

    @Test
    public void getTotalOrderPriceTest(){
        ShoppingCartServiceImpl shoppingCartService = new ShoppingCartServiceImpl();
        ProductService productServiceMock = mock(ProductService.class);
        shoppingCartService.setProductService(productServiceMock);

        String code ="12";
        HttpSession httpSession = new MockHttpSession();
        String type = "plus";

        Product product = new Product();
        product.setId(1L);
        product.setName("name");
        Optional OptProd = Optional.of(product);
        httpSession.setAttribute("cart",null);
        when(productServiceMock.findProductById(product.getId())).thenReturn(null);
        Assert.assertFalse(shoppingCartService.getTotalOrderPrice(httpSession));

        List<CartItem> cart = new ArrayList<>();
        cart.add(new CartItem(product,2));
        httpSession.setAttribute("cart",cart);
        when(productServiceMock.findProductById(product.getId())).thenReturn(OptProd);
        Assert.assertTrue(shoppingCartService.getTotalOrderPrice(httpSession));

    }

    @Test
    public void clearCartTest(){
        ShoppingCartServiceImpl shoppingCartService = new ShoppingCartServiceImpl();
        ProductService productServiceMock = mock(ProductService.class);
        shoppingCartService.setProductService(productServiceMock);

        String code ="12";
        HttpSession httpSession = new MockHttpSession();
        String type = "plus";

        Product product = new Product();
        product.setId(1L);
        product.setName("name");
        Optional OptProd = Optional.of(product);
        httpSession.setAttribute("cart",null);
        List<CartItem> cart = new ArrayList<>();
        cart.add(new CartItem(product,2));
        httpSession.setAttribute("cart",cart);
        when(productServiceMock.findProductById(product.getId())).thenReturn(OptProd);
        shoppingCartService.clearCart(httpSession);

    }

    @Test
    public void performOrderTest(){
        ShoppingCartServiceImpl shoppingCartService = new ShoppingCartServiceImpl();

        ProductService productServiceMock = mock(ProductService.class);
        SalesRepository salesRepositoryMock = mock(SalesRepository.class);
        LoyaltyRepository loyaltyRepositoryMock = mock(LoyaltyRepository.class);

        shoppingCartService.setProductService(productServiceMock);
        shoppingCartService.setSalesRepository(salesRepositoryMock);
        shoppingCartService.setLoyaltyRepository(loyaltyRepositoryMock);


        String code ="12";
        HttpSession httpSession = new MockHttpSession();
        String type = "plus";
        User user = new User();
       user.setId(1L);

        UserInfo userInfo = new UserInfo();
        user.setUserInfo(userInfo);
        userInfo.setId(1L);
        userInfo.setUser_id(user);


        Loyalty loyalty = new Loyalty();
        loyalty.setId(1L);
        //loyalty.setId(user.getId());
        loyalty.setUserInfo_id(userInfo);
        loyalty.setBalance(100);
        userInfo.setLoyaltyCode_id(loyalty);



    Sales sales = new Sales();
    sales.setId(1L);

    List<Sales> salesList =List.of(sales);
    userInfo.setSales(salesList);

    //sales.setUserInfoId(userInfo);

        Product product = new Product();
        product.setId(1L);
        product.setName("name");
        Optional OptProd = Optional.of(product);
        httpSession.setAttribute("totalOrderPrice",1);
        List<CartItem> cart = new ArrayList<>();
        cart.add(new CartItem(product,2));
        httpSession.setAttribute("cart",cart);
        when(productServiceMock.findProductById(product.getId())).thenReturn(OptProd);
       Loyalty loyalityTest = userInfo.getLoyaltyCode_id();
       Long a =loyalityTest.getId();

        Optional<Loyalty> OptTest = loyaltyRepositoryMock.findById(1);
        Optional<Loyalty> OptLoy = Optional.of(loyalty);
        Optional<Sales> OptSales = Optional.of(sales);

        when(loyaltyRepositoryMock.findById(userInfo.getLoyaltyCode_id().getId())).thenReturn(OptLoy);
                when(salesRepositoryMock.findById(userInfo.getLoyaltyCode_id().getId())).thenReturn(OptSales);
//        Assert.assertTrue(shoppingCartService.performOrder(httpSession,userInfo));

    }

    @Test
    public void reserveTest(){
        ShoppingCartServiceImpl shoppingCartService = new ShoppingCartServiceImpl();
        ProductService productServiceMock = mock(ProductService.class);
        shoppingCartService.setProductService(productServiceMock);
        HttpSession httpSession = new MockHttpSession();


        httpSession.setAttribute("cart", null);

        Product product = new Product();
        product.setId(1L);

        CartItem cartItem = new CartItem();
        cartItem.setQuantity(1);
        cartItem.setProduct(product);
        cartItem.setTotalPrice();
        List<CartItem> cart = new ArrayList<>();
        cart.add(new CartItem(product,2));
        List<CartItem> cartItemList = List.of(cartItem);
        httpSession.setAttribute("cart", cart);
        List<CartItem> cartItems = (List<CartItem>) httpSession.getAttribute("cart");
        int a = cartItemList.get(0).getQuantity();

        Product productTest = new Product();
        productTest = cartItem.getProduct();
        when(productServiceMock.reserveItem(productTest,1,true)).thenReturn(true);
        shoppingCartService.reserve(httpSession);
    }

    @Test
    public void addFundsOnLoyaltyTest(){
       ShoppingCartServiceImpl shoppingCartService = new ShoppingCartServiceImpl();
       LoyaltyRepository loyaltyRepositoryMock = mock(LoyaltyRepository.class);
        UserInfoRepository userInfoRepositoryMock=mock(UserInfoRepository.class);
        Sales_has_productRepository sales_has_productRepositoryMock = mock(Sales_has_productRepository.class);
       UserService userServiceMock = mock(UserService.class);
        shoppingCartService.setLoyaltyRepository(loyaltyRepositoryMock);
       shoppingCartService.setUserInfoRepository(userInfoRepositoryMock);
       shoppingCartService.setSales_has_productRepository(sales_has_productRepositoryMock);
       shoppingCartService.setUserService(userServiceMock);
       int amount = 1;
       UserInfo userInfo = new UserInfo();
       userInfo.setId(1L);

       Loyalty loyalty = new Loyalty();
       loyalty.setId(1L);
       loyalty.setUserInfo_id(userInfo);
       userInfo.setLoyaltyCode_id(loyalty);
       Optional<Loyalty> OptLoy = Optional.of(loyalty);

        Mockito.when(loyaltyRepositoryMock.findById(userInfo.getLoyaltyCode_id().getId())).thenReturn(OptLoy);

        Assert.assertTrue(shoppingCartService.addFundsOnLoyalty(amount,userInfo));



    }
}