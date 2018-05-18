package com.fenast.app.ibextube.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED, reason = "User Not Found")
public class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String errorMessage;

    public UserNotFoundException() { super(); }

    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() { return errorMessage; }

    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
}
