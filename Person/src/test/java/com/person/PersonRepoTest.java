package com.person;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.person.entity.Person;
import com.person.repo.PersonRepo;

@DataJpaTest
public class PersonRepoTest {

	@Autowired
	PersonRepo repo;
	
	List<Person> listP = new ArrayList<Person>();
	
	@BeforeEach 
	public void setup() {
		
		Person p = new Person();
		p.id = 11l;
		p.firstNm="Vin";		
		
		listP.add(p);
		
		p = new Person();
		p.id = 22l;
		p.firstNm="FName2";
		
		listP.add(p);

		repo.save(listP.get(0));
		
	}
	
	
	@Test
	public void shouldSave() {
		
		Person p1 = new Person();
		p1.firstNm = "Vin";
		repo.save(p1);
		
		assertThat(p1.id).isGreaterThan(0);
		
	}
	
	
	
	@Test
	public void shouldGetPersonByName() {
		
		List<Person> p = repo.findByFirstNm("Vin");
		
		assertEquals(p.stream().anyMatch(a->a.firstNm.equals("Vin")), true);
		
	}
	
	
	@Test
	public void shouldGetPersonByNameNotfound() {
		
		List<Person> p = repo.findByFirstNm("Vin");
		
		assertEquals(p.stream().anyMatch(a->a.firstNm.equals("Vin1")), false);
		
	}
	
	
	
	@Test
	@Order(1)
	public void testSave() {
		
		Person p = repo.save(listP.get(0));
		
		assertThat(p).isNotNull();
		//assertThat(p.id).isEqualTo(1);
		
		p = repo.save(listP.get(1));
		
		assertThat(p).isNotNull();
		//assertThat(p.id).isEqualTo(2);
		
		
		
	}
	
	
	@Test
	@Order(2)
	public void testGetAll() {
		
		//when(repo.findAll())
		//.thenReturn(listP);
		
		Iterable<Person> iter = repo.findAll();
		
		iter.forEach(e->System.out.println(e.toString()));
		
		assertThat(iter).isNotEmpty();
		
		assertThat(iter).isNotNull();
		
		//assertThat(iter.iterator().next().id).isEqualTo(3l);
		
		
	}
	
	
	
	
}
