package com.person.CucumberTest;

import org.apache.http.entity.ContentType;

import com.person.entity.Person;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class SavePrsonTest extends BaseTestConfiguration{

	private Response response;
	
	Person p;
	/*
	@Given("Input the {string} and {string} as name")
	public void input_the_and_as_name(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		
		p = new Person();
		p.firstNm = string;
		p.lastName = string2;
		
		
		
	}
	@When("Open the api {string} is called")
	public void open_the_api_is_called(String string) {
	    // Write code here that turns the phrase above into concrete actions
	   //throw new io.cucumber.java.PendingException();
		
		response = getRequest()
				.when()
				.contentType("application/json")
				.body(p)
				.basePath(string)
				.post();
	}
	@Then("Save Person Status should be {int}")
	public void save_person_status_should_be(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		
		response.then().statusCode(int1);
		
		response = getRequest().when().basePath("/Persons").get();
		
		System.out.println(response.asString());
		
	}
	*/
}
