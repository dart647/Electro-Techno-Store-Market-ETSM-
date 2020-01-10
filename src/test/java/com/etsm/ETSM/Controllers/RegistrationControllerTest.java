package com.etsm.ETSM.Controllers;

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

 /*   @Test
    public void addUserTest(){
        RegistrationService registrationService = mock(RegistrationService.class);
        UserService userServiceMock = mock(UserService.class);
        HeaderService headerServiceMock = mock(HeaderService.class);

        RegistrationController registrationController = new RegistrationController(registrationService,userServiceMock);
        registrationController.setRegistrationService(registrationService);
        registrationController.setUserService(userServiceMock);
        registrationController.setHeaderService(headerServiceMock);

        User user = new User();
        user.setId(1L);

        BindingResult bindingResult = new BindingResult() {
            @Override
            public Object getTarget() {
                return null;
            }

            @Override
            public Map<String, Object> getModel() {
                return null;
            }

            @Override
            public Object getRawFieldValue(String field) {
                return null;
            }

            @Override
            public PropertyEditor findEditor(String field, Class<?> valueType) {
                return null;
            }

            @Override
            public PropertyEditorRegistry getPropertyEditorRegistry() {
                return null;
            }

            @Override
            public String[] resolveMessageCodes(String errorCode) {
                return new String[0];
            }

            @Override
            public String[] resolveMessageCodes(String errorCode, String field) {
                return new String[0];
            }

            @Override
            public void addError(ObjectError error) {

            }

            @Override
            public String getObjectName() {
                return null;
            }

            @Override
            public void setNestedPath(String nestedPath) {

            }

            @Override
            public String getNestedPath() {
                return null;
            }

            @Override
            public void pushNestedPath(String subPath) {

            }

            @Override
            public void popNestedPath() throws IllegalStateException {

            }

            @Override
            public void reject(String errorCode) {

            }

            @Override
            public void reject(String errorCode, String defaultMessage) {

            }

            @Override
            public void reject(String errorCode, Object[] errorArgs, String defaultMessage) {

            }

            @Override
            public void rejectValue(String field, String errorCode) {

            }

            @Override
            public void rejectValue(String field, String errorCode, String defaultMessage) {

            }

            @Override
            public void rejectValue(String field, String errorCode, Object[] errorArgs, String defaultMessage) {

            }

            @Override
            public void addAllErrors(Errors errors) {

            }

            @Override
            public boolean hasErrors() {
                return true;
            }

            @Override
            public int getErrorCount() {
                return 0;
            }

            @Override
            public List<ObjectError> getAllErrors() {
                return null;
            }

            @Override
            public boolean hasGlobalErrors() {
                return false;
            }

            @Override
            public int getGlobalErrorCount() {
                return 0;
            }

            @Override
            public List<ObjectError> getGlobalErrors() {
                return null;
            }

            @Override
            public ObjectError getGlobalError() {
                return null;
            }

            @Override
            public boolean hasFieldErrors() {
                return false;
            }

            @Override
            public int getFieldErrorCount() {
                return 0;
            }

            @Override
            public List<FieldError> getFieldErrors() {
                return null;
            }

            @Override
            public FieldError getFieldError() {
                return null;
            }

            @Override
            public boolean hasFieldErrors(String field) {
                return false;
            }

            @Override
            public int getFieldErrorCount(String field) {
                return 0;
            }

            @Override
            public List<FieldError> getFieldErrors(String field) {
                return null;
            }

            @Override
            public FieldError getFieldError(String field) {
                return null;
            }

            @Override
            public Object getFieldValue(String field) {
                return null;
            }

            @Override
            public Class<?> getFieldType(String field) {
                return null;
            }
        };
        BindingResult bindingResult1 = mock(BindingResult.class);


        String s = registrationController.addUser(user,bindingResult);
        Assert.assertEquals(s,"/registration");

        String s2 = registrationController.addUser(user,bindingResult1);
        Assert.assertEquals(s2,"redirect:/");

    }

*/
    }

