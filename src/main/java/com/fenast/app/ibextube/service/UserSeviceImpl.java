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
        return userRepository.findByUserName(userName);
    }

    // To Be removed in the future
    @Override
    public boolean authenticateUser(User user) {
        User result = findUserByName(user.getUsername());
        if(result != null) {
            if(user.getUsername().equalsIgnoreCase(result.getUsername()) && user.getPassword().equalsIgnoreCase(result.getPassword())) {
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
