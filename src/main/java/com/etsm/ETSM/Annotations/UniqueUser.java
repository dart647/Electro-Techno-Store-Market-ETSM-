/*
 * Copyright (c) 2019. Smalkov Nikita.
 */

package com.etsm.ETSM.Annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = UniqueUserValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUser {
    String message() default "Пользователь с такими данными уже существует!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
