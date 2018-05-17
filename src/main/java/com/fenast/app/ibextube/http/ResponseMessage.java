package com.fenast.app.ibextube.http;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by taddesee on 5/17/2018.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMessage {
    private Boolean success;

    private String message;

    private String errorMsg;

    private int httpStatusCode;

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

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }
}
