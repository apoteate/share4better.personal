package com.webapp.share4better.repository;

import com.webapp.share4better.model.Contact;
import com.webapp.share4better.model.Food;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface IContactRepository extends CrudRepository<Contact, Integer> {
}
