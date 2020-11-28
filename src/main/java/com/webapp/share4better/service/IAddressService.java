package com.webapp.share4better.service;

import com.webapp.share4better.model.Address;

import java.util.Optional;

public interface IAddressService {
    public Optional<Address> getAddressInformation(Integer id);
    public void updateAddressInfo(Address address);
}
