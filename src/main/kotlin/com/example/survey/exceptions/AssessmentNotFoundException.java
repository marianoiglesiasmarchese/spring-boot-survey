package com.example.survey.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AssessmentNotFoundException extends BusinessException {

    private static final String DEFAULT_MESSAGE = "The assessment that you tried to find doesn't exist";

    public AssessmentNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public AssessmentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AssessmentNotFoundException(String message) {
        super(message);
    }

    public AssessmentNotFoundException(Throwable cause) {
        super(cause);
    }

}