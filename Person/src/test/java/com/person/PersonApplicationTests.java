package com.person;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;

import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import  org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import com.person.entity.Person;
import com.person.service.PersonService;


@SpringBootTest
@AutoConfigureMockMvc
class PersonApplicationTests {

	  @Autowired
	  private WebApplicationContext wac;
	
	  @Autowired	
	private MockMvc mock;
	
	@Test
	void contextLoads() {
	}
	
	@MockBean
	PersonService serv;
	
List<Person> persons = new ArrayList<Person>();
	
	
	@BeforeEach
	public  void addPerson() throws Exception {
		
		Person p = new Person();
		p.firstNm = "Mah";
		
		persons.add(p);
		
	/*MvcResult s =
	    mock.perform(post("/Persons/save")
               // .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(p)))
	    		.andReturn()
	    		;
	*/
	//System.out.println(s.toString());
		
		//MvcResult res = mock.perform(
		//		post("/Persons/save"))
		//		.
		//		.andReturn();

		
	}
	
	
	@Test
	@DisplayName("Test get perosns")
	void shouldGetPersons() throws Exception {
		
		Mockito.when(serv.getPersons()).thenReturn(persons);
		
		
		mock.perform(get("/Persons"))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].firstNm", is("Mah") ));
		;
		
	}
	
	
	
	@Test
	@DisplayName("Test save perosn")
	void shouldSavePrson() throws Exception {
		
		Mockito.when(serv.save(Mockito.any())).thenReturn(persons.get(0));
		
		
		 mock.perform(post("/Persons/save")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(persons.get(0))))
		 			.andExpect(status().isOk())
		    		;
		
	}
	

	@Test
	@DisplayName("Test get perosn")
	void shouldGetPrson() throws Exception {
		
		Mockito.when(serv.getPerson(Mockito.any())).thenReturn(Optional.ofNullable(persons.get(0)));
		
		
		 mock.perform(get("/Persons/{id}",1))
				 	.andExpect(status().isOk())
		    		;
		
	}
	
	
	
	
	@Test
	void testHello() throws Exception {
		
		//mock.perform(MockMvcRequestBuilders.get("/")).andExpect(content().string("Hello Vin!!"));
		
		
		MvcResult res = mock.perform(MockMvcRequestBuilders.get("/")).andReturn();
		assertEquals("Hello Vin!!", res.getResponse().getContentAsString());
		
		assertThat(res.getResponse().getContentAsString()).isEqualTo("Hello Vin!!");

		//andExpect("Hello Vin!!");

	}
	
	@Test
	@DisplayName("GetDisc API test")
	public void shouldGetDisc() throws Exception {
		//Mockito.when(serv.save(Mockito.any())).thenReturn(persons.get(0));

		//MvcResult res = 
		mock.perform(get("/findDisc", 22 ))
		.andExpect(status().isOk());
		//.andExpect(status().isOk())
		//.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
		//.andExpect(MockMvcResultMatchers.jsonPath("$[0].firstNm", is("Mah") ));

	}
	
	 static String asJsonString(final Object obj) {
	        try {
	            return new ObjectMapper().writeValueAsString(obj);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
	
	
	
}
