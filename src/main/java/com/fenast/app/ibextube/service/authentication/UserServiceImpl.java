package com.fenast.app.ibextube.service.authentication;

import com.fenast.app.ibextube.db.model.authentication.User;
import com.fenast.app.ibextube.db.repository.authentication.IUserRepository;
import com.fenast.app.ibextube.service.IService.authentication.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by taddesee on 5/17/2018.
 */
@Repository
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder userPasswordEncoder;

    @Override
    public User findUserByName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User findUserById(int userById) {
        return userRepository.findUserById(userById);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(userPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
