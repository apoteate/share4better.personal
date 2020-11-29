package com.webapp.share4better.service;

import com.webapp.share4better.model.Contact;
import com.webapp.share4better.repository.IContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ContactService implements IContactService {
    @Autowired
    private IContactRepository repository;

    @Override
    public Optional<Contact> getContactInformation(Integer id) {
        return repository.findById(id);
    }
    @Override
    public void updateContactInfo(Contact contact) {
        repository.save(contact);
    }

    @Override
    public void removeContactInfo(Contact contact) {
        repository.delete(contact);
    }
}
