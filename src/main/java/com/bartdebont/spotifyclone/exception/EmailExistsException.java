package com.bartdebont.spotifyclone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FOUND)
public class EmailExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EmailExistsException(String message) {
        super(message);
    }
}
