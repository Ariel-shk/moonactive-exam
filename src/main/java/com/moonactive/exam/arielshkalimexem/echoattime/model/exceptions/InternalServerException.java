package com.moonactive.exam.arielshkalimexem.echoattime.model.exceptions;

import org.springframework.http.HttpStatus;

public class InternalServerException extends BaseServerException {

    public InternalServerException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
