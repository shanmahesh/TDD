package com.person.CucumberTest;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

import java.util.Optional;

import org.hamcrest.Matchers;
import org.hamcrest.core.IsEqual;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.person.api.PersonRestController;
import com.person.entity.Person;
import com.person.repo.PersonRepo;
import com.person.service.PersonService;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

@ExtendWith(MockitoExtension.class)
public class PersonAPITest extends BaseTestConfiguration{

	private Response response;
	private ValidatableResponse json;
	private RequestSpecification request;
    private String ENDPOINT = "http://localhost:";

    
    
    @Given("save person API is called with valid person")
    public void save_person_api_is_called_with_valid_person() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
    }
    
  /*  @When("person first name <fstNm> is passed")
    public void person_first_name_fst_nm_is_passed(String string) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        
     // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
    	
    	Person p1 = new Person();
		p1.setFirstNm(string);
		
		response = getRequest()
				   .basePath("/Persons/save")
				   .contentType(ContentType.JSON)
				   .body(p1)
				   .post();
    }*/
    
    @When("person first name {string} is passed")
    public void person_first_name_is_passed(String string) {
        // Write code here that turns the phrase above into concrete actions
       // throw new io.cucumber.java.PendingException();
        
    	Person p1 = new Person();
		p1.setFirstNm(string);
		
		response = getRequest()
				   .basePath("/Persons/save")
				   .contentType(ContentType.JSON)
				   .body(p1)
				   .post();
        
    }
    

    
    @Then("the returned person ID should be > {int}")
    public void the_returned_person_id_should_be1(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
       // throw new io.cucumber.java.PendingException();
    	
    	response.then().assertThat()
		.body("id",Matchers.greaterThan(int1));
    	
    }

    @Given("save person API is called with invalid person")
    public void save_person_api_is_called_with_invalid_person() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
    	
    }
    
    @When("person first name {string} is passed \\(invalid)")
    public void person_first_name_is_passed_invalid(String string) {
        // Write code here that turns the phrase above into concrete actions
       // throw new io.cucumber.java.PendingException();
    	
     	Person p1 = new Person();
    		p1.setFirstNm(string);
    		
    		response = getRequest()
    				   .basePath("/Persons/save")
    				   .contentType(ContentType.JSON)
    				   .body(p1)
    				   .post();
    	
    }

  
    
    @Then("the returned person ID should be {int}")
    public void the_returned_person_id_should_be(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
       // throw new io.cucumber.java.PendingException();
    	
    	response.then().statusCode(int1);
    }
    
    @Then("the returned body should be {string}")
    public void the_returned_person_id_should_be(String str) {
        // Write code here that turns the phrase above into concrete actions
       // throw new io.cucumber.java.PendingException();
    	
    	
    	System.out.println("RRRR-- " + response.asString());
    	
    	//response.then().assertThat()
		//.body("firstNm",Matchers.greaterThan(int1));
    	
    	response.then().assertThat()
		.body(equalToIgnoringCase(str));
    	
    }
    

    
    
	
	@Given("If person {int} {string} is present in the system")
	public void if_person_is_present_in_the_system(Integer int1, String string) {
	    // Write code here that turns the phrase above into concrete actions
	   // throw new io.cucumber.java.PendingException();

		/*Person p1 = new Person();
		p1.setFirstNm(string);
		
		response = getRequest()
				   .basePath("/Persons/save")
				   .contentType(ContentType.JSON)
				   .body(p1)
				   .post();
		
		response.then().statusCode(200);
	*/
		response = getRequest()
				   .basePath("/Persons")
				   .get();
	
		System.out.println(" GIVEN " + response.asString());
		
	}
	@When("person {int} is passed to the api and queried")
	public void person_is_passed_to_the_api_and_queried(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();

		response = getRequest()
				   .basePath("/Persons/{id}")
				   .pathParam("id",int1)
				   .get();
		
		System.out.println(response.asPrettyString());
		
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
		
		System.out.println(response.asString());
		
		response.then().assertThat()
			.body("firstNm",Matchers.equalTo(string));
	}
	
	
	@Before
	public void init() {
		request = getRequest();
	}
	
	@Given("For a given person {string} present in the system")
	public void for_a_given_person_present_in_the_system(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		
		 request.queryParam("name", string);
	}

	
	  @Autowired
	  private WireMockServer wireMockServer;
	
	@Given("{int} is passed to the api")
	public void is_passed_to_the_api(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	    request.queryParam("age", int1);
	}

	@When("api {string} is called with {int} then {int}")
	public void api_is_called_with_then(String string, Integer int1, Integer int2) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		
		this.wireMockServer.stubFor(
			      WireMock.get(WireMock.urlPathMatching("/getDisc/([0-9]*)"))
			      //.get("/getDisc/(*)")
			      
			        .willReturn(aResponse()
			          //.withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
			          //.withBody("[{\"userId\": 1,\"id\": 1,\"title\": \"Learn Spring Boot 3.0\", \"completed\": false}," +
			          //  "{\"userId\": 1,\"id\": 2,\"title\": \"Learn WireMock\", \"completed\": true}]"))
			        .withBody(String.valueOf(int2)))		
			    );
		
		
		
		response = request
				//.queryParam("name", string)
				//.queryParam("age", "0")
				.when()
				.get(string)
				.andReturn();
		
	}

	@Then("the {int} should be returned as {string} {int}")
	public void the_should_be_returned_as(Integer int1, String string, Integer int2) {
	    // Write code here that turns the phrase above into concrete actions
	 //   throw new io.cucumber.java.PendingException();
		response
		.then()
		.assertThat()
		.body(equalToIgnoringCase(string + int2 ));
		
	}
	
	
	
	
	
	String name = "";

	Person p1 = new Person();
	
	@Given("The person firstNm {string} available in the system")
	public void the_person_first_nm_available_in_the_system(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		name = string;
		
		
		p1.setFirstNm(string);
		
		response = getRequest()
				   .basePath("/Persons/save")
				   .contentType(ContentType.JSON)
				   .body(p1)
				   .post();
		
		p1 = response.as(Person.class);
		
	}
	@When("status is {string}")
	public void status_is(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		assertThat(p1.getStatus().equalsIgnoreCase(""));
		
	}
	@When("API \\/statusChange is called with {string} status")
	public void api_status_change_is_called_with_status(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		
		response = getRequest()
				   .basePath("/Persons/statusChange")
				   .contentType(ContentType.JSON)
				   .param("name", name)
				   .post();
		
		
	}
	@Then("the return code should be {int}")
	public void the_return_code_should_be(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		
		response.then().statusCode(int1);
	    
	}
	@Then("persons status should be {string}")
	public void persons_status_should_be(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		response.then().body("status", equalToIgnoringCase(string));
	}
	
	
	

	
}
