package com.webapp.share4better.repository;

import com.webapp.share4better.model.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IAddressRepository extends CrudRepository<Address, Integer> {
}
