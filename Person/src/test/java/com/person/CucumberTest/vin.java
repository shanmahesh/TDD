package com.person.CucumberTest;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

//@CucumberContextConfiguration
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,
//			classes = com.person.PersonApplication.class)
public class vin extends BaseTestConfiguration{

	private Response response;
	private ValidatableResponse json;
	private RequestSpecification request;
    private String ENDPOINT = "http://localhost:";

    //@LocalServerPort
    //int port;
    
	@Given("Open the api1 {string}")
	public void open_the_api1(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		System.out.println("I am in" + string);
	}
	@When("Open the api {string}")
	public void open_the_api(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		
		getRequest();
		
		response = getRequest().get();
				
		//System.out.println(getRequest().por);
		
		//RestAssured.port = port;
		//response =RestAssured.given().when().port(port).get();
		
		
		
		//getRequest().when().basePath("/");
		//System.out.println(port);
		System.out.println(RestAssured.basePath);
		
		//getRequest().when().baseUri("http://localhost");
		System.out.println(RestAssured.baseURI);
		System.out.println(RestAssured.port);
		
		
		
	//	System.out.println(RestAssured.port + " -- " + getPort());
		
		//response =getRequest().get();
		
		
	}
	@Then("Status should be {int}")
	public void status_should_be(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	    response.then().statusCode(int1);
	}
	@Then("Body shoulbe {string}")
	public void body_shoulbe(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	    String s = response.then().extract().asString();
	    
		assertEquals(s,string);
	    
		System.out.println(response.then().extract().asString());
	}

	
	
}
