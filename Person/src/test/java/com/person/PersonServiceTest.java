package com.person;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;



import com.person.entity.Person;
import com.person.repo.PersonRepo;
import com.person.service.PersonService;

//@RunWith(MockitoJUnitRunner.class)


@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

	//@Autowired
	PersonService serv ;
	

	
	List<Person> listP = new ArrayList<Person>();
	
	@Mock
	PersonRepo repo;
	
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
