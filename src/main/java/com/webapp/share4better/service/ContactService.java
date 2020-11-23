package com.webapp.share4better.service;

import com.webapp.share4better.model.Contact;
import com.webapp.share4better.repository.IContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService implements IContactService {
    @Autowired
    private IContactRepository repository;

    public Iterable<Contact> getPhoneNumber(Integer id) {
        return repository.listphoneNumber(id);
    }

    public Iterable<Contact> getAdditionalNumber(Integer id) {
        return repository.listadditionalNumber(id);
    }

}
