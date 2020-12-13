package com.webapp.share4better.repository;

import com.webapp.share4better.model.Contact;
import com.webapp.share4better.model.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IUserRepository extends CrudRepository<Profile, Integer> {
    @Query(value = "SELECT * FROM profile WHERE user_email = :userEmail", nativeQuery = true)
    public Optional<Profile> userAuthentication(@Param("userEmail") String userEmail);

    @Query(value = "SELECT * FROM profile WHERE user_id = :userId", nativeQuery = true)
    public Optional<Profile> findUser(@Param("userId") Integer userId);
}