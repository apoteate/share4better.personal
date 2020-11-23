package com.webapp.share4better.service;

import com.webapp.share4better.model.Address;
import com.webapp.share4better.repository.IAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService implements IAddressService{
    @Autowired
    private IAddressRepository repository;

    @Override
    public Iterable<Address> getAddressInformation(Integer id) {
        return repository.getAddressInformation(id);
    }
}
