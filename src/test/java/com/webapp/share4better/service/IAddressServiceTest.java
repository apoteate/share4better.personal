package com.webapp.share4better.service;

import com.webapp.share4better.model.Address;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.springframework.test.util.AssertionErrors.*;

@SpringBootTest
public class IAddressServiceTest {

    @Autowired
    private IAddressService service;

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
    public void testGetUpdateAndRemoveAddressInformation(){

        //getting data from table which was added.
        Optional<Address> getAddress = service.getAddressInformation(999999991);
        //asserting return data to the actual values.
        assertTrue("Retrieved Address from address Table",getAddress.isPresent());

        assertEquals("Home Address : " + getAddress.get().toString(), "300001 Mill shop Drive", getAddress.get().getHome_address());
        assertEquals("Home Address : " + getAddress.get().toString(), "charlotte", getAddress.get().getHome_city());
        assertEquals("Home Address : " + getAddress.get().toString(), "NC", getAddress.get().getHome_state());
        assertEquals("Home Address : " + getAddress.get().toString(), 28222, getAddress.get().getHome_zip());
        assertEquals("Home Address : " + getAddress.get().toString(), "USA", getAddress.get().getHome_country());

        assertEquals("Work Address : " + getAddress.get().toString(), "123 Mill Drive", getAddress.get().getWork_address());
        assertEquals("Work Address : " + getAddress.get().toString(), "charlotte", getAddress.get().getWork_city());
        assertEquals("Work Address : " + getAddress.get().toString(), "NC", getAddress.get().getWork_state());
        assertEquals("Work Address : " + getAddress.get().toString(), 28111, getAddress.get().getWork_zip());
        assertEquals("Work Address : " + getAddress.get().toString(), "USA", getAddress.get().getWork_country());
        assertEquals("Address ID to be matched ",999999991,getAddress.get().getId());


        //getting data from table which was added.
        Optional<Address> getAddress1 = service.getAddressInformation(1010101010);
        //asserting return data to the actual values.
        assertFalse("Retrieved Address from address Table",getAddress1.isPresent());
    }

}