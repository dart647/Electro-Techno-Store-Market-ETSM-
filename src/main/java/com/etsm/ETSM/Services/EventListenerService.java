/*
 * Copyright (c) 2019. Smalkov Nikita.
 */

package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.CartItem;
import com.etsm.ETSM.Models.Product;
import com.etsm.ETSM.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EventListenerService implements ApplicationListener<OnRegistrationCompleteEvent> {

    private HttpSession httpSession;

    private ShoppingCartService shoppingCartService;

    private ProductService productService;

    private RegistrationService registrationService;

    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        try {
            this.confirmRegistration(event);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) throws MessagingException{
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        registrationService.createVerificationToken(user,token);
        String receiveAddress = user.getUsername();
        String subject = "Account confirmation";
        String confirmationUrl
                = event.getAppUrl() + "/registrationConfirm?token=" + token;
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"utf-8");
        String htmlMsg = "<h3>Вы успешно зарегистрировались!</h3><a href=\"http://mydomain.org:8090" + confirmationUrl + "\">Подтвердить регистрацию</a>";
        helper.setText(htmlMsg,true);
        helper.setTo(receiveAddress);
        helper.setSubject(subject);
        mailSender.send(mimeMessage);
    }

    @Autowired
    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
}
