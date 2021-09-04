package com.person.service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.person.entity.Person;
import com.person.repo.PersonRepo;



@Service
public class PersonService {
	
	
	PersonRepo personRepo;
	
	public PersonService(PersonRepo personRepo) {
		this.personRepo = personRepo;
	}
	
	
	public String getLookUp(Person p) {
		
		return "";
	}
	
	

	public List<Person> getPersons(){
		Iterable<Person> it = personRepo.findAll();
		
		
		
		List<Person> ps = new ArrayList<Person>();
		it.forEach(e->
		{
			/*
			if(e.firstNm.equalsIgnoreCase("FName")) {
				e.lastName = m.get(e.firstNm);
			}
			*/
			
			getLookUp(e);
			
			ps.add(e);
		}
				
				);
		
		Map<String, String> m = new HashMap<String,String>();
		
		int z = 0;
		
		return ps;
	}
	
	
	public Optional<Person> getPerson(Long id) {
		
		return personRepo.findById(id);
	}
	
	
	public Person save(Person p) {
		Person p1 = personRepo.save(p);
		
		if(p1.firstNm == null || p1.firstNm.trim().length() == 0)
			throw new InvalidParameterException();
		
		return p1;
	}
	
	public void delete(Long id) {
		personRepo.deleteById(id);
	}


	public Person edit(Long id, JsonPatch patch) throws JsonProcessingException, IllegalArgumentException, JsonPatchException {
		// TODO Auto-generated method stub
		
		Person personSource = personRepo.findById(id).get();
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		 JsonNode patched = patch.apply(objectMapper.convertValue(personSource, JsonNode.class));
		  
		 System.out.println(patched);
		 
		 Person target =  objectMapper.treeToValue(patched, Person.class);
		 
		 personRepo.save(target);
		
		return personRepo.findById(id).get();
	}


	public Person edit(Long id, Map<String, String> map) {
		// TODO Auto-generated method stub
		
		Person personSource = personRepo.findById(id).get();
		
		personSource.setFirstNm(map.get("firstNm"));
		
		 personRepo.save(personSource);
		
		 return personRepo.findById(id).get();
	}
	
	
}
