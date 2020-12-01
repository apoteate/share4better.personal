package com.webapp.share4better.service;

import com.webapp.share4better.model.Contact;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

    @Autowired
    private TestUtil testUtil;

    @BeforeEach
    public void setUpStart() {
        testUtil.addContactTestData();

    }

    @AfterEach
    public void setUpEnd(){
        testUtil.removeContactTestData();
    }

    @Test
    public void testGetUpdateAndRemoveContactInformation(){

        //getting data from table which was added.
        Optional<Contact> getContact = service.getContactInformation(999999991);
        //asserting return data to the actual values.
        assertTrue("Retrieved Contact from contact Table",getContact.isPresent());
        assertEquals("123-456-7891",getContact.get().getPhone_number());
        assertEquals("987-543-1234",getContact.get().getAdditional_number());
        assertEquals(999999991,getContact.get().getId());

        //getting data from table which was added.
        Optional<Contact> getContact1 = service.getContactInformation(99999999);
        //asserting return data to the actual values.
        assertFalse("Retrieved Address from address Table",getContact1.isPresent());
    }
}