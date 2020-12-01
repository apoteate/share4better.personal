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
        assertEquals("Home Address to be matched","300001 Mill shop Drive charlotte NC 28222",getAddress.get().getHome_address());
        assertEquals("Work Address to be matched","123 Mill Drive charlotte NC 28111",getAddress.get().getWork_address());
        assertEquals("Address ID to be matched ",999999991,getAddress.get().getId());


        //getting data from table which was added.
        Optional<Address> getAddress1 = service.getAddressInformation(1010101010);
        //asserting return data to the actual values.
        assertFalse("Retrieved Address from address Table",getAddress1.isPresent());
    }

}