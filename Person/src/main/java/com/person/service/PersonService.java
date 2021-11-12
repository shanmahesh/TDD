package com.person.service;


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
import com.person.exception.InvalidPersonException;
import com.person.external.compute;
import com.person.repo.PersonRepo;



@Service
public class PersonService {
	
	
	compute computeServ;
	
	PersonRepo personRepo;
	
	public PersonService(PersonRepo personRepo) {
		this.personRepo = personRepo;
	}
	

    @Autowired
	public void setComputeServ(compute computeServ) {
		this.computeServ = computeServ;
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
		
		
		if(p.firstNm == null || p.firstNm.trim().length() == 0)
			throw new InvalidPersonException(null);
		
		Person p1 = personRepo.save(p);
		
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
	
	
	
	public String computeDisc(String fstNm, String age) {
		
		List<Person> prsns = personRepo.findByFirstNm(fstNm);
		
		
		String disc = computeServ.computeByAge(Integer.valueOf(age));
		
		
		return prsns.get(0).getFirstNm() + " gets " + disc ;
		
	}


	public Person statusChange(String name) {
		
		List<Person> prsns = personRepo.findByFirstNm(name);
		
		if(prsns.size() ==0) {
			return null;
		}else {
			prsns.get(0).setStatus("Added");
		}
		
		 personRepo.save(prsns.get(0));
		
		return prsns.get(0);
	}
	
}
