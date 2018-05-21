package com.fenast.app.ibextube.constants;

/**
 * Created by taddesee on 5/21/2018.
 */
public enum AppUrlConstant {
    FRONT_END_APP_BASE_URL("REGISTERATION CONFIRMATION");

    AppUrlConstant(String url) {
    }

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
