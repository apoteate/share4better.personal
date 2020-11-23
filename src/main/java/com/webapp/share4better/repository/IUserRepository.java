package com.webapp.share4better.repository;

import com.webapp.share4better.model.Contact;
import com.webapp.share4better.model.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IUserRepository extends CrudRepository<Profile, String> {
    @Query(value = "SELECT user_id,user_name,user_password,donor_status,user_email FROM profile WHERE user_email = :userEmail", nativeQuery = true)
    public Iterable<Profile> userAuthentication(@Param("userEmail") String userEmail);
}