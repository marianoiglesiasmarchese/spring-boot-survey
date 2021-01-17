package com.example.survey.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public abstract class BusinessException extends RuntimeException {

    protected static final String DEFAULT_MESSAGE = "Business Exception";

    public BusinessException() {
        super(DEFAULT_MESSAGE);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

}