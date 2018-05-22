package com.fenast.app.ibextube.service.IService;

import com.fenast.app.ibextube.db.model.resource.UserDetail;
import com.fenast.app.ibextube.db.model.resource.VerificationToken;

import java.util.List;

public interface IUserDetailService {
    List<UserDetail> getAllUsers();
    UserDetail findUserByName(String userName);
    UserDetail findUserById(int userById);
    boolean authenticateUser(String username, String password);
    UserDetail saveUser(UserDetail userDetail);
    void updateUserInfo(UserDetail userDetail);
    UserDetail signupUser(UserDetail userDetail);
    void confirmRegisteration(UserDetail userDetail, boolean isEmail, boolean isPhone);
    void createVerificationToken(UserDetail userDetail, String token, String type);
    VerificationToken getVerificationToken(String verificationToken);
}
