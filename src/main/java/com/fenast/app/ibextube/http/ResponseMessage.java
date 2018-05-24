package com.fenast.app.ibextube.http;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by taddesee on 5/24/2018.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMessage implements java.io.Serializable  {

    private Boolean success;
    private String message;
    private String error;
    private int statusCode;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
