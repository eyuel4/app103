package com.fenast.app.ibextube.constants;

/**
 * Created by taddesee on 5/21/2018.
 */
public enum RestEndpointConstants {
    SIGNUP_CONFIRM("/registerationConfirm?token=");

    RestEndpointConstants(String endpoint) {
    }

    private String endpoint;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
