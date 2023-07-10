package com.example.schoolPaymentManagement.exception;

/**
 * @author Raha
 * @since 2023-07-10
 */
public class BadParameterException extends RuntimeException {
    public BadParameterException(String msg) {
        super(msg);
    }
}