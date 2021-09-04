package com.person.CucumberTest;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.person.api.PersonRestController;
import com.person.entity.Person;
import com.person.repo.PersonRepo;
import com.person.service.PersonService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.*;



@ExtendWith(MockitoExtension.class)
public class PersonAPITest extends BaseTestConfiguration{

	private Response response;
	private ValidatableResponse json;
	private RequestSpecification request;
    private String ENDPOINT = "http://localhost:";

	@MockBean  
	PersonService personService;
	
	@InjectMocks
	PersonRestController restController;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	  @Autowired	
	private MockMvc mockMvc;
	
	@Mock
	PersonRepo repo;
	
	@Given("If person {int} {string} is present in the system")
	public void if_person_is_present_in_the_system(Integer int1, String string) {
	    // Write code here that turns the phrase above into concrete actions
	   // throw new io.cucumber.java.PendingException();
		
		
		
		
		RestAssuredMockMvc.standaloneSetup(personService);
		
		Person p1 = new Person();
		p1.setFirstNm(string);
		
		//BDDMockito.given(personService.getPerson(Long.valueOf(int1)))
		//		  .willReturn(Optional.ofNullable(p1));
		
	
		 
		
		
		
	}
	@When("person {int} is passed to the api and queried")
	public void person_is_passed_to_the_api_and_queried(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		
		//RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
		
		//personService = new PersonService(repo);

		
		mockMvc = MockMvcBuilders.standaloneSetup(restController).build();
		
		RestAssuredMockMvc.mockMvc(mockMvc);
		
		//RestAssuredMockMvc.standaloneSetup(restController,personService);
		//RestAssuredMockMvc.standaloneSetup(personService);
		
		Person p1 = new Person();
		p1.setFirstNm("Mah");
		
		//Mockito.when(repo.findById(Long.valueOf(int1)))
		//.thenReturn(Optional.ofNullable(p1));
		
		
		//Mockito.when(personService.getPerson(Long.valueOf(int1)))
		//.thenReturn(Optional.ofNullable(p1));
		
		BDDMockito.when(personService.getPerson(Long.valueOf(int1)))
		.thenReturn(Optional.ofNullable(p1));
		
		//BDDMockito.given(personService.getPerson(Long.valueOf(int1)))
		//.willReturn(Optional.ofNullable(p1));
		
		
		//BDDMockito.when(personService.getPerson(Long.valueOf(int1)))
		//.thenReturn(Optional.ofNullable(p1));
		
		
		//BDDMockito.given(personService.getPerson(Long.valueOf(int1)))
		 // .willReturn(Optional.ofNullable(p1));
		MockMvcResponse r = RestAssuredMockMvc
				.given().when()
				.get("/Persons/{id}",int1 )
				;
		
		System.out.println(" STAUS XXXXXXXXXX" +  r.statusCode() + r.asPrettyString() );
		
		
		
		response = getRequest()
				   .basePath("/Persons/{id}")
				   .pathParam("id",int1)
				   .get();
		
		
		
	}
	@Then("the status should be {int}")
	public void the_status_should_be(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		response.then().statusCode(int1);
	}
	@Then("the person name should be {string}")
	public void the_person_name_should_be(String string) {
	    // Write code here that turns the phrase above into concrete actions
	   // throw new io.cucumber.java.PendingException();
		
		response.then().assertThat()
			.body("Person.firstNm",Matchers.equalTo(string));
	}
	
	
	
	
	
	
	
	
	
}
