package com.player.playlistapplication.exception;

/**
 * @author Raha
 * @since 2023-06-22
 */
public class BadParameterException extends RuntimeException {
    public BadParameterException(String msg) {
        super(msg);
    }
}