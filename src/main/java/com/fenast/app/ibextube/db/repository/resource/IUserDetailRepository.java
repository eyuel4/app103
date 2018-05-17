package com.fenast.app.ibextube.db.repository.resource;

import com.fenast.app.ibextube.db.model.resource.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDetailRepository extends JpaRepository<UserDetail, Integer> {

    @Query("FROM UserDetail u WHERE u.username = :userName")
    UserDetail findByUserName(@Param("userName") String userName);

    @Query("FROM UserDetail u WHERE u.idUser = :idUser")
    UserDetail findUserById(@Param("idUser") int idUser);
}
