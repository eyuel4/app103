package com.fenast.app.ibextube.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "User account already exist!")
public class UserExistException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String errorMessage;

    public UserExistException() {
        super();
    }

    public UserExistException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() { return errorMessage;}

    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
}
