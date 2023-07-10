package com.example.schoolPaymentManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Raha
 * @since 2023-07-10
 */
@ResponseStatus(code = HttpStatus.IM_USED)
public class ItemExistException  extends RuntimeException{
    public ItemExistException(String message) {
        super(message);
    }
}
