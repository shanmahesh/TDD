package com.person;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.person.entity.Person;
import com.person.exception.InvalidPersonException;
import com.person.external.compute;
import com.person.repo.PersonRepo;
import com.person.service.PersonService;

//@RunWith(MockitoJUnitRunner.class)

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

	//@Autowired
	
	@InjectMocks
	PersonService serv ;
	

	@Autowired 
	compute computeServ; 
	
	List<Person> listP = new ArrayList<Person>();
	
	@Mock
	PersonRepo repo;
	
	@Mock 
	compute compServ;
	
	@BeforeEach
	public void setUp() {
		
		Person p = new Person();
		p.id = 11l;
		p.firstNm="FName";		
		p.lastName = "LN";
		
		listP.add(p);
		
		p = new Person();
		p.id = 22l;
		p.firstNm="FName2";
		
		listP.add(p);
		
		 serv = new PersonService(repo);
	}
	
	
	
	@Test
	public void shouldSave() {
		
		
		
		
		Person p1 = new Person();
		
		p1.firstNm = "Vin";
		p1.id = 1L;
		
		Mockito.when(repo.save(Mockito.any(Person.class))).thenReturn(p1);
		
		//Mockito.when(serv.save(p1)).thenReturn(p1);
		
		serv.save(p1);
		
		assertThat(p1.id).isEqualTo(1);
		
	}
	
	
	@Test
	public void shouldNotSave() {
		
		Person p1 = new Person();
		
		p1.firstNm = null;
		
		Assertions.assertThrows(InvalidPersonException.class, 
		()->serv.save(p1)
		);
		
		//assertThat(p1.id).isEqualTo(0);
		
	}
	
	@Test
	public void shouldComputeDisc() {
		
		
		
	}
	
	
	@Test
	public void shouldComputeDiscExternal() {
		
		String x = computeServ.computeByAge(22);
		
		System.out.println( " CCCCCC " + x);
		
		
	}
	
	
	
	@Test
	public void testFindAll() {
		
		//Iterable<Person> p = (Iterable<Person>) listP;
		
		//Mockito.when(repo.findAll()).thenReturn(p);
		
		BDDMockito.given(repo.findAll())
		.willReturn(listP);
		
		
		List<Person> ps = serv.getPersons();
		
		assertThat(ps.get(0).firstNm).isEqualTo("FName");
	
	}
	
	
	@Test
	public void testNameFound() {
	
		BDDMockito.given(repo.findAll())
		.willReturn(listP);
		
		List<Person> ps = serv.getPersons();
		
		ps.forEach(e->
		{
			if(e.firstNm.equalsIgnoreCase("FName"))
				assertThat(e.lastName).isEqualTo("LN");
		}
				
				);
		
	}
		
	
	
	
	
	
}
