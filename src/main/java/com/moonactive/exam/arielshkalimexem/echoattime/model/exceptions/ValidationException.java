package com.moonactive.exam.arielshkalimexem.echoattime.model.exceptions;

import org.springframework.http.HttpStatus;

public class ValidationException extends BaseServerException {

    public ValidationException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
