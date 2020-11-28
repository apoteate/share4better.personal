package com.webapp.share4better.service;

import com.webapp.share4better.model.Contact;

import java.util.Optional;

public interface IContactService {
    public Optional<Contact> getContactInformation(Integer id);
    public void updateContactInfo(Contact contact);
    }
