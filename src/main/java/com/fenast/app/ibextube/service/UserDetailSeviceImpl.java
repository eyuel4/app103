package com.fenast.app.ibextube.service;

import com.fenast.app.ibextube.db.model.resource.UserDetail;
import com.fenast.app.ibextube.db.repository.resource.IUserDetailRepository;
import com.fenast.app.ibextube.service.IService.IUserDetailService;
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
}
