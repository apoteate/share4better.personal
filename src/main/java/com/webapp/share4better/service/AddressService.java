package com.webapp.share4better.service;

import com.webapp.share4better.model.Address;
import com.webapp.share4better.repository.IAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService implements IAddressService{
    @Autowired
    private IAddressRepository repository;

    @Override
    public Optional<Address> getAddressInformation(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void updateAddressInfo(Address address) {
         repository.save(address);
    }
}
