package com.gsmct.repositories;

import com.gsmct.entities.AssociationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssociationDetailsRepository extends JpaRepository<AssociationDetails, Integer> {
  List<AssociationDetails> findTop10ByNameContainsIgnoreCaseAndAddress_State(@Param("text") String text, @Param("state") String state);
}
