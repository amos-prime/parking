package com.vattenfall.exceptions;

/**
 * Created by amoss on 23.01.14.
 */
public class UserNotFound  extends Exception {
    public UserNotFound() {
        super();
    }

    public UserNotFound(String message) {
        super(message);
    }
}