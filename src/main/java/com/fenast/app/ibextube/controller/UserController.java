package com.fenast.app.ibextube.controller;

import com.fenast.app.ibextube.db.model.User;
import com.fenast.app.ibextube.service.IService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ibex/api/v1/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String authenticateUser(@RequestBody User user) throws Exception {
        boolean isAuthenticated = false;
        if(user != null) {
            isAuthenticated = userService.authenticateUser(user);
        }

        if(isAuthenticated) {
            return "successfully authenticated";
        }
        else {
            return "UserName and Password Incorrect";
        }

    }

    public String registerUser(@RequestBody User user) throws Exception {
        if(user != null) {
            User user1 = userService.findUserByName(user.getUsername());
            if(user1 != null) {
                return "An Account with USERID already created";
            }
        }
        else {
            userService.saveUser(user);
            return "User Registered";
        }
        return null;
    }
}
