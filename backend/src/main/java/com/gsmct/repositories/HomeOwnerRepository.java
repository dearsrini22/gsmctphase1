package com.gsmct.repositories;

import com.gsmct.entities.HomeOwner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeOwnerRepository extends CrudRepository<HomeOwner, Integer> {
}
