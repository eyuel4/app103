package com.fenast.app.ibextube.util;

/**
 * Created by taddesee on 6/7/2018.
 */
public class MaskHelper {
    public static String maskEmail(String email) {
        return email.replaceAll("(^[^@]{3}|(?!^)\\G)[^@]", "$1*");
    }

    public static String maskPhone(String phone) {
        return null;
    }
}
