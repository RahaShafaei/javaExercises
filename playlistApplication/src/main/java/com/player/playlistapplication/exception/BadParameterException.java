package com.player.playlistapplication.exception;

import com.player.playlistapplication.dto.GenreDto;
import com.player.playlistapplication.model.Genre;

/**
 * @author Raha
 * @since 2023-06-22
 */
public class BadParameterException extends RuntimeException {
    public BadParameterException(String msg) {
        super(msg);
    }
}