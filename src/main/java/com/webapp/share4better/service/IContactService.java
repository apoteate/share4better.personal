package com.webapp.share4better.service;

import com.webapp.share4better.model.Contact;

public interface IContactService {

    public Iterable<Contact> getPhoneNumber(Integer id);

    public Iterable<Contact> getAdditionalNumber(Integer id);

}
