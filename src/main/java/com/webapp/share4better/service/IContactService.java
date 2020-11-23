package com.webapp.share4better.service;

import com.webapp.share4better.model.Contact;

public interface IContactService {

    public Iterable<Contact> getContactInformation(Integer id);

}
