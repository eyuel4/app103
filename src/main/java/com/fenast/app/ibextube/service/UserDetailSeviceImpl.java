package com.fenast.app.ibextube.service;

import com.fenast.app.ibextube.constants.AppUrlConstant;
import com.fenast.app.ibextube.constants.EmailSubjectConstant;
import com.fenast.app.ibextube.constants.RestEndpointConstants;
import com.fenast.app.ibextube.db.model.authentication.User;
import com.fenast.app.ibextube.db.model.resource.UserDetail;
import com.fenast.app.ibextube.db.model.resource.VerificationToken;
import com.fenast.app.ibextube.db.repository.resource.IUserDetailRepository;
import com.fenast.app.ibextube.db.repository.resource.IVerificationTokenRepository;
import com.fenast.app.ibextube.exception.InvalidUserInputException;
import com.fenast.app.ibextube.exception.UserExistException;
import com.fenast.app.ibextube.service.IService.IEmailService;
import com.fenast.app.ibextube.service.IService.IUserDetailService;
import com.fenast.app.ibextube.service.IService.authentication.IUserService;
import com.fenast.app.ibextube.util.EmailValidator;
import com.fenast.app.ibextube.util.PhoneNumberValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.MimeMessage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
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

    @Autowired
    private EmailValidator emailValidatorUtil;

    @Autowired
    private JavaMailSender emailSender;


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
    public UserDetail signupUser(UserDetail userDetailInput) throws InvalidUserInputException {
        System.out.println(userDetailInput);
        UserDetail userDetail1 = findUserByName(userDetailInput.getUsername());
        boolean isEmail = false;
        boolean isPhone = false;

        if(userDetail1 != null) {
            System.out.println("An account with USERID is saved");
            System.out.println("An Account with USERID already created");
            throw new UserExistException("User Account already exist!");
        }
        else {
            isPhone = validateInputIsPhone(userDetailInput);
            if(!isPhone) {
                isEmail =  validateInputIsEmail(userDetailInput);
            }
            else if (!isPhone && !isEmail) {
                throw new InvalidUserInputException("Invalid User Input");
            }
            User user = new User();
            user.setUsername(userDetailInput.getUsername());
            user.setPassword(userDetailInput.getPassword());
            user.setEnabled(true);
            user.setAccountLocked(true);
            user.setCredentialsExpired(true);
            user.setAccountExpired(true);
            user.setConfirmed(false);
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

            confirmRegisteration(savedUserDetail, isEmail, isPhone);
            return savedUserDetail;
            // return "UserDetail Registered";
        }
    }

    private boolean validateInputIsPhone(UserDetail userDetail) {
        if (userDetail.getUsername() != null) {
            return PhoneNumberValidator.validatePhoneNumber(userDetail.getUsername());
        }
        return false;
    }

    private boolean validateInputIsEmail(UserDetail userDetail) {
        if (userDetail.getUsername() != null) {
            return emailValidatorUtil.validateEmail(userDetail.getUsername());
        }
        return false;
    }

    @Override
    public void confirmRegisteration(UserDetail userDetail, boolean isEmail, boolean isPhoneNumber) {


        // Send confirmation code through email
        if(isEmail) {
            String token = UUID.randomUUID().toString();
            createVerificationToken(userDetail, token, "SIGNUP");
            String x = AppUrlConstant.FRONT_END_APP_BASE_URL.getUrl();
            String xx = "http://localhost:4200/signup/confirm/"+token;
            String xy = "<html><body><a href='"+xx+"'>Confirm Email</a></body></html>";
            System.out.println(xx);
            String confirmationUrl = AppUrlConstant.FRONT_END_APP_BASE_URL.getUrl() +""+ RestEndpointConstants.SIGNUP_CONFIRM.getEndpoint() + token;
            System.out.println(confirmationUrl);
            //emailService.sendSimpleMessage(userDetail.getUsername(), EmailSubjectConstant.SIGNUP_CONFIRMATION.getEmailSubject(), xy);

            emailService.sendMessageWithAttachement(userDetail.getUsername(), "SignUp Confirmation", xy, null);
        }
        else if (isPhoneNumber) {
            // Send Confirmation code using phone number
        }
        else {
            throw new InvalidUserInputException("Invalid User Input");
        }

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

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime + "registered");
        LocalDateTime expirdate = localDateTime.plusDays(1);
        System.out.println(expirdate + "expired");
        verificationToken.setExpiryDate(expirdate);

        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public VerificationToken getVerificationToken(String verificationToken) {
        return verificationTokenRepository.findByToken(verificationToken);
    }

    /**
     * The following method will remove the verification code once user is confirmed
     * @param verificationToken
     */
    @Override
    public void deleteVerificationToken(VerificationToken verificationToken) {
        verificationTokenRepository.delete(verificationToken);
    }
}
