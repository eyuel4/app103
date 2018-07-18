package com.fenast.app.ibextube.service.IService;

import com.fenast.app.ibextube.db.model.resource.UserDetail;
import com.fenast.app.ibextube.db.model.resource.VerificationToken;
import com.fenast.app.ibextube.http.PasswordRequest;
import com.fenast.app.ibextube.http.ResponseMessageBase;

import java.util.List;

public interface IUserDetailService {
    List<UserDetail> getAllUsers();
    UserDetail findUserByName(String userName);
    UserDetail findUserById(int userById);
    boolean authenticateUser(String username, String password);
    UserDetail saveUser(UserDetail userDetail, boolean encode);
    void updateUserInfo(UserDetail userDetail);
    UserDetail signupUser(UserDetail userDetail);
    void confirmRegisteration(UserDetail userDetail, boolean isEmail, boolean isPhone);
    VerificationToken createVerificationToken(UserDetail userDetail, String type);
    VerificationToken getVerificationToken(String verificationToken);
    void deleteVerificationToken(VerificationToken verificationToken);
    void requestUpdatePassword(UserDetail userDetail);
    void requestRecoverPassword(UserDetail userDetail);
    ResponseMessageBase updatePassword(String token , PasswordRequest passwordRequest) throws Exception;
    boolean isAcctActivated(int userId);
    ResponseMessageBase updateForgotPassword(String token, PasswordRequest passwordRequest) throws Exception;
    ResponseMessageBase requestActivateAccount(UserDetail userDetail) throws Exception;
}
