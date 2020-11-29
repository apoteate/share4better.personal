package com.webapp.share4better.controller;

import com.webapp.share4better.model.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNull;


@SpringBootTest
public class ContactControllerTest {

    @Autowired
    private ContactController contactController;


    @Test
    public void getContactInformation() {

        HttpServletRequest mockRequest = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);

        when(mockRequest.getSession().getAttribute("userID")).thenReturn(999999991);
        Contact contact = contactController.getContactInformation(mockRequest).getBody();

        assertEquals("", "123-456-7891", contact.getPhone_number());
        assertEquals("", "987-543-1234", contact.getAdditional_number());

    }

    @Test
    public void getContactInformationNotPresentInDataBase() {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);

        when(mockRequest.getSession().getAttribute("userID")).thenReturn(88888888);
        Contact contact = contactController.getContactInformation(mockRequest).getBody();

        assertNull("NOT IN THE DB", contact.getPhone_number());
        assertNull("NOT IN THE DB", contact.getAdditional_number());
    }


    @Test
    public void updateContactInformation() {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);
        when(mockRequest.getSession().getAttribute("userID")).thenReturn(999999992);

        contactController.updateContactInfo("123","456",mockRequest);

        Contact contact = contactController.getContactInformation(mockRequest).getBody();
        assertEquals("checking if table is updated with new values","123",contact.getPhone_number());
        assertEquals("checking if table is updated with new values","456",contact.getAdditional_number());

        //revering back to original values
        contactController.updateContactInfo("987654321","987654321",mockRequest);
    }


}