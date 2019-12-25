/*
 * Copyright (c) 2019. Smalkov Nikita.
 */

package com.etsm.ETSM.Annotations;

import com.etsm.ETSM.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserValidator implements ConstraintValidator<UniqueUser,String> {

    private UserService userService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            userService.loadUserByUsername(s);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Override
    public void initialize(UniqueUser constraintAnnotation) {

    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
