package com.person;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;

import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
	

	@BeforeEach
	public  void addPerson() throws Exception {
		
		Person p = new Person();
		p.firstNm = "Mah";
		
	MvcResult s =
	    mock.perform(post("/Persons/save")
               // .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(p)))
	    		.andReturn()
	    		;
	
	//System.out.println(s.toString());
		
		//MvcResult res = mock.perform(
		//		post("/Persons/save"))
		//		.
		//		.andReturn();

		
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
	
	void testGetPersons() throws Exception {
	 
		Person p = new Person();
		p.firstNm = "Mah";
		
		 mock.perform(post("/Persons/save")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(p)))
		 			.andExpect(status().isOk())
		    		;
		
		mock.perform(get("/Persons"))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].firstNm", is("Mah") ));
		;
		//.andExpect(jsonPath("$[0].firstNm", is("Mah") ));
		
	}
	
	
	
	 static String asJsonString(final Object obj) {
	        try {
	            return new ObjectMapper().writeValueAsString(obj);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
	
	
	
}
