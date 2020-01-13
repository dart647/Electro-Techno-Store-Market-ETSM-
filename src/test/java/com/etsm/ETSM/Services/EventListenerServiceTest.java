package com.etsm.ETSM.Services;

import antlr.Token;
import com.etsm.ETSM.Models.CartItem;
import com.etsm.ETSM.Models.Product;
import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Models.VerificationToken;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationEvent;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mock.web.MockHttpSession;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import static org.mockito.Mockito.mock;

public class EventListenerServiceTest {


    @Test(expected = NullPointerException.class)
    public void onApplicationEventTest(){

        RegistrationService registrationServiceMock = mock(RegistrationService.class);
        JavaMailSender javaMailSenderMock = mock(JavaMailSender.class);
        HttpSession httpSessionMock = mock(HttpSession.class);
        ShoppingCartService shoppingCartServiceMock = mock(ShoppingCartService.class);
        ProductService productServiceMock = mock(ProductService.class);
        EventListenerService eventListenerService = new EventListenerService();
        eventListenerService.setMailSender(javaMailSenderMock);
        eventListenerService.setRegistrationService(registrationServiceMock);
        eventListenerService.setHttpSession(httpSessionMock);
        eventListenerService.setShoppingCartService(shoppingCartServiceMock);
        eventListenerService.setProductService(productServiceMock);

        User user = new User();
        user.setId(1L);
        user.setUsername("UserName");


        String token = UUID.randomUUID().toString();



        OnRegistrationCompleteEvent event = new OnRegistrationCompleteEvent(user, Locale.ENGLISH,"123");
        event.setUser(user);
        event.setAppUrl("fr234");

       // Session session = new Session();
        //MimeMessage mimeMessage = new MimeMessage();
       // mimeMessage
       // Mockito.when(javaMailSenderMock.createMimeMessage()).thenReturn()


        eventListenerService.onApplicationEvent(event);

    }
}