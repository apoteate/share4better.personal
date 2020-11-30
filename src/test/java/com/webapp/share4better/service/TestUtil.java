package com.webapp.share4better.service;

import com.webapp.share4better.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestUtil {

    @Autowired
    private IAddressService addressService;

    private Address address_1 = createAddress(999999991,"300001 Mill shop Drive charlotte NC 28222","123 Mill Drive charlotte NC 28111");
    private Address address_2 = createAddress(999999992,"300001 Mill shop Drive charlotte NC 28222","123 Mill Drive charlotte NC 28111");

    public void addAddressTestData(){
        addressService.updateAddressInfo(address_1);
        addressService.updateAddressInfo(address_2);

    }

    public void removeAddressTestData(){
        addressService.removeAddress(address_1);
        addressService.removeAddress(address_2);

    }

    public Address createAddress(int id, String home, String work){
        Address address = new Address();
        address.setId(id);
        address.setHome_address(home);
        address.setWork_address(work);
        return address;
    }
}
