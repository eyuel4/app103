package com.fenast.app.ibextube.controller;

import com.fenast.app.ibextube.db.model.User;
import com.fenast.app.ibextube.service.IService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ibex/api")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean authenticateUser(@RequestBody User user) throws Exception {
        boolean isAuthenticated = false;
        if(user != null) {
            if(user.getUsername() != null && user.getPassword() != null) {
                isAuthenticated = userService.authenticateUser(user.getUsername(), user.getPassword());
                System.out.println(isAuthenticated);
            }
        }
        return isAuthenticated;
/*        if(isAuthenticated) {
            return "successfully authenticated";
        }
        else {
            return "UserName and Password Incorrect";
        }*/

    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String registerUser(@RequestBody User user) throws Exception {
        System.out.println("Singup Controller executed");
        if(user != null) {
            System.out.println(user);
            User user1 = userService.findUserByName(user.getUsername());
            if(user1 != null) {
                System.out.println("An account with USERID is saved");
                return "An Account with USERID already created";
            }
            else {
                System.out.println("User Registered");
                userService.saveUser(user);
                return "User Registered";
            }
        }
        else {
            System.out.println("User is null");
            return "User is null";
        }
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getTest() throws Exception {
        return "{Value: Hello World}";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User findUserById(@PathVariable("id") String id) throws Exception {
        System.out.println("I was here");
        return userService.findUserByName(id);
    }

/*    @RequestMapping(value = "/user", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User findUserByName(@RequestBody User user) throws Exception {
        return userService.findUserByName(user.getUsername());
    }*/

    @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateUserInfo(@RequestBody User user) throws Exception {
        userService.updateUserInfo(user);
    }
}
