package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Annotations.EmailExistsException;
import com.etsm.ETSM.Models.Category;
import com.etsm.ETSM.Models.Role;
import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Services.HeaderService;
import com.etsm.ETSM.Services.RegistrationService;
import com.etsm.ETSM.Services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.spi.RegisterableService;
import javax.naming.Binding;

import java.beans.PropertyEditor;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class RegistrationControllerTest {

    @Test
    public void registrationTest(){

        RegistrationService registrationService = mock(RegistrationService.class);
        UserService userServiceMock = mock(UserService.class);
        HeaderService headerServiceMock = mock(HeaderService.class);

        RegistrationController registrationController = new RegistrationController(registrationService,userServiceMock);
        registrationController.setRegistrationService(registrationService);
        registrationController.setUserService(userServiceMock);
        registrationController.setHeaderService(headerServiceMock);

        User user = new User();
        user.setId(1L);
        user.setRoles(Collections.singleton(Role.ADMIN));
        Optional<User> userOptional = Optional.of(user);


        User finalUserForRole = user;
        Principal principal = () -> finalUserForRole.getUsername();
        headerServiceMock.setHeader(principal);

        Mockito.when(headerServiceMock.getHeaderRole()).thenReturn("ADMIN");
        Category category = new Category();
        category.setId(1L);
        List<Category> categoryList = List.of(category);
        Mockito.when(headerServiceMock.getHeaderCategories()).thenReturn(categoryList);

        ModelAndView m1 = registrationController.registration(principal);

        Assert.assertEquals(m1.getViewName(),"/registration");

    }

    @Test
    public void confirmRegistrationTest(){

       UserService userServiceMock = mock(UserService.class);
        RegistrationService registrationServiceMock = mock(RegistrationService.class);
        RegistrationController registrationController = new RegistrationController(registrationServiceMock,userServiceMock);
    }

    @Test
    public void addUserTest(){
        UserService userServiceMock = mock(UserService.class);
        RegistrationService registrationServiceMock = mock(RegistrationService.class);
        RegistrationController registrationController = new RegistrationController(registrationServiceMock,userServiceMock);
    ///



    }

  /*  @Test//(expected = EmailExistsException.class)
    public void createUserAccountTest() throws EmailExistsException {

        UserService userServiceMock = mock(UserService.class);
        RegistrationService registrationServiceMock = mock(RegistrationService.class);
        RegistrationController registrationController = new RegistrationController(registrationServiceMock,userServiceMock);
        registrationController.setRegistrationService(registrationServiceMock);
        ///

        User user = new User();
        user.setId(1L);
        Mockito.when(registrationServiceMock.AddNewUser(user)).thenReturn(user);

        registrationController.createUserAccount


    }

   */

    }

