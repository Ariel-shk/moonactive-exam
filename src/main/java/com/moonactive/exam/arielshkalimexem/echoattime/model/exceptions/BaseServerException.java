package com.moonactive.exam.arielshkalimexem.echoattime.model.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public abstract class BaseServerException extends RuntimeException {

    private final LocalDateTime timestamp = LocalDateTime.now();

    public BaseServerException(String message) {
        super(message);
    }

    public abstract HttpStatus getHttpStatus();

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
