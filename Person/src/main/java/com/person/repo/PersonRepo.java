package com.person.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.person.entity.Person;

@Repository
public interface PersonRepo extends CrudRepository<Person, Long> {

}
