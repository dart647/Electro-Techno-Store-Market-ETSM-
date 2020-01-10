/*
 * Copyright (c) 2020. Smalkov Nikita.
 */

package com.etsm.ETSM.Annotations;

public class EmailExistsException extends Throwable {
    public EmailExistsException(final String message) {
        super(message);
    }
}
