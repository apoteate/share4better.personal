package com.webapp.share4better.service;

import com.webapp.share4better.model.Address;

public interface IAddressService {
    public Iterable<Address> getAddressInformation(Integer id);
}
