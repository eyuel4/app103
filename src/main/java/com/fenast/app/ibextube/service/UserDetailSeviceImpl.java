package com.fenast.app.ibextube.service;

import com.fenast.app.ibextube.constants.AppUrlConstant;
import com.fenast.app.ibextube.constants.MessageType;
import com.fenast.app.ibextube.constants.RestEndpointConstants;
import com.fenast.app.ibextube.db.model.authentication.User;
import com.fenast.app.ibextube.db.model.resource.UserDetail;
import com.fenast.app.ibextube.db.model.resource.VerificationToken;
import com.fenast.app.ibextube.db.repository.resource.IUserDetailRepository;
import com.fenast.app.ibextube.db.repository.resource.IVerificationTokenRepository;
import com.fenast.app.ibextube.exception.InvalidUserInputException;
import com.fenast.app.ibextube.exception.InvalidVerificationTokenException;
import com.fenast.app.ibextube.exception.UserExistException;
import com.fenast.app.ibextube.exception.UserNotFoundException;
import com.fenast.app.ibextube.http.PasswordRequest;
import com.fenast.app.ibextube.http.ResponseMessageBase;
import com.fenast.app.ibextube.service.IService.IEmailService;
import com.fenast.app.ibextube.service.IService.IUserDetailService;
import com.fenast.app.ibextube.service.IService.authentication.IUserService;
import com.fenast.app.ibextube.util.EmailValidator;
import com.fenast.app.ibextube.util.MaskHelper;
import com.fenast.app.ibextube.util.PhoneNumberValidator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserDetailSeviceImpl implements IUserDetailService {

    final static Log log = LogFactory.getLog(UserDetailSeviceImpl.class);

    @Autowired
    private IUserDetailRepository userRepository;

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

    @Autowired
    private PasswordEncoder userPasswordEncoder;

    @Autowired
    @Qualifier(value = "userPasswordEncoder")
    private PasswordEncoder userPasswordEncoder1;

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
            String encodePassword = userPasswordEncoder.encode(password);
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
    public UserDetail saveUser(UserDetail userDetail, boolean encode) {
        if (encode) {
            userDetail.setPassword(userPasswordEncoder1.encode(userDetail.getPassword()));
        }
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
            log.error("An Account with USERID already created");
            log.debug("An Account with USERID already created");
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
                log.debug("Invalid User Input on UserDetailServiceImpl");
                log.error("Invalid User Input on UserDetailServiceImpl");
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
            User savedUser = userService.saveUser(user, true);

            UserDetail userDetail = new UserDetail();
            userDetail.setIdUser(savedUser.getId());
            userDetail.setUsername(savedUser.getUsername());
            userDetail.setFirstName(userDetailInput.getFirstName());
            userDetail.setLastName(userDetailInput.getLastName());
            userDetail.setPassword(userDetailInput.getPassword());

            UserDetail savedUserDetail = new UserDetail();
            savedUserDetail = saveUser(userDetail, true);
            System.out.println("UserDetail Registered");
            log.debug("User " + savedUserDetail.getUsername()+"UserDetail Registered");
            log.error("User " + savedUserDetail.getUsername()+"UserDetail Registered");

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
            //String token = UUID.randomUUID().toString();
            VerificationToken verificationToken = createVerificationToken(userDetail, "ACCOUNT_CONFIRMATION");

            String url = "http://localhost:4200/signup/confirm/";
            String xx = "http://localhost:4200/signup/confirm/"+verificationToken.getToken();
            String xy = "<html><body><a href='"+xx+"'>Confirm Email</a></body></html>";
            System.out.println(xx);
            String confirmationUrl = AppUrlConstant.FRONT_END_APP_BASE_URL.getUrl() +""+ RestEndpointConstants.SIGNUP_CONFIRM.getEndpoint() + verificationToken.getToken();

            String message = url + verificationToken.getToken();
            sendEmail(userDetail,message);
            System.out.println(confirmationUrl);
            //emailService.sendSimpleMessage(userDetail.getUsername(), EmailSubjectConstant.SIGNUP_CONFIRMATION.getEmailSubject(), xy);

           // emailService.sendMessageWithAttachement(userDetail.getUsername(), "SignUp Confirmation", xy, null);
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
     * @param type
     */
    @Override
    public VerificationToken createVerificationToken(UserDetail userDetail, String type) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(token, userDetail, type);

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime + "registered");
        LocalDateTime expirdate = localDateTime.plusDays(1);
        System.out.println(expirdate + "expired");
        verificationToken.setExpiryDate(expirdate);

        return verificationTokenRepository.save(verificationToken);
    }

    @Override
    public VerificationToken getVerificationToken(String verificationToken) {
        return verificationTokenRepository.findByToken(verificationToken);
    }


    public ResponseMessageBase updatePassword(String token, PasswordRequest passwordRequest) throws Exception {
        VerificationToken verificationToken = getVerificationToken(token);
        if (verificationToken == null) {
            throw new InvalidVerificationTokenException("Invalid password update link");
        }

        UserDetail userDetail = verificationToken.getUserDetail();
        if (isTokenExpired(verificationToken)) {
            // Throw link expired exception
            throw new InvalidVerificationTokenException("Verification code expired!");
        }

        User user = userService.findUserById(userDetail.getIdUser());
        if (user != null) {
            String oldPasswordEncoded = userPasswordEncoder1.encode(passwordRequest.getOldPassword());
            if (checkPassword(passwordRequest.getOldPassword(), user.getPassword())) {
                //user.setPassword(userPasswordEncoder1.encode(passwordRequest.getNewPassword()));
                user.setPassword(passwordRequest.getNewPassword());
                userService.saveUser(user, true);
                deleteVerificationToken(verificationToken);
            } else {
                throw new InvalidVerificationTokenException("Invalid Password");
            }
        }

        ResponseMessageBase responseMessageBase = new ResponseMessageBase();
        responseMessageBase.setSuccess(true);
        responseMessageBase.setMessage_type(MessageType.Message_SUCCESS.getType());
        responseMessageBase.setMessage("Your password is updated!");
        return responseMessageBase;
    }

    /**
     * The following method will remove the verification code once user is confirmed
     * @param verificationToken
     */
    @Override
    public void deleteVerificationToken(VerificationToken verificationToken) {
        verificationTokenRepository.delete(verificationToken);
    }

    /**
     * The following method will be called the first time the user
     * call change password. Email will be sent to user with one time verification token
     * when user click on that it will redirect to update password
     * @param userDetail
     */
    @Override
    public void requestUpdatePassword(UserDetail userDetail) {
        UserDetail userDetail1 = userRepository.findByUserName(userDetail.getUsername());
        VerificationToken verificationToken = verificationTokenRepository.findByUserIdAndType("Update_password",userDetail1.getIdUser());

        String token = null;
        if (userDetail1 != null && verificationToken == null) {
            //token = UUID.randomUUID().toString();
            createVerificationToken(userDetail1,"Update_password");
        }
        else if (userDetail1 != null && verificationToken != null) {
            token = verificationToken.getToken();
        }
        String url = "http://localhost:4200/profile/edit/password/reset/";
        String message = url + token;

        sendMessageToUser(userDetail, message);
    }

    /**
     * Send Recover Password link with token to the User
     * @param userDetail
     */
    @Override
    public void requestRecoverPassword(UserDetail userDetail) {
        UserDetail userDetail1 = userRepository.findByUserName(userDetail.getUsername());
        if (userDetail1 == null) {
            throw new UserNotFoundException("User Account Not Found");
        }
        VerificationToken verificationToken = verificationTokenRepository.findByUserIdAndType("Recover_password",userDetail1.getIdUser());

        String token = null;
        if (userDetail1 != null && verificationToken == null) {
            //token = UUID.randomUUID().toString();
            verificationToken = createVerificationToken(userDetail1, "Recover_password");
            token = verificationToken.getToken();
        }
        else if (userDetail1 != null && verificationToken != null) {
            token = verificationToken.getToken();
        }
        String url = "http://localhost:4200/profile/edit/password/recover/";
        String message = url + token;
        sendMessageToUser(userDetail, message);
    }

    /**
     * The following method check if the user has signup with phone/email
     * and send text/email to user
     * @param userDetail
     * @param message
     */
    private void sendMessageToUser(UserDetail userDetail, String message) {
        boolean isEmail = validateInputIsEmail(userDetail);
        if (isEmail) {
            sendEmail(userDetail, message);
        }
    }

    /**
     * Send email with message as body to the user
     * @param userDetail
     * @param message
     */
    private void sendEmail(UserDetail userDetail, String message) {
        //String xx = url+token;
        String xy = "<html><body><a href='"+message+"'>Confirm Email</a></body></html>";
        System.out.println(message);
        //String confirmationUrl = url  + token;
        System.out.println(message);
        System.out.println(message);

        // emailService.sendMessageWithAttachement(userDetail.getUsername(), "SignUp Confirmation", xy, null);
    }

    /**
     * Send SMS Text
     */
    private void sendSmsText() {

    }

    private boolean checkPassword(String password, String hasedPassword) {
        return userPasswordEncoder1.matches(password, hasedPassword);
    }

    /**
     * Check if Account is activated or not
     * @param userId
     * @return
     */
    @Override
    public boolean isAcctActivated(int userId) {
       VerificationToken verificationToken = verificationTokenRepository.findByUserIdAndType("ACCOUNT_CONFIRMATION", userId);
       if (verificationToken != null) {
           return false;
       } else {
           return true;
       }
    }

    /**
     * Change forgotten Password
     * @param token
     * @param passwordRequest
     * @return
     * @throws Exception
     */
    @Override
    public ResponseMessageBase updateForgotPassword(String token, PasswordRequest passwordRequest) throws Exception {
        VerificationToken verificationToken = verificationTokenRepository.findByTokenAndType("Recover_password", token);
        if (verificationToken == null) {
            throw new InvalidVerificationTokenException("Invalid password update link");
        }

        UserDetail userDetail = verificationToken.getUserDetail();

        if (isTokenExpired(verificationToken)) {
            // Throw link expired exception
            throw new InvalidVerificationTokenException("Verification code expired!");
        }

        User user = userService.findUserById(userDetail.getIdUser());
        if (user != null) {
            user.setPassword(passwordRequest.getNewPassword());
            userService.saveUser(user, true);
            deleteVerificationToken(verificationToken);
        } else {
            throw new InvalidVerificationTokenException("Invalid Password");
        }

        ResponseMessageBase responseMessageBase = new ResponseMessageBase();
        responseMessageBase.setSuccess(true);
        responseMessageBase.setMessage_type(MessageType.Message_SUCCESS.getType());
        responseMessageBase.setMessage("Your password is updated!");
        return responseMessageBase;
    }

    /**
     * Check if a toke is expired or not
     * @param verificationToken
     * @return
     */
    private boolean isTokenExpired(VerificationToken verificationToken) {
        Calendar cal = Calendar.getInstance();
        Duration duration = Duration.between(LocalDateTime.now(), verificationToken.getExpiryDate());
        long diff = Math.abs(duration.toHours());
        if (diff <= 0) {
            return true;
        }
        else { return false; }
    }

    /**
     * The following method will create a token for activating user account
     * @param userDetail
     * @return
     * @throws Exception
     */
    @Override
    public ResponseMessageBase requestActivateAccount(UserDetail userDetail) throws Exception {
        VerificationToken verificationToken = verificationTokenRepository.findByUserIdAndType("ACCOUNT_CONFIRMATION", userDetail.getIdUser());
        UserDetail userDetail1 = verificationToken.getUserDetail();
        boolean isEmail = validateInputIsEmail(userDetail1);
        boolean isNumber = validateInputIsPhone(userDetail1);

        if (verificationToken != null) {
            if (isTokenExpired(verificationToken)) {
                deleteVerificationToken(verificationToken);
                verificationToken = createVerificationToken(userDetail1, "ACCOUNT_CONFIRMATION");
            }
        }
        if (verificationToken == null && userDetail != null) {
            verificationToken = createVerificationToken(userDetail1, "ACCOUNT_CONFIRMATION");
        }

        if (isEmail) {
            String message = "http://localhost:4200/signup/confirm/"+verificationToken.getToken();
            sendEmail(userDetail1, message);
        } else if (isNumber) {
            // Send a text with confirmation code
        }

        ResponseMessageBase respMsgBase = new ResponseMessageBase();
        respMsgBase.setMessage("Activation password send on email/phone "+ MaskHelper.maskEmail(userDetail1.getUsername()));
        respMsgBase.setMessage_type(MessageType.Message_SUCCESS.getType());
        respMsgBase.setSuccess(true);
        return respMsgBase;
    }

}
