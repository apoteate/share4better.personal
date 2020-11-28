package com.webapp.share4better.repository;

import com.webapp.share4better.model.Contact;
import org.springframework.data.repository.CrudRepository;


public interface IContactRepository extends CrudRepository<Contact, Integer> {
}
