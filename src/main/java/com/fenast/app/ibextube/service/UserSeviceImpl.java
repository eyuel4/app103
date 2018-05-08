package com.fenast.app.ibextube.service;

import com.fenast.app.ibextube.db.model.User;
import com.fenast.app.ibextube.db.repository.IUserRepository;
import com.fenast.app.ibextube.service.IService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserSeviceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByName(String userName) {
        return userRepository.findByUserName(userName);
        //return null;
    }

    @Override
    public User findUserById(int userById) {
        return userRepository.findUserById(userById);
    }

    // To Be removed in the future
    @Override
    public boolean authenticateUser(String username, String password) {
        System.out.println(username + " " + password);
        User result = findUserByName(username);
        if(result == null) {
            System.out.println("result null");
        }

        if(result != null) {
            String encodePassword = passwordEncoder.encode(password);
            if(username.equalsIgnoreCase(result.getUsername()) && encodePassword.equalsIgnoreCase(result.getPassword())) {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void updateUserInfo(User user) {
        userRepository.save(user);
    }
}
