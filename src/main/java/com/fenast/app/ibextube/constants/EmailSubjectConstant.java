package com.fenast.app.ibextube.constants;

/**
 * Created by taddesee on 5/21/2018.
 */
public enum EmailSubjectConstant {
    SIGNUP_CONFIRMATION("ACCOUNT CONFIRMATION");

    EmailSubjectConstant(String emailSubject) {
    }

    private String emailSubject;

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }
}
