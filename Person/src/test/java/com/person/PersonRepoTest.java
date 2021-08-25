package com.person;

import static org.assertj.core.api.Assertions.assertThat;
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

//@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepoTest {

	@Autowired
	PersonRepo repo;
	
	List<Person> listP = new ArrayList<Person>();
	
	@BeforeEach 
	public void setup() {
		
		Person p = new Person();
		p.id = 11l;
		p.firstNm="FName";		
		
		listP.add(p);
		
		p = new Person();
		p.id = 22l;
		p.firstNm="FName2";
		
		listP.add(p);

		repo.save(listP.get(0));
		
	}
	
	/*
	@Test
	@Order(1)
	public void testSave() {
		
		Person p = repo.save(listP.get(0));
		
		assertThat(p).isNotNull();
		assertThat(p.id).isEqualTo(1);
		
		p = repo.save(listP.get(1));
		
		assertThat(p).isNotNull();
		assertThat(p.id).isEqualTo(2);
		
		
		
	}*/
	
	/*
	@Test
	@Order(2)
	public void testGetAll() {
		
		//when(repo.findAll())
		//.thenReturn(listP);
		
		Iterable<Person> iter = repo.findAll();
		
		iter.forEach(e->System.out.println(e.toString()));
		
		assertThat(iter).isNotEmpty();
		
		assertThat(iter).isNotNull();
		
		assertThat(iter.iterator().next().id).isEqualTo(3l);
		
		
	}*/
	
	
	
	
}
