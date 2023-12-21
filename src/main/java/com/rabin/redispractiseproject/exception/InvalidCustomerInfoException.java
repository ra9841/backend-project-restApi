package com.rabin.redispractiseproject.exception;

public class InvalidCustomerInfoException extends RuntimeException{
    public InvalidCustomerInfoException(String message) {
        super(message);
    }
}
