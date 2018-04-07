package com.fenast.app.ibextube.service;

import com.fenast.app.ibextube.db.model.User;
import com.fenast.app.ibextube.db.repository.IUserRepository;
import com.fenast.app.ibextube.service.IService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserSeviceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByName(String userName) {
        //return userRepository.findByUserName(userName);
        return null;
    }

    // To Be removed in the future
    @Override
    public boolean authenticateUser(String username, String password) {
        User result = findUserByName(username);
        if(result != null) {
            if(username.equalsIgnoreCase(result.getUsername()) && password.equalsIgnoreCase(result.getPassword())) {
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
        return userRepository.save(user);
    }
}
