package com.person.api;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.person.entity.Person;
import com.person.service.PersonService;

@RestController
public class PersonRestController {

	@Autowired
	PersonService personService; 
	
	@GetMapping("/")
	public String hello() {
		return "Hello Vin!!";
	}
	
	@GetMapping("/Persons")
	public List<Person> getPersons(){
		
		return personService.getPersons();
	}
	
	@GetMapping("/Persons/{id}")
	public Optional<Person> getPerson(@PathVariable Long id) {
		
		return personService.getPerson(id);
	}
	
	
	@PostMapping("/Persons/save")
	public Person save(@RequestBody Person p) {
		
		System.out.println(p.firstNm);
		
		Person p1 = personService.save(p);
		return p1;
	}
	
	@DeleteMapping("/Persons/delete/{id}")
	public String delete(@PathVariable Long id) {
		
		personService.delete(id);
		
		return " Deleted " + id;
	}
	
	@PatchMapping(path =  "/Persons/{id}/edit", consumes = "application/json-patch+json")
	public Person edit(@PathVariable Long id, @RequestBody JsonPatch patch) throws JsonProcessingException, IllegalArgumentException, JsonPatchException {
		
		System.out.println(patch);
		
		Person p1 = personService.edit(id, patch);
		
		
		return p1;
	}
	
	@PatchMapping(path =  "/Persons/{id}/editMap")
	public Person editMap(@PathVariable Long id, @RequestBody Map<String,String> map) {
		
		System.out.println(map);
		
		Person p1 = personService.edit(id, map);
		return p1;
	}
	
	
}
