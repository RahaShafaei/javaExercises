package com.player.playlistapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.IM_USED)
public class ItemExistException  extends RuntimeException{
    public ItemExistException(String message) {
        super(message);
    }
}
