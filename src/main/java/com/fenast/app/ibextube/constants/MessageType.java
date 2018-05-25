package com.fenast.app.ibextube.constants;

public enum MessageType {
    Message_INFO("info"),
    Message_SUCCESS("success"),
    Message_WARNING("warning"),
    Message_ERROR("error");


    private String type;

    MessageType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
