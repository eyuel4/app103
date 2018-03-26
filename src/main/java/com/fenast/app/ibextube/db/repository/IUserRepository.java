package com.fenast.app.ibextube.db.repository;

import com.fenast.app.ibextube.db.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    @Query("FROM User u WHERE u.username = :userName")
    User findByUserName(@Param("userName") String userName);

}
