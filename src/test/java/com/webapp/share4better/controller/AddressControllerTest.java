package com.webapp.share4better.controller;

import com.webapp.share4better.model.Address;
import com.webapp.share4better.service.TestUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.servlet.http.HttpServletRequest;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNull;

@SpringBootTest
public class AddressControllerTest {

    @Autowired
    private AddressController addressController;

    @Autowired
    private TestUtil testUtil;

    @BeforeEach
    public void setUpStart() {
        testUtil.addAddressTestData();

    }

    @AfterEach
    public void setUpEnd(){
        testUtil.removeAddressTestData();
    }

    @Test
    public void getAddressInformation() {

        HttpServletRequest mockRequest = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);

        when(mockRequest.getSession().getAttribute("userID")).thenReturn(999999991);
        Address address = addressController.getAddressInformation(mockRequest).getBody();

        assertEquals("Home Address : " + address.toString(), "300001 Mill shop Drive", address.getHome_address());
        assertEquals("Home Address : " + address.toString(), "charlotte", address.getHome_city());
        assertEquals("Home Address : " + address.toString(), "NC", address.getHome_state());
        assertEquals("Home Address : " + address.toString(), 28222, address.getHome_zip());
        assertEquals("Home Address : " + address.toString(), "USA", address.getHome_country());

        assertEquals("Work Address : " + address.toString(), "123 Mill Drive", address.getWork_address());
        assertEquals("Work Address : " + address.toString(), "charlotte", address.getWork_city());
        assertEquals("Work Address : " + address.toString(), "NC", address.getWork_state());
        assertEquals("Work Address : " + address.toString(), 28111, address.getWork_zip());
        assertEquals("Work Address : " + address.toString(), "USA", address.getWork_country());


    }

    @Test
    public void getAddressInformationNotPresentInDataBase() {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);

        when(mockRequest.getSession().getAttribute("userID")).thenReturn(88888888);
        Address address = addressController.getAddressInformation(mockRequest).getBody();

        assertNull("Home Address", address.getHome_address());
        assertNull("Work Address", address.getWork_address());

    }


    @Test
    public void updateContactInformation() {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);
        when(mockRequest.getSession().getAttribute("userID")).thenReturn(999999992);

        addressController.updateAddressInfo("300001 Mill shop Drive","charlotte", "NC", 28222,"USA","123 Mill Drive","charlotte", "NC", 28111,"USA", mockRequest);

        Address address = addressController.getAddressInformation(mockRequest).getBody();
        assertEquals("Home Address : " + address.toString(), "300001 Mill shop Drive", address.getHome_address());
        assertEquals("Home Address : " + address.toString(), "charlotte", address.getHome_city());
        assertEquals("Home Address : " + address.toString(), "NC", address.getHome_state());
        assertEquals("Home Address : " + address.toString(), 28222, address.getHome_zip());
        assertEquals("Home Address : " + address.toString(), "USA", address.getHome_country());

        assertEquals("Work Address : " + address.toString(), "123 Mill Drive", address.getWork_address());
        assertEquals("Work Address : " + address.toString(), "charlotte", address.getWork_city());
        assertEquals("Work Address : " + address.toString(), "NC", address.getWork_state());
        assertEquals("Work Address : " + address.toString(), 28111, address.getWork_zip());
        assertEquals("Work Address : " + address.toString(), "USA", address.getWork_country());


    }

}