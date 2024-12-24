package com.gsmct.repositories;

import com.gsmct.entities.BoardMemberType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardMemberTypeRepository extends CrudRepository<BoardMemberType, String> {
}
