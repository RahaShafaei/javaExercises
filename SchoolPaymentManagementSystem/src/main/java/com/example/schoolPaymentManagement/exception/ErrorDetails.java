package com.example.schoolPaymentManagement.exception;

import java.time.LocalDateTime;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 *     To handle exception and errors response.
 * </p>
 */
public class ErrorDetails {
    private LocalDateTime timeStamp;
    private String message;
    private String exceptionName;
    private String description;

    public ErrorDetails(LocalDateTime timeStamp, String message, String exceptionName, String description) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.description = description;
        this.exceptionName = exceptionName;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }
}
