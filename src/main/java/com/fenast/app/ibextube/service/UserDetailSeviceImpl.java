package com.fenast.app.ibextube.service;

import com.fenast.app.ibextube.constants.AppUrlConstant;
import com.fenast.app.ibextube.constants.EmailSubjectConstant;
import com.fenast.app.ibextube.constants.RestEndpointConstants;
import com.fenast.app.ibextube.db.model.authentication.User;
import com.fenast.app.ibextube.db.model.resource.UserDetail;
import com.fenast.app.ibextube.db.model.resource.VerificationToken;
import com.fenast.app.ibextube.db.repository.resource.IUserDetailRepository;
import com.fenast.app.ibextube.db.repository.resource.IVerificationTokenRepository;
import com.fenast.app.ibextube.exception.UserExistException;
import com.fenast.app.ibextube.service.IService.IEmailService;
import com.fenast.app.ibextube.service.IService.IUserDetailService;
import com.fenast.app.ibextube.service.IService.authentication.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserDetailSeviceImpl implements IUserDetailService {

    @Autowired
    private IUserDetailRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserService userService;

    @Autowired
    private IEmailService emailService;

    @Autowired
    private IVerificationTokenRepository verificationTokenRepository;

    @Override
    public List<UserDetail> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetail findUserByName(String userName) {
        return userRepository.findByUserName(userName);
        //return null;
    }

    @Override
    public UserDetail findUserById(int userById) {
        return userRepository.findUserById(userById);
    }

    // To Be removed in the future
    @Override
    public boolean authenticateUser(String username, String password) {
        System.out.println(username + " " + password);
        UserDetail result = findUserByName(username);
        if(result == null) {
            System.out.println("result null");
        }

        if(result != null) {
            String encodePassword = passwordEncoder.encode(password);
/*            if(username.equalsIgnoreCase(result.getUsername()) && encodePassword.equalsIgnoreCase(result.getPassword())) {
                return true;
            }
            else {
                return false;
            }*/
        }
        return false;
    }

    @Override
    public UserDetail saveUser(UserDetail userDetail) {
        //userDetail.setPassword(passwordEncoder.encode(userDetail.getPassword()));
        return userRepository.save(userDetail);
    }

    @Override
    public void updateUserInfo(UserDetail userDetail) {
        userRepository.save(userDetail);
    }

    @Override
    public UserDetail signupUser(UserDetail userDetailInput) {
        System.out.println(userDetailInput);
        UserDetail userDetail1 = findUserByName(userDetailInput.getUsername());
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
            user.setEnabled(false);
            user.setAccountLocked(true);
            user.setCredentialsExpired(true);
            user.setAccountExpired(true);
            User savedUser = userService.saveUser(user);

            UserDetail userDetail = new UserDetail();
            userDetail.setIdUser(savedUser.getId());
            userDetail.setUsername(savedUser.getUsername());
            userDetail.setFirstName(userDetailInput.getFirstName());
            userDetail.setLastName(userDetailInput.getLastName());
            userDetail.setPassword(userDetailInput.getPassword());

            System.out.println("UserDetail Registered");

            UserDetail savedUserDetail = new UserDetail();
            savedUserDetail = saveUser(userDetail);

            confirmRegisteration(savedUserDetail);
            return savedUserDetail;
            // return "UserDetail Registered";
        }
    }

    @Override
    public void confirmRegisteration(UserDetail userDetail) {
        String token = UUID.randomUUID().toString();
        createVerificationToken(userDetail, token, "SIGNUP");


        String confirmationUrl = AppUrlConstant.FRONT_END_APP_BASE_URL.getUrl() +""+ RestEndpointConstants.SIGNUP_CONFIRM.getEndpoint() + token;
        emailService.sendSimpleMessage(userDetail.getUsername(), EmailSubjectConstant.SIGNUP_CONFIRMATION.getEmailSubject(), confirmationUrl);
    }

    /**
     * The following method will save the temporary url generated for signup verification or password reset
     * @param userDetail
     * @param token
     * @param type
     */
    @Override
    public void createVerificationToken(UserDetail userDetail, String token, String type) {
        VerificationToken verificationToken = new VerificationToken(token, userDetail, type);
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public VerificationToken getVerificationToken(String verificationToken) {
        return verificationTokenRepository.findByToken(verificationToken);
    }
}
