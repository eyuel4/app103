package com.fenast.app.ibextube.db.repository.authentication;

/**
 * Created by taddesee on 5/17/2018.
 */
import com.fenast.app.ibextube.db.model.authentication.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    @Query("FROM User u WHERE u.username = :userName")
    User findByUserName(@Param("userName") String userName);

    @Query("FROM User u WHERE u.id = :id")
    User findUserById(@Param("id") int idUser);
}