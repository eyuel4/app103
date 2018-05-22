package com.fenast.app.ibextube.util;

/**
 * Created by taddesee on 5/22/2018.
 */
public class PhoneNumberValidator {
    public static boolean validatePhoneNumber(String phoneNo) {
        //validating phone number with 1234567890 format
        if(phoneNo.matches("\\d{10}")) {
            return true;
        }
        else if (phoneNo.matches(("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}"))) {
            return true;
        }
        else if (phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) {
            return true;
        }
        else {
            return false;
        }
    }
}
