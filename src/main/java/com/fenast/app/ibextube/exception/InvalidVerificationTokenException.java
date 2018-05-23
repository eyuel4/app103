package com.fenast.app.ibextube.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by taddesee on 5/23/2018.
 */
@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED, reason = "Invalid Verification token")
public class InvalidVerificationTokenException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String errorMessage;

    public InvalidVerificationTokenException() { super(); }

    public InvalidVerificationTokenException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() { return errorMessage; }

    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
}
