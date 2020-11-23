package com.webapp.share4better.repository;

import com.webapp.share4better.model.Contact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface IContactRepository extends CrudRepository<Contact, Integer> {
    @Query(value="SELECT * FROM CONTACT c WHERE c.phoneNumber = ?1", nativeQuery = true)
    public Iterable<Contact> listphoneNumber(Integer phoneNumber);

    @Query(value="SELECT * FROM CONTACT c WHERE c.additionalNumber = ?1", nativeQuery = true)
    public Iterable<Contact> listadditionalNumber(Integer additionalNumber);

}
