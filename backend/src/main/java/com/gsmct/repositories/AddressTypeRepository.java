package com.gsmct.repositories;

import com.gsmct.entities.AddressType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressTypeRepository extends CrudRepository<AddressType, String> {
}
