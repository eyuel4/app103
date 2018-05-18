package com.fenast.app.ibextube.service;

import com.fenast.app.ibextube.db.model.authentication.User;
import com.fenast.app.ibextube.db.model.resource.UserDetail;
import com.fenast.app.ibextube.db.repository.resource.IUserDetailRepository;
import com.fenast.app.ibextube.exception.UserExistException;
import com.fenast.app.ibextube.service.IService.IUserDetailService;
import com.fenast.app.ibextube.service.IService.authentication.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserDetailSeviceImpl implements IUserDetailService {

    @Autowired
    private IUserDetailRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserService userService;

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
            User savedUser = userService.saveUser(user);

            UserDetail userDetail = new UserDetail();
            userDetail.setIdUser(savedUser.getId());
            userDetail.setUsername(savedUser.getUsername());
            userDetail.setFirstName(userDetailInput.getFirstName());
            userDetail.setLastName(userDetailInput.getLastName());
            userDetail.setPassword(userDetailInput.getPassword());

            System.out.println("UserDetail Registered");
            return saveUser(userDetail);
            // return "UserDetail Registered";
        }
    }
}
