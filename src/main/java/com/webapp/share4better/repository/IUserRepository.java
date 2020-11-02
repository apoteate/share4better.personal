package com.webapp.share4better.repository;

import com.webapp.share4better.model.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IUserRepository extends CrudRepository<Profile, String> {
    @Query(value = "select FROM Users WHERE user_name =(:userName)", nativeQuery = true)
    public Iterable<Profile> userAuthentication(@Param("userName") String userName);

    @Query(value = "select FROM UserProfile WHERE user_name = :userName", nativeQuery = true)
    public Iterable<Profile> userProfile(@Param("userName") String userName);


}
