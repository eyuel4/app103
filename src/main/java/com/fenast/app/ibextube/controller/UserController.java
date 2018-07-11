package com.fenast.app.ibextube.controller;

import com.fenast.app.ibextube.constants.MessageType;
import com.fenast.app.ibextube.db.model.resource.UserDetail;
import com.fenast.app.ibextube.db.model.authentication.User;
import com.fenast.app.ibextube.db.model.resource.VerificationToken;
import com.fenast.app.ibextube.exception.InvalidVerificationTokenException;
import com.fenast.app.ibextube.exception.UserNotFoundException;
import com.fenast.app.ibextube.http.PasswordRequest;
import com.fenast.app.ibextube.http.ResponseMessageBase;
import com.fenast.app.ibextube.http.UserDetailResponse;
import com.fenast.app.ibextube.service.IService.IUserDetailService;
import com.fenast.app.ibextube.service.IService.authentication.IUserService;
import com.fenast.app.ibextube.util.MaskHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;

@RestController
@RequestMapping("/ibex/api")
public class UserController {
    @Autowired
    private IUserDetailService userDetailService;

    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder userPasswordEncoder;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean authenticateUser(@RequestBody UserDetail userDetail) throws Exception {
        boolean isAuthenticated = false;
/*        if(userDetail != null) {
            if(userDetail.getUsername() != null && userDetail.getPassword() != null) {
                isAuthenticated = userService.authenticateUser(userDetail.getUsername(), userDetail.getPassword());
                System.out.println(isAuthenticated);
            }
        }*/
        return isAuthenticated;
/*        if(isAuthenticated) {
            return "successfully authenticated";
        }
        else {
            return "UserName and Password Incorrect";
        }*/

    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDetail registerUser(@RequestBody UserDetail userDetailInput) throws Exception {
        System.out.println("Singup Controller executed");
        UserDetail savedUserDetail = null;
        if(userDetailInput == null) {
            throw new UserNotFoundException("Username or Password not provided!");
        } else {
            savedUserDetail = userDetailService.signupUser(userDetailInput);
            return savedUserDetail;
        }
/*        if(userDetailInput != null) {
            System.out.println(userDetailInput);
            UserDetail userDetail1 = userDetailService.findUserByName(userDetailInput.getUsername());
            if(userDetail1 != null) {
                System.out.println("An account with USERID is saved");
                System.out.println("An Account with USERID already created");
                throw new UserExistException("User Account already exist!");
            }
            else {
                User user = new User();
                user.setEnabled(false);
                user.setUsername(userDetailInput.getUsername());
                user.setPassword(userDetailInput.getPassword());
                User savedUser = userService.saveUser(user);

                UserDetail userDetail = new UserDetail();
                userDetail.setIdUser(savedUser.getId());
                userDetail.setUsername(savedUser.getUsername());
                userDetail.setFirstName(userDetailInput.getFirstName());
                userDetail.setLastName(userDetailInput.getLastName());
                userDetail.setPassword(userDetailInput.getPassword());

                System.out.println("UserDetail Registered");
                UserDetail  x = userDetailService.saveUser(userDetail);
                if(x != null) {
                    x.setSuccess(true);
                }
               // return "UserDetail Registered";
            }
        }
        else {
            System.out.println("UserDetail is null");
            System.out.println("UserDetail is null");
        }
        return null;*/
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getTest() throws Exception {
        return "{Value: Hello World}";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDetailResponse getUserDetail(@PathVariable("id") String id) throws Exception {
        System.out.println("I was here "+ id);
        System.out.println(id);
        int idNum;
        try {
            idNum  =Integer.parseInt(id.trim());
        }
        catch (NumberFormatException e) {
            throw e;
        }
        UserDetail user = userDetailService.findUserById(idNum);

        boolean isVerified = userDetailService.isAcctActivated(idNum);

        UserDetailResponse userDetailResponse = new UserDetailResponse();
        userDetailResponse.setFirstName(user.getFirstName());
        userDetailResponse.setLastName(user.getLastName());
        userDetailResponse.setMiddleName(null);
        userDetailResponse.setPhotoUrl(user.getProfilePic());
        userDetailResponse.setAcctActivated(isVerified);

        System.out.println(userDetailResponse);
        return userDetailResponse;
    }

/*    @RequestMapping(value = "/user", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDetail findUserByName(@RequestBody UserDetail user) throws Exception {
        return userService.findUserByName(user.getUsername());
    }*/

    @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateUserInfo(@RequestBody UserDetail userDetail) throws Exception {
        userDetailService.updateUserInfo(userDetail);
    }

    @RequestMapping(value = "/registeration/confirm/{token}", method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessageBase confirmSignup(@PathVariable("token") String token) throws Exception {
        System.out.println("I was here to delete token");
        VerificationToken verificationToken = userDetailService.getVerificationToken(token);
        if (verificationToken == null) {
           // userService.findUserById(verificationToken.get)
            // Throw Invalid Token Exception or link expired
            throw new InvalidVerificationTokenException("Invalid verification code or link! User can't be verified");
        }

        System.out.println(verificationToken);
        UserDetail userDetail = verificationToken.getUserDetail();
        Calendar cal = Calendar.getInstance();
        Duration duration = Duration.between(LocalDateTime.now(), verificationToken.getExpiryDate());
        long diff = Math.abs(duration.toHours());
        System.out.println(diff);
        //verificationToken.getExpiryDate() - LocalDateTime.now()
        if (diff <= 0) {
            // Throw link expired exception;
            throw new InvalidVerificationTokenException("Verification code expired! Please try new verification code");
        }

        // Query from User user UserId
        // Then set enabled to true
        // then call user service and update the user
       // User user = userService.findUserByName(userDetail.getUsername());
        User user = userService.findUserById(userDetail.getIdUser());
        user.setConfirmed(true);
        userService.saveUser(user, false);

        userDetailService.deleteVerificationToken(verificationToken);
        ResponseMessageBase responseMessageBase = new ResponseMessageBase();
        responseMessageBase.setSuccess(true);
        responseMessageBase.setMessage_type(MessageType.Message_SUCCESS.getType());
        responseMessageBase.setMessage("Thanks you account is confirmed!");
        System.out.println(responseMessageBase);
        return responseMessageBase;
    }

    @RequestMapping(value = "/profile/edit/password/{token}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessageBase updatePassword(@PathVariable("token") String token, @RequestBody PasswordRequest passwordRequest ) throws Exception {
        return userDetailService.updatePassword(token, passwordRequest);
    }

    /**
     * The following endpoint will be called from the angular app when user clicks reset password for first
     * time. Email or Text will be sent with link
     * @param userDetailInput
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/profile/edit/password/reset", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseMessageBase resetPassword(@RequestBody UserDetail userDetailInput) throws Exception {
        System.out.println("Password reset I was called");
        userDetailService.requestUpdatePassword(userDetailInput);

        ResponseMessageBase respMsgBase = new ResponseMessageBase();
        respMsgBase.setMessage("Password reset link sent to "+ MaskHelper.maskEmail(userDetailInput.getUsername()));
        respMsgBase.setMessage_type(MessageType.Message_SUCCESS.getType());
        respMsgBase.setSuccess(true);
        return respMsgBase;
    }

    /**
     * The following method generate Recover Link and will email or text to the user
     * @param userDetail
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/profile/edit/password/recover", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseMessageBase getRecoverPasswordLink(@RequestBody UserDetail userDetail) throws Exception {
        System.out.println("Password Recover I was called");
        userDetailService.requestRecoverPassword(userDetail);

        ResponseMessageBase respMsgBase = new ResponseMessageBase();
        respMsgBase.setMessage("Password recover link sent to " + MaskHelper.maskEmail(userDetail.getUsername()));
        respMsgBase.setMessage_type(MessageType.Message_SUCCESS.getType());
        respMsgBase.setSuccess(true);
        return respMsgBase;
    }

    @RequestMapping(value = "/profile/edit/password/recover/{token}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseMessageBase changeForgotPassword(@PathVariable("token") String token, @RequestBody PasswordRequest passwordRequest) throws Exception {
        return userDetailService.updateForgotPassword(token, passwordRequest);
    }
}
