package com.person.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.person.entity.Person;

@Repository
public interface PersonRepo extends CrudRepository<Person, Long> {

	public List<Person> findByFirstNm(String firstNm);
	
}
