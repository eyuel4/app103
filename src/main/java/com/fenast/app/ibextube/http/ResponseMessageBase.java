package com.fenast.app.ibextube.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fenast.app.ibextube.constants.MessageType;

/**
 * Created by taddesee on 5/24/2018.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMessageBase implements java.io.Serializable  {

    private Boolean success;
    private String message;
    private String error;
    private int statusCode;
    private String message_type;

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

    public String getMessage_type() {
        return message_type;
    }

    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }

    @Override
    public String toString() {
        return "ResponseMessageBase{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", error='" + error + '\'' +
                ", statusCode=" + statusCode +
                ", message_type=" + message_type +
                '}';
    }
}
