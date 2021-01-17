package com.example.survey.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class AssessmentWithoutAnswersException extends BusinessException {

    private static final String DEFAULT_MESSAGE = "This assessment doesn't have answers";

    public AssessmentWithoutAnswersException() {
        super(DEFAULT_MESSAGE);
    }

    public AssessmentWithoutAnswersException(String message, Throwable cause) {
        super(message, cause);
    }

    public AssessmentWithoutAnswersException(String message) {
        super(message);
    }

    public AssessmentWithoutAnswersException(Throwable cause) {
        super(cause);
    }

}