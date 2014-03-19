package com.vattenfall.exceptions;

/**
 * User: smotynga
 */
public class DomainException extends RuntimeException {
    public DomainException(Throwable e) {
        super(e);
    }
}
