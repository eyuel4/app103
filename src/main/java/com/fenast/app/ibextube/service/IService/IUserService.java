package com.fenast.app.ibextube.service.IService;

import com.fenast.app.ibextube.db.model.User;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();
    User findUserByName(String userName);
    User findUserById(int userById);
    boolean authenticateUser(String username, String password);
    User saveUser(User user);
    void updateUserInfo(User user);
}
