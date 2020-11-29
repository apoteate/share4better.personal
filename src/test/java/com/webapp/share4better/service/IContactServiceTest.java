package com.webapp.share4better.service;

import com.webapp.share4better.model.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.*;

@SpringBootTest
public class IContactServiceTest {
    @Autowired
    private IContactService service;

    @Test
    public void testGetUpdateAndRemoveContactInformation(){

        Contact contact = new Contact();
        contact.setId(99999999);
        contact.setPhone_number("123-456-7891");
        contact.setAdditional_number("987-543-1234");
        //Adding test address to the table.
        service.updateContactInfo(contact);

        //getting data from table which was added.
        Optional<Contact> getContact = service.getContactInformation(99999999);
        //asserting return data to the actual values.
        assertTrue("Retrieved Contact from contact Table",getContact.isPresent());
        assertEquals(contact.getPhone_number(),getContact.get().getPhone_number());
        assertEquals(contact.getAdditional_number(),getContact.get().getAdditional_number());
        assertEquals(contact.getId(),getContact.get().getId());

        //removing added test data from the table.
        service.removeContactInfo(contact);
        //getting data from table which was added.
        Optional<Contact> getContact1 = service.getContactInformation(99999999);
        //asserting return data to the actual values.
        assertFalse("Retrieved Address from address Table",getContact1.isPresent());
    }
}