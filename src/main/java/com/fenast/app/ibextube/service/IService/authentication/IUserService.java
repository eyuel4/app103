package com.fenast.app.ibextube.service.IService.authentication;

import com.fenast.app.ibextube.db.model.authentication.User;

/**
 * Created by taddesee on 5/17/2018.
 */
public interface IUserService {
    User findUserByName(String userName);
    User findUserById(int userById);
    User saveUser(User user, boolean encode);
}
