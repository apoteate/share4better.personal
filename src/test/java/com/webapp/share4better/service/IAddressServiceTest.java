package com.webapp.share4better.service;

import com.webapp.share4better.model.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.springframework.test.util.AssertionErrors.*;

@SpringBootTest
public class IAddressServiceTest {

    @Autowired
    private IAddressService service;

    @Test
    public void testGetUpdateAndRemoveAddressInformation(){

        Address address = new Address();
        address.setId(99999999);
        address.setHome_address("123 Test Drive charlotte NC 28888 USA");
        address.setWork_address("123 Test work drive Charlotte NC 28888 USA");
        //Adding test address to the table.
        service.updateAddressInfo(address);

        //getting data from table which was added.
        Optional<Address> getAddress = service.getAddressInformation(99999999);
        //asserting return data to the actual values.
        assertTrue("Retrieved Address from address Table",getAddress.isPresent());
        assertEquals("Home Address to be matched",address.getHome_address(),getAddress.get().getHome_address());
        assertEquals("Work Address to be matched",address.getWork_address(),getAddress.get().getWork_address());
        assertEquals("Address ID to be matched ",address.getId(),getAddress.get().getId());

        //removing added test data from the table.
        service.removeAddress(address);
        //getting data from table which was added.
        Optional<Address> getAddress1 = service.getAddressInformation(99999999);
        //asserting return data to the actual values.
        assertFalse("Retrieved Address from address Table",getAddress1.isPresent());
    }

}