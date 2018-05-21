package com.fenast.app.ibextube.controller;

import com.fenast.app.ibextube.db.model.resource.UserDetail;
import com.fenast.app.ibextube.db.model.authentication.User;
import com.fenast.app.ibextube.db.model.resource.VerificationToken;
import com.fenast.app.ibextube.exception.UserExistException;
import com.fenast.app.ibextube.exception.UserNotFoundException;
import com.fenast.app.ibextube.service.IService.IUserDetailService;
import com.fenast.app.ibextube.service.IService.authentication.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
        UserDetail u = userDetailService.findUserByName(id);
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

    @RequestMapping(value = "/registerationConfirm", method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void confirmSignup(@RequestParam("token") String token) {
        VerificationToken verificationToken = userDetailService.getVerificationToken(token);
        if (verificationToken == null) {
            // Throw Invalid Token Exception or link expired
        }

        UserDetail userDetail = verificationToken.getUserDetail();
        Calendar cal = Calendar.getInstance();
        if (verificationToken.getExpiryDate().getTime() - cal.getTime().getTime() <= 0) {
            // Throw link expired exception;
        }

        // Query from User user UserId
        // Then set enabled to true
        // then call user service and update the user
    }
}
