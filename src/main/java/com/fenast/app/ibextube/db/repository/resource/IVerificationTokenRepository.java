package com.fenast.app.ibextube.db.repository.resource;

import com.fenast.app.ibextube.db.model.resource.VerificationToken;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by taddesee on 5/21/2018.
 */
@Repository
public interface IVerificationTokenRepository extends JpaRepository<VerificationToken, Long>{
/*    @Query(value = "SELECT v FROM VerificationToken v WHERE v.idUser= :idUser AND v.type= :type", nativeQuery = true)
    VerificationToken findByUserIdAndType(@Param("ïdUser") String idUser,@Param("type") String type);*/

    @Query(value = "SELECT v.* FROM verificationToken v WHERE v.token= :token", nativeQuery = true)
    VerificationToken findByToken(@Param("token") String token);
}
