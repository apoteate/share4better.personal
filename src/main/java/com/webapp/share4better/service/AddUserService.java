package com.webapp.share4better.service;
import com.webapp.share4better.model.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class AddUserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertWithQuery(Profile person) {
        entityManager.createNativeQuery("INSERT INTO profile (user_id, user_name, user_password,donor_status,user_email) VALUES (?,?,?,?,?)")
                .setParameter(1, person.getUserId())
                .setParameter(2, person.getUserName())
                .setParameter(3, person.getUserPassword())
                .setParameter(4, person.isDonorStatus())
                .setParameter(5, person.getUserEmail())
                .executeUpdate();


    }
}