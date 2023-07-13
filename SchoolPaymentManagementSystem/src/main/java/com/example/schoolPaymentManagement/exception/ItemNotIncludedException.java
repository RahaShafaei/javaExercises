package com.example.schoolPaymentManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Raha
 * @since 2023-07-10
 */
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class ItemNotIncludedException extends RuntimeException {
    public ItemNotIncludedException(String message) {
        super(message);
    }
}
