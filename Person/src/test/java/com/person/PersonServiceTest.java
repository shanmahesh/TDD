package com.person;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.person.entity.Person;
import com.person.repo.PersonRepo;
import com.person.service.PersonService;

//@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

	//@Autowired
	PersonService serv ;
	
	@Mock
	PersonRepo repo;
	
	List<Person> listP = new ArrayList<Person>();
	
	
	@BeforeEach
	public void setUp() {
		
		Person p = new Person();
		p.id = 11l;
		p.firstNm="FName";		
		
		listP.add(p);
		
		p = new Person();
		p.id = 22l;
		p.firstNm="FName2";
		
		listP.add(p);
		
		 MockitoAnnotations.initMocks(this);
		
		Mockito.mock(PersonRepo.class);
		
		 serv = new PersonService(repo);
	}
	
	
	
	@Test
	public void testFindAll() {
		
		BDDMockito.given(repo.findAll())
		.willReturn(listP);
		
		List<Person> ps = serv.getPersons();
		
		assertThat(ps.get(0).firstNm).isEqualTo("FName");
	
	}
	
	
	/*@Test
	public void testNameFound() {
	
		BDDMockito.given(repo.findAll())
		.willReturn(listP);
		
		List<Person> ps = serv.getPersons();
		
		ps.forEach(e->
		{
			if(e.firstNm.equalsIgnoreCase("FName"))
				assertThat(e.lastName).isEqualTo("FirstNameFound");
		}
				
				);
		
	}*/
		
		
		
	
	
	
	
}
