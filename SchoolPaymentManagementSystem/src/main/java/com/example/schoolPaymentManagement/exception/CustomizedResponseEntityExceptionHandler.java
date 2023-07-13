package com.example.schoolPaymentManagement.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

/**
 * @author Raha
 * @since 2023-07-10
 */
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllException(Exception ex,
                                                                 WebRequest request) throws Exception {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                ex.getClass().getSimpleName(),
                request.getDescription(false)
        );

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleItemNotFoundException(Exception ex,
                                                                          WebRequest request) throws Exception {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                ex.getClass().getSimpleName(),
                request.getDescription(false)
        );

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ItemNotIncludedException.class)
    public final ResponseEntity<ErrorDetails> handleItemNotIncludedException(Exception ex,
                                                                             WebRequest request) throws Exception {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                ex.getClass().getSimpleName(),
                request.getDescription(false)
        );

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {

        String msg = ex.getFieldError().getDefaultMessage();
        msg = "Total of Error:" + ex.getFieldErrorCount() + " _ " + msg;

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                msg,
                ex.getClass().getSimpleName(),
                request.getDescription(false)
        );

        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getLocalizedMessage(),
                ex.getClass().getSimpleName(),
                request.getDescription(false)
        );

        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDetails> handleSQLIntegrityConstraintViolationException(DataIntegrityViolationException ex,
                                                                                       WebRequest request) {

        String msg = ex.getMostSpecificCause().getMessage();
        msg = msg.substring(0, msg.indexOf("SQL statement", 0));

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                msg,
                ex.getClass().getSimpleName(),
                request.getDescription(false)
        );

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
