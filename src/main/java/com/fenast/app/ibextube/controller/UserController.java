package com.fenast.app.ibextube.controller;

import com.fenast.app.ibextube.db.model.resource.UserDetail;
import com.fenast.app.ibextube.db.model.authentication.User;
import com.fenast.app.ibextube.db.model.resource.VerificationToken;
import com.fenast.app.ibextube.exception.InvalidVerificationTokenException;
import com.fenast.app.ibextube.exception.UserExistException;
import com.fenast.app.ibextube.exception.UserNotFoundException;
import com.fenast.app.ibextube.http.ResponseMessage;
import com.fenast.app.ibextube.service.IService.IUserDetailService;
import com.fenast.app.ibextube.service.IService.authentication.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.NumberFormat;
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
    public UserDetail findUserById(@PathVariable("id") String id) throws Exception {
        System.out.println("I was here "+ id);
        System.out.println(id);
        int idNum;
        try {
            idNum  =Integer.parseInt(id.trim());
        }
        catch (NumberFormatException e) {
            throw e;
        }
        UserDetail u = userDetailService.findUserById(idNum);
        System.out.println(u);
        return u;
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
    public ResponseMessage confirmSignup(@PathVariable("token") String token) {
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
        userService.saveUser(user);

        userDetailService.deleteVerificationToken(verificationToken);
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setSuccess(true);
        responseMessage.setMessage("Thanks you account is confirmed!");
        return responseMessage;
    }
}
