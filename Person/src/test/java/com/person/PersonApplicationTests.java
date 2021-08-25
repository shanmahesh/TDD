package com.person;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.person.api.PersonRestController;
import com.person.service.PersonService;

//@RunWith(SpringRunner.class)
@WebMvcTest(PersonRestController.class)
class PersonApplicationTests {

	@Autowired
	MockMvc mock;
	
	@Test
	void contextLoads() {
	}
	
	@MockBean
	PersonService serv;
	

	@Test
	void testHello() throws Exception {
		
		//mock.perform(MockMvcRequestBuilders.get("/")).andExpect(content().string("Hello Vin!!"));
		
		MvcResult res = mock.perform(MockMvcRequestBuilders.get("/")).andReturn();
		assertEquals("Hello Vin!!", res.getResponse().getContentAsString());
		
		assertThat(res.getResponse().getContentAsString()).isEqualTo("Hello Vin!!");
		
		
		
		//andExpect("Hello Vin!!");
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
