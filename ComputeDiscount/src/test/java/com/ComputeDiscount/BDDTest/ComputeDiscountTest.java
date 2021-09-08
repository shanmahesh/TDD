package com.ComputeDiscount.BDDTest;



import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.response.Response;

public class ComputeDiscountTest extends BaseTestConfiguration{
 
	
	String getDscAPI = "";
	Response response;
	
	@Given("the compute disc API {string}")
	public void the_compute_disc_api(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	    getDscAPI = string + "/{x}";
	}

	@When("{int} is passed")
	public void is_passed(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		
		System.out.println(getDscAPI);
		
		response = getRequest()
					.basePath(getDscAPI)
					.pathParam("x", int1)
					.get();
		
	}

	@Then("the status code should be {int}")
	public void the_status_code_should_be(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	    response.then().statusCode(int1);
	}

	@Then("discount should be {int}")
	public void discount_should_be(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		response
			.then()
			.extract()
			.asString()
			.equalsIgnoreCase(String.valueOf(int1));

	}
	
	
}
