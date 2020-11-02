package com.webapp.share4better.repository;

import com.webapp.share4better.model.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IUserRepository extends CrudRepository<Profile, String> {
    @Query("SELECT e FROM user_authentication e WHERE user_name = :userName")
    Iterable<Profile> userAuthentication(@Param("userName") String userName);

    @Query("SELECT e FROM user_profile e WHERE user_name = :userName")
    Iterable<Profile> userProfile(@Param("userName") String userName);


}
